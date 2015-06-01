package se.molk.blog.web.controller;

import se.molk.blog.dao.PostDAO;
import se.molk.blog.dao.UserDAO;
import se.molk.blog.domain.Post;
import se.molk.blog.domain.User;
import se.molk.blog.service.PostService;
import se.molk.blog.service.UserService;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UserProfileControl")
public class UserProfileControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

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
        List<Post> posts;

        try {
            HttpSession session = request.getSession();
            String currentUserName = (String) session.getAttribute("userName");
            session.setAttribute("currentUserName", currentUserName);
            String postSearchAction = request.getParameter("postSearchAction");
            int user_id = userService.getUserIdByUserName(currentUserName);

            if("My Posts".equals(postSearchAction)){
                posts = postService.getPostsByUserId(user_id);
                request.setAttribute("posts", posts);
                if(posts != null){
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/my_posts.jsp");
                    dispatcher.forward(request, response);
                }else{
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('You don't have any posts！');" +
                                    "history.go(-1);" +
                                    "</script>"
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
