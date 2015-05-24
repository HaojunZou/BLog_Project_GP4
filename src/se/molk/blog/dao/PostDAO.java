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

    public List<Post> getPostsByTitle(String title) throws SQLException {
        List<Post> postList = new LinkedList<Post>();
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String postSearchQuery = "select * from Posts where postTitle like ?";
            PreparedStatement pstSearch = connection.prepareStatement(postSearchQuery);
            pstSearch.setString(1, "%" + title + "%");
            ResultSet resultSet = pstSearch.executeQuery();
            while (resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getInt("post_id"));
                post.setTitle(resultSet.getString("postTitle"));
                post.setBody(resultSet.getString("postBody"));
                post.setUserId(resultSet.getInt("userId"));
                post.setDate(resultSet.getString("publishedDate"));
                post.setPublished(resultSet.getBoolean("published"));

                int categoryId = resultSet.getInt("categoryId");
                CategoryDAO categoryDAO = new CategoryDAO();
                post.setCategory(categoryDAO.getCategoryById(categoryId));

                postList.add(post);
            }
            pstSearch.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return postList;
    }

    public List<Post> getPostsByUserId(int userId) throws SQLException {
        List<Post> postList = new LinkedList<Post>();
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String postSearchQuery = "select * from Posts where userId = ?";
            PreparedStatement pstSearch = connection.prepareStatement(postSearchQuery);
            pstSearch.setInt(1, userId);
            ResultSet resultSet = pstSearch.executeQuery();
            while (resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getInt("post_id"));
                post.setTitle(resultSet.getString("postTitle"));
                post.setBody(resultSet.getString("postBody"));
                post.setUserId(resultSet.getInt("userId"));
                post.setDate(resultSet.getString("publishedDate"));
                post.setPublished(resultSet.getBoolean("published"));

                int categoryId = resultSet.getInt("categoryId");
                CategoryDAO categoryDAO = new CategoryDAO();
                post.setCategory(categoryDAO.getCategoryById(categoryId));

                postList.add(post);
            }
            pstSearch.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return postList;
    }

    public List<Post> getPostsByFuzzySearch(String fuzzy) throws SQLException {
        List<Post> postList = new LinkedList<Post>();
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String postSearchQuery = "select * from Posts where postTitle like ? or postBody like ?";
            PreparedStatement pstSearch = connection.prepareStatement(postSearchQuery);
            pstSearch.setString(1, "%" + fuzzy + "%");
            pstSearch.setString(2, "%" + fuzzy + "%");
            ResultSet resultSet = pstSearch.executeQuery();
            while (resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getInt("post_id"));
                post.setTitle(resultSet.getString("postTitle"));
                post.setBody(resultSet.getString("postBody"));
                post.setUserId(resultSet.getInt("userId"));
                post.setDate(resultSet.getString("publishedDate"));
                post.setPublished(resultSet.getBoolean("published"));

                int categoryId = resultSet.getInt("categoryId");
                CategoryDAO categoryDAO = new CategoryDAO();
                post.setCategory(categoryDAO.getCategoryById(categoryId));

                postList.add(post);
            }
            pstSearch.close();
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

    public boolean publishNewPost(String title, String body, String author) throws SQLException {
        boolean published = false;
        int userId;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String publishedDate = dateFormat.format(date);
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try {
            UserDAO userDAO = new UserDAO();
            userId = userDAO.getUserIdByUserName(author);
            if(userId!=0){
                try{
                    String idCheckQuery = "select * from Posts where post_id=?"; //check if this id number has been token
                    String insertQuery =
                            "insert into Posts (post_id, postTitle, postBody, userId, publishedDate)" +
                                    "values(?,?,?,?,?)";   //insert a record to user table
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
                    preparedStatement.setInt(1, idNumber);
                    preparedStatement.setString(2, title);
                    preparedStatement.setString(3, body);
                    preparedStatement.setInt(4, userId);
                    preparedStatement.setString(5, publishedDate);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    published = true;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                published = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return published;
    }

    public boolean checkPostByPostId(int post_id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String checkQuery = "select * from Posts where post_id = ?";
            PreparedStatement pstCheck = connection.prepareStatement(checkQuery);
            pstCheck.setInt(1, post_id);
            ResultSet checkResult = pstCheck.executeQuery();
            if(checkResult.next()){
                pstCheck.close();
                return true;
            }else {
                pstCheck.close();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return false;
    }

    public boolean deleteAPost(int post_id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        if(checkPostByPostId(post_id)){
            try{
                String deleteQuery = "delete from Posts where post_id = ?";
                PreparedStatement pstDelete = connection.prepareStatement(deleteQuery);
                pstDelete.setInt(1, post_id);
                pstDelete.executeUpdate();
                pstDelete.close();
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                connection.close();
            }
        }else{
            connection.close();
            return false;
        }
        return false;
    }

}
