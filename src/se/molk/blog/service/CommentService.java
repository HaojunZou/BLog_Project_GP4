package se.molk.blog.service;

import se.molk.blog.dao.CommentDAO;
import se.molk.blog.domain.Comment;

import java.sql.SQLException;
import java.util.List;

public class CommentService {

    private CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public List<Comment> getAllComments() throws SQLException, ClassNotFoundException {
        return commentDAO.getAllComments();

    }

    public List<Comment> getCommentsByPostId(int post_id) throws SQLException {
        return commentDAO.getCommentsByPostId(post_id);
    }

    public boolean postNewComment(String commentBody) throws SQLException {
        return commentDAO.postNewComment(commentBody);
    }

    public boolean deleteComment(int comment_id) throws SQLException{
        return commentDAO.deleteComment(comment_id);
    }
}
