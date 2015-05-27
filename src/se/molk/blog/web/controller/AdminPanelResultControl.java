package se.molk.blog.web.controller;

import se.molk.blog.dao.PostDAO;
import se.molk.blog.dao.UserDAO;
import se.molk.blog.domain.Post;
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
        Post selectedPost = null;
        try {
            user = new UserDAO();
            post = new PostDAO();
            selectedPost = new Post();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextFilter textFilter = new TextFilter();
        UserService userService = new UserService(user);
        PostService postService = new PostService(post);
        String deleteUserAction = request.getParameter("deleteUserAction");
        String updateUserAction = request.getParameter("updateUserAction");
        String deletePostAction = request.getParameter("deletePostAction");
        String searchPostAction = request.getParameter("searchPostAction");
        String updatePostAction = request.getParameter("updatePostAction");
        String searchPostId = request.getParameter("searchPostId");
        String userName = textFilter.filterHtml(request.getParameter("userName"));
        String newUserName = textFilter.filterHtml(request.getParameter("newUserName"));
        String newPassword = textFilter.filterHtml(request.getParameter("newPassword"));
        String newEmail = request.getParameter("newEmail");
        String newRealName = textFilter.filterHtml(request.getParameter("newRealName"));
        String newGender = request.getParameter("newGender");
        String newBirthday = request.getParameter("newBirthday");
        String newCountry = request.getParameter("newCountry");
        String thisPostId = request.getParameter("thisPostId");
        String newPostTitle = textFilter.filterHtml(request.getParameter("newPostTitle"));
        String newPostBody = textFilter.filterHtml(request.getParameter("newPostBody"));
        String deleteUserRecord = textFilter.filterHtml(request.getParameter("deleteUserRecord"));
        String deletePostRecord = request.getParameter("deletePostRecord");

        try {
            // delete user
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

            // delete post
            if("Delete Post".equals(deletePostAction)){
                int post_id;
                try
                {
                    post_id = Integer.parseInt(deletePostRecord);
                    boolean postDeleted = postService.deleteAPost(post_id);
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
                catch(Exception e)
                {
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('Please enter a number！');" +
                                    "history.go(-1);" +
                                    "</script>"
                    );
                }
            }

            // update user
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

            // get a post
            if("Get This Post".equals(searchPostAction)){
                selectedPost = postService.getPostById(Integer.parseInt(searchPostId));
                if(selectedPost != null) {
                    request.setAttribute("selectedPostId", selectedPost.getId());
                    request.setAttribute("selectedPostTitle", selectedPost.getTitle());
                    request.setAttribute("selectedPostBody", selectedPost.getBody());
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/admin_panel_result.jsp");
                    dispatcher.forward(request, response);
                }else{
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('No matched data！');" +
                                    "history.go(-1);" +
                                    "</script>"
                    );
                }
            }

            // update a post
            if("Update This Post".equals(updatePostAction)){
                if(postService.updateAPost(Integer.parseInt(thisPostId), newPostTitle, newPostBody)){
                    response.sendRedirect("/blog/admin_panel_executed.html");
                }else{
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('Update failed！');" +
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
