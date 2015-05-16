package se.molk.blog.web.controller;

import se.molk.blog.dao.UserDAO;
import se.molk.blog.service.UserService;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UserUpdateProfileControl")
public class UserUpdateProfileControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UserDAO user = null;
        try {
            user = new UserDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserService userService = new UserService(user);
        HttpSession session = request.getSession();
        String currentUserName = (String) session.getAttribute("userName");
        String realName = request.getParameter("realName");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String country = request.getParameter("country");

        try {
            boolean profileUpdated = userService.updateUserInfo(currentUserName, realName, gender, birthday, country);
            if(profileUpdated){
                response.sendRedirect("/blog/user_profile_executed.html");
            }else {
                out.print(
                        "<script type='text/javascript'>" +
                                "window.alert('Ops, something goes wrong!');" +
                                "history.go(-1)" +
                                "</script>"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
