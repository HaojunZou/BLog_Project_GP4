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

@WebServlet("/UserSignUpControl")
public class UserSignUpControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        TextFilter textFilter = new TextFilter();

        UserDAO user = null;
        try {
            user = new UserDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserService userService = new UserService(user);
        String userName = textFilter.filterHtml(request.getParameter("userName"));
        String email = request.getParameter("email");
        String userPassword = textFilter.filterHtml(request.getParameter("userPassword"));
        String realName = textFilter.filterHtml(request.getParameter("realName"));
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String country = request.getParameter("country");

        List<User> userList;

        try {
            boolean userAdded = userService.addUser(userName, email, userPassword, realName, gender, birthday, country);
            if(userAdded && userService.isLogged(userName)){
                userList = userService.getAllUsers();
                HttpSession session = request.getSession();
                session.setAttribute("currentUserName", userName);
                session.setAttribute("userList", userList);
                response.sendRedirect("/blog/home.jsp");
            }else{
                out.print(
                        "<script type='text/javascript'>" +
                                "window.alert('Registration failed！');" +
                                "history.go(-1);" +
                                "</script>"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp");
        dispatcher.forward(request, response);
    }
}
