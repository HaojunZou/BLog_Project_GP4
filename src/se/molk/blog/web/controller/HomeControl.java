package se.molk.blog.web.controller;

import se.molk.blog.dao.PostDAO;
import se.molk.blog.domain.Post;
import se.molk.blog.service.PostService;
import se.molk.blog.utils.TextFilter;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/HomeControl")
public class HomeControl extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        PostDAO post = null;

        try {
            post = new PostDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextFilter textFilter = new TextFilter();
        PostService postService = new PostService(post);
        HttpSession session = request.getSession();
        List<Post> usersPosts;

        String title = textFilter.filterHtml(request.getParameter("title"));
        String body = request.getParameter("body");
        String blogUserName = (String) session.getAttribute("blogUserName");
        String showUsersBlog = request.getParameter("showUsersBlog");
        String sendBlog = request.getParameter("sendBlog");
        int postUserId = Integer.parseInt(request.getParameter("author"));
        //String category = request.getParameter("category");
        //String author = request.getParameter("author");
        try {
            if("Show this person's blog".equals(showUsersBlog)){
                if(postUserId!=0){
                    usersPosts = postService.getPostsByUserId(postUserId);
                    request.setAttribute("usersPosts", usersPosts);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
                    dispatcher.forward(request, response);
                }else {
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('This user doesn't exist');" +
                                    "history.go(-1)" +
                                    "</script>"
                    );
                }
            }

            if("Send This Blog".equals(sendBlog)){
                if(blogUserName != null){
                    if(postService.publishNewPost(title, body, blogUserName)){
                        response.sendRedirect("/blog/home.jsp");
                    }else {
                        out.print(
                                "<script type='text/javascript'>" +
                                        "window.alert('You can't post blog');" +
                                        "history.go(-1)" +
                                        "</script>"
                        );
                    }
                }else{
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('You need to log in to write a blog');" +
                                    "history.go(-1)" +
                                    "</script>"
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

