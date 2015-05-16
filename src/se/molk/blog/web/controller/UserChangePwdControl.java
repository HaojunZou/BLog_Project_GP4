package se.molk.blog.web.controller;

import se.molk.blog.dao.UserDAO;
import se.molk.blog.service.UserService;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UserChangePwdControl")
public class UserChangePwdControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public UserChangePwdControl() {
        super();
    }
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
        String oldUserPassword = request.getParameter("oldUserPassword");
        String newUserPassword = request.getParameter("newUserPassword");

        try {
            boolean passwordChanged = userService.changePassword(currentUserName, oldUserPassword, newUserPassword);
            if (passwordChanged){
                response.sendRedirect("/blog/user_profile_executed.html");
            }else{
                out.print(
                        "<script type='text/javascript'>" +
                                "window.alert('Password is incorrect');" +
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
