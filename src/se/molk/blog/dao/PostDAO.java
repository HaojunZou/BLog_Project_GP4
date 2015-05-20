package se.molk.blog.dao;

import se.molk.blog.domain.Post;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PostDAO {
    private static final String driver = "org.gjt.mm.mysql.Driver";
    private static final String url = "jdbc:mysql://localhost/blog";
    private static final String dbUserName = "root";
    private static final String dbPassword = "haojun";

    public PostDAO() throws Exception{
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            throw  new Exception("Error creating driver");
        }
    }

/*
    public List<Post> getAllPublishedPosts() throws SQLException, ClassNotFoundException {
        return getAllPosts(true);
    }

    public List<Post> getAllPosts() throws SQLException, ClassNotFoundException {
        return getAllPosts(false);
    }
*/

    public List<Post> getAllPosts() throws ClassNotFoundException, SQLException {
        List<Post> postList = new LinkedList<Post>();

        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String postSearchQuery = "select * from Posts order by publishedDate desc";
            Statement statement = connection.createStatement();
            /*
            if(onlyPublished){
                postSearchQuery += "where published = true";
            }
            */
            ResultSet resultSet = statement.executeQuery(postSearchQuery);
            while (resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getInt("post_id"));
                post.setTitle(resultSet.getString("postTitle"));
                post.setBody(resultSet.getString("postBody"));
                post.setUserId(resultSet.getInt("userId"));
                post.setDate(resultSet.getString("publishedDate"));
                post.setPublished(resultSet.getBoolean("published"));
/*
                int userId = resultSet.getInt("userId");
                UserDAO userDAO = new UserDAO();
                post.setAuthor(userDAO.getUserById(userId));
*/
                int categoryId = resultSet.getInt("categoryId");
                CategoryDAO categoryDAO = new CategoryDAO();
                post.setCategory(categoryDAO.getCategoryById(categoryId));

                postList.add(post);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return postList;
    }

    public Post getPostById(int id){
        return null;
    }

    public void publishNewPost(String title, String body) throws SQLException {
        //int userId = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String publishedDate = dateFormat.format(date);
        try {
            //UserDAO userDAO = new UserDAO();
            //userId = userDAO.getUserIdByUserName(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String idCheckQuery = "select * from Posts where post_id=?"; //check if this id number has been token
            String insertQuery =
                    "insert into Posts (post_id, postTitle, postBody, publishedDate)" +
                            "values(?,?,?,?)";   //insert a record to user table
            int idNumber = 1;
            for (int i = 1; i < 1.0e9; i++) {
                PreparedStatement pstIdCheck = connection.prepareStatement(idCheckQuery);
                pstIdCheck.setString(1, Integer.toString(i));
                ResultSet resultSetId = pstIdCheck.executeQuery();  //check if this id number has been token
                if (resultSetId.next()) {     //if id number exist, than make it plus 1
                    pstIdCheck.setString(1, Integer.toString(i + 1));
                    pstIdCheck.close();
                } else {                      //if not, use this id number
                    idNumber = i;
                    pstIdCheck.close();
                    break;
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, Integer.toString(idNumber));
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, body);
            //preparedStatement.setString(4, Integer.toString(userId));
            preparedStatement.setString(4, publishedDate);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
