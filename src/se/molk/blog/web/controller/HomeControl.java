package se.molk.blog.web.controller;

import se.molk.blog.dao.PostDAO;
import se.molk.blog.dao.UserDAO;
import se.molk.blog.domain.Post;
import se.molk.blog.domain.User;
import se.molk.blog.service.PostService;
import se.molk.blog.service.UserService;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/HomeControl")
public class HomeControl extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PostDAO post = null;
        try {
            post = new PostDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }

        PostService postService = new PostService(post);

        String title = request.getParameter("title");
        String body = request.getParameter("body");
        //String category = request.getParameter("category");
        //String author = (String) request.getAttribute("currentUserName");
        try {
            postService.publishNewPost(title, body);
            response.sendRedirect("/blog/home.jsp");
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

