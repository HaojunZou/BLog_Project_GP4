package se.molk.blog.web.controller;

import se.molk.blog.dao.UserDAO;
import se.molk.blog.service.UserService;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UserSignUpControl")
public class UserSignUpControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDAO user = null;
        try {
            user = new UserDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserService userService = new UserService(user);
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String userPassword = request.getParameter("userPassword");
        String realName = request.getParameter("realName");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String country = request.getParameter("country");

        try {
            userService.addUser(userName, email, userPassword, realName, gender, birthday, country);

            response.sendRedirect("/blog/home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp");
        dispatcher.forward(request, response);
    }
}
