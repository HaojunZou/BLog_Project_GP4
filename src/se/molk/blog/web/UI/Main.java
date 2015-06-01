package se.molk.blog.web.UI;

import se.molk.blog.dao.CommentDAO;
import se.molk.blog.dao.PostDAO;
import se.molk.blog.dao.UserDAO;
import se.molk.blog.domain.Comment;
import se.molk.blog.domain.Post;
import se.molk.blog.domain.User;
import se.molk.blog.service.CommentService;
import se.molk.blog.service.PostService;
import se.molk.blog.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/main.jsp")
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Main() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        UserDAO user = null;
        PostDAO post = null;
        CommentDAO comment = null;
        try {
            user = new UserDAO();
            post = new PostDAO();
            comment = new CommentDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserService userService = new UserService(user);
        PostService postService = new PostService(post);
        CommentService commentService = new CommentService(comment);
        List<User> users;
        List<Post> posts;
        List<Comment> comments;
        try {
            users = userService.getAllUsers();
            posts = postService.getAllPosts();
            comments = commentService.getAllComments();
            request.setAttribute("posts", posts);
            request.setAttribute("users", users);
            request.setAttribute("comments", comments);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/main.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
