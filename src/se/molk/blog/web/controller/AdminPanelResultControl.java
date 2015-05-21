package se.molk.blog.web.controller;

import se.molk.blog.dao.PostDAO;
import se.molk.blog.dao.UserDAO;
import se.molk.blog.service.PostService;
import se.molk.blog.service.UserService;
import se.molk.blog.utils.TextFilter;

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
        PostDAO post = null;
        try {
            user = new UserDAO();
            post = new PostDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextFilter textFilter = new TextFilter();
        UserService userService = new UserService(user);
        PostService postService = new PostService(post);
        String deleteUserAction = textFilter.filterHtml(request.getParameter("deleteUserAction"));
        String updateUserAction = textFilter.filterHtml(request.getParameter("updateUserAction"));
        String deleteUserRecord = textFilter.filterHtml(request.getParameter("deleteUserRecord"));
        String deletePostAction = textFilter.filterHtml(request.getParameter("deletePostAction"));
        int deletePostRecord = Integer.parseInt(request.getParameter("deletePostRecord"));
        String userName = textFilter.filterHtml(request.getParameter("userName"));
        String newUserName = textFilter.filterHtml(request.getParameter("newUserName"));
        String newPassword = textFilter.filterHtml(request.getParameter("newPassword"));
        String newEmail = request.getParameter("newEmail");
        String newRealName = textFilter.filterHtml(request.getParameter("newRealName"));
        String newGender = request.getParameter("newGender");
        String newBirthday = request.getParameter("newBirthday");
        String newCountry = request.getParameter("newCountry");

        try {
            if("Delete User".equals(deleteUserAction)){
                boolean userDeleted = userService.deleteAnUser(deleteUserRecord);
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

            if("Delete Post".equals(deletePostAction)){
                boolean postDeleted = postService.deleteAPost(deletePostRecord);
                if (postDeleted){
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

            if("Update User".equals(updateUserAction)){
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
