package se.molk.blog.web.controller;

import se.molk.blog.dao.UserDAO;
import se.molk.blog.service.UserService;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/AdminPanelResultControl")
public class AdminPanelResultControl extends HttpServlet {
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
            String deleteValue = request.getParameter("deleteRecord");

            try {
                boolean userDeleted = userService.deleteAnUser(deleteValue);
                if (userDeleted){
                    response.sendRedirect("/blog/admin_panel_executed.html");
                }else {
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('No such data！');" +
                                    "history.go(-1);" +
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
