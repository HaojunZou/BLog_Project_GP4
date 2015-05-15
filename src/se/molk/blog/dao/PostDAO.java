package se.molk.blog.dao;

import se.molk.blog.domain.Post;
import java.sql.*;
import java.util.*;

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

    public List<Post> getAllPublishedPosts() throws SQLException, ClassNotFoundException {
        return getAllPosts(true);
    }

    public List<Post> getAllPosts() throws SQLException, ClassNotFoundException {
        return getAllPosts(false);
    }

    private List<Post> getAllPosts(boolean onlyPublished) throws ClassNotFoundException, SQLException {
        List<Post> postList = new LinkedList<Post>();
        Post post = new Post();

        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String postSearchQuery = "select * from Posts";
            Statement statement = connection.createStatement();
            if(onlyPublished){
                postSearchQuery += "where published = true";
            }
            ResultSet resultSet = statement.executeQuery(postSearchQuery);
            while (resultSet.next()){
                post.setId(resultSet.getInt("post_id"));
                post.setTitle(resultSet.getString("postTitle"));
                post.setBody(resultSet.getString("postBody"));
                post.setPublished(resultSet.getBoolean("published"));

                int userId = resultSet.getInt("userId");
                UserDAO userDAO = new UserDAO();
                post.setAuthor(userDAO.getUserById(userId));

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
}
