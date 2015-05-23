package se.molk.blog.service;


import se.molk.blog.dao.PostDAO;
import se.molk.blog.domain.Post;

import java.sql.SQLException;
import java.util.List;

public class PostService {
    private PostDAO postDAO;

    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public List<Post> getAllPosts() throws SQLException, ClassNotFoundException {
        return postDAO.getAllPosts();

    }

    public boolean publishNewPost(String title, String body, String author) throws SQLException {
        return postDAO.publishNewPost(title, body, author);
    }

    public List<Post> getPostsByTitle(String title) throws SQLException {
        return postDAO.getPostsByTitle(title);
    }

    public List<Post> getPostsByUserId(int userId) throws SQLException{
        return postDAO.getPostsByUserId(userId);
    }

    public boolean deleteAPost(int post_id) throws SQLException{
        return postDAO.deleteAPost(post_id);
    }
}
