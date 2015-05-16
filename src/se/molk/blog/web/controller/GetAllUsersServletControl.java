package se.molk.blog.web.controller;

import se.molk.blog.dao.UserDAO;
import se.molk.blog.domain.User;
import se.molk.blog.service.UserService;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UserServletControl")
public class GetAllUsersServletControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public GetAllUsersServletControl() {
        super();
    }

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
            request.setAttribute("users", users);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/test_get_all_users.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
