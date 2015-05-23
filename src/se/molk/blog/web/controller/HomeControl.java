package se.molk.blog.web.controller;

import se.molk.blog.dao.PostDAO;
import se.molk.blog.service.PostService;
import se.molk.blog.utils.TextFilter;

import java.io.*;
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

        String title = textFilter.filterHtml(request.getParameter("title"));
        String body = request.getParameter("body");
        String blogUserName = (String) session.getAttribute("blogUserName");
        //String category = request.getParameter("category");
        //String author = request.getParameter("author");
        try {
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

            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
            //dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

