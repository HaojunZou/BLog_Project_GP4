package se.molk.blog.web.UI;

import se.molk.blog.dao.PostDAO;
import se.molk.blog.dao.UserDAO;
import se.molk.blog.domain.Post;
import se.molk.blog.domain.User;
import se.molk.blog.service.PostService;
import se.molk.blog.service.UserService;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/main.jsp")
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Main() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO user = null;
        PostDAO post = null;
        try {
            user = new UserDAO();
            post = new PostDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserService userService = new UserService(user);
        PostService postService = new PostService(post);
        List<User> users;
        List<Post> posts;
        try {
            users = userService.getAllUsers();
            posts = postService.getAllPosts();
            request.setAttribute("posts", posts);
            request.setAttribute("users", users);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/main.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
