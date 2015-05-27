package se.molk.blog.mocktest;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import se.molk.blog.dao.CommentDAO;
import se.molk.blog.dao.PostDAO;
import se.molk.blog.dao.UserDAO;
import se.molk.blog.domain.Comment;
import se.molk.blog.domain.Post;
import se.molk.blog.domain.User;

import java.util.LinkedList;
import java.util.List;


public class DAOTest {


    @Test
    public void should_return_a_list_of_comments() {
        try {
            CommentDAO commentDAO = (CommentDAO) Mockito.mock(CommentDAO.class);
            LinkedList listOfComments = new LinkedList();
            Comment comment = new Comment();
            listOfComments.add(comment);
            Mockito.when(commentDAO.getAllComments()).thenReturn(listOfComments);
            List list = commentDAO.getAllComments();
            Assert.assertEquals(1, list.size());
        } catch (Exception e) {
        }
    }

    @Test
    public void should_return_a_list_of_users() {
        try {
            UserDAO userDAO = (UserDAO) Mockito.mock(UserDAO.class);
            LinkedList listOfUsers = new LinkedList();
            User user = new User();
            listOfUsers.add(user);
            Mockito.when(userDAO.getAllUsers()).thenReturn(listOfUsers);
            List list = userDAO.getAllUsers();
            Assert.assertEquals(1, list.size());
        } catch (Exception e) {
        }
    }

    @Test
    public void should_return_number_of_posts() {
        try {
            PostDAO postDAO = (PostDAO) Mockito.mock(PostDAO.class);
            LinkedList listOfPosts = new LinkedList();
            Post post = new Post();
            listOfPosts.add(post);
            listOfPosts.add(post);
            Mockito.when(postDAO.getAllPosts()).thenReturn(listOfPosts);
            List list = postDAO.getAllPosts();
            Assert.assertEquals(2, list.size());
        } catch (Exception e) {
        }
    }
}

