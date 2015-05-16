package se.molk.blog.service;


import se.molk.blog.dao.PostDAO;

public class PostService {
    private PostDAO postDAO;

    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }
}
