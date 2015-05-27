package se.molk.blog.se.molk.blog.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import se.molk.blog.dao.CommentDAO;
import se.molk.blog.dao.UserDAO;
import se.molk.blog.domain.Comment;
import se.molk.blog.domain.User;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


public class CommentDAOTest {

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
        } catch (Exception e){}
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
        } catch (Exception e){}
    }





}