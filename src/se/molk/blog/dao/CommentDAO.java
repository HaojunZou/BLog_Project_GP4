package se.molk.blog.dao;

import se.molk.blog.domain.Comment;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CommentDAO {

    private static final String driver = "org.gjt.mm.mysql.Driver";
    private static final String url = "jdbc:mysql://localhost/blog";
    private static final String dbUserName = "root";
    private static final String dbPassword = "haojun";


    public CommentDAO() throws Exception {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new Exception("Error creating driver");
        }
    }

    public List<Comment> getAllComments() throws ClassNotFoundException, SQLException {
        List<Comment> commentList = new LinkedList<Comment>();


        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String postSearchQuery = "select * from PostComment order by commentDate desc";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(postSearchQuery);
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setComment_id(resultSet.getInt("comment_id"));
                comment.setCommentBody(resultSet.getString("commentBody"));
                //comment.setPost_id(resultSet.getInt("postId"));
                comment.setCommentDate(resultSet.getString("commentDate"));

                commentList.add(comment);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return commentList;
    }

    public List<Comment> getCommentsByPostId(int post_id) throws SQLException {
        List<Comment> commentList= new LinkedList<Comment>();

        return commentList;
    }

    public boolean postNewComment(String commentBody) throws SQLException {
        boolean published = false;
        //int userId;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String commentDate = dateFormat.format(date);
        try {
            //UserDAO userDAO = new UserDAO();
            //userId = userDAO.getUserIdByUserName(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
            try{
                String idCheckQuery = "select * from PostComment where comment_id=?"; //check if this id number has been token
                String insertQuery =
                        "insert into PostComment (comment_id, commentBody, commentDate)" +
                                "values(?,?,?)";   //insert a record to user table
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
                preparedStatement.setString(2, commentBody);
                preparedStatement.setString(3, commentDate);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
                published = true;
            }catch (Exception e){
                e.printStackTrace();
            }

        return published;
    }

    public boolean deleteComment(int comment_id) throws SQLException{
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);

        try{
            String deleteQuery = "delete from PostComment where comment_id = ?";
            PreparedStatement pstDelete = connection.prepareStatement(deleteQuery);
            pstDelete.setInt(1, comment_id);
            pstDelete.executeUpdate();
            pstDelete.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return false;
    }

}
