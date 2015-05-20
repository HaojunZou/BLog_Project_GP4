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
        String deleteAction = request.getParameter("deleteAction");
        String updateAction = request.getParameter("updateAction");
        String deleteValue = request.getParameter("deleteRecord");
        String userName = request.getParameter("userName");
        String newUserName = request.getParameter("newUserName");
        String newPassword = request.getParameter("newPassword");
        String newEmail = request.getParameter("newEmail");
        String newRealName = request.getParameter("newRealName");
        String newGender = request.getParameter("newGender");
        String newBirthday = request.getParameter("newBirthday");
        String newCountry = request.getParameter("newCountry");

        try {
            if("Delete".equals(deleteAction)){
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
            }
            if("Update".equals(updateAction)){
                String updateUserInfo = userService.adminUpdateUserInfo(userName, newUserName, newEmail, newPassword, newRealName, newGender, newBirthday, newCountry);
                if(updateUserInfo.equals("updateSuccessful")){
                    response.sendRedirect("/blog/admin_panel_executed.html");
                }
                if(updateUserInfo.equals("updateFailed")) {
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('Update failed！');" +
                                    "history.go(-1);" +
                                    "</script>"
                    );
                }
                if(updateUserInfo.equals("userNotExist")) {
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('User not exist！');" +
                                    "history.go(-1);" +
                                    "</script>"
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
