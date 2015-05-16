package se.molk.blog.web.controller;

import se.molk.blog.dao.UserDAO;
import se.molk.blog.domain.User;
import se.molk.blog.service.UserService;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/HomeControl")
public class HomeControl extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO user = null;
        try {
            user = new UserDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserService userService = new UserService(user);
        List<User> users;
        try {
            users = userService.getAllUsers();
            HttpSession session = request.getSession();
            session.setAttribute("users", users);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

