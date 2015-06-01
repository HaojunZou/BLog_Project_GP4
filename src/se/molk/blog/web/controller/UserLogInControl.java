package se.molk.blog.web.controller;

import se.molk.blog.dao.UserDAO;
import se.molk.blog.domain.User;
import se.molk.blog.service.UserService;
import se.molk.blog.utils.TextFilter;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UserLogInControl")
public class UserLogInControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public UserLogInControl() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        UserDAO user = null;
        try {
            user = new UserDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextFilter textFilter = new TextFilter();
        UserService userService = new UserService(user);
        List<User> userList;
        String userName = textFilter.filterHtml(request.getParameter("userName"));
        String userPassword = textFilter.filterHtml(request.getParameter("userPassword"));

        try {
            int userType = userService.logIn(userName, userPassword);
            if (userType == 2 && userService.isLogged(userName)){
                userList = userService.getAllUsers();
                HttpSession session = request.getSession();
                session.setAttribute("currentUserName", userName);
                session.setAttribute("userList", userList);
                response.sendRedirect("/blog/home.jsp");
            }else if (userType == 1){
                response.sendRedirect("/blog/admin_panel.jsp");
            }else if (userType == 3){
                out.print(
                        "<script type='text/javascript'>" +
                                "window.alert('User name or password is incorrect');" +
                                "history.go(-1)" +
                                "</script>"
                );
            }
            else{
                out.print(
                        "<script type='text/javascript'>" +
                                "window.alert('Sorry! Incorrect user name or password');" +
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
