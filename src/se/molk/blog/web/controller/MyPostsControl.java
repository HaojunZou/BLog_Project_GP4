package se.molk.blog.web.controller;

import se.molk.blog.dao.PostDAO;
import se.molk.blog.domain.Post;
import se.molk.blog.domain.User;
import se.molk.blog.service.PostService;
import se.molk.blog.utils.TextFilter;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/MyPostsControl")
public class MyPostsControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Post selectedPost;
        PostDAO post = null;
        try {
            post = new PostDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextFilter textFilter = new TextFilter();
        PostService postService = new PostService(post);

        String deletePostAction = request.getParameter("deletePostAction");
        String searchPostAction = request.getParameter("searchPostAction");
        String updatePostAction = request.getParameter("updatePostAction");
        String deletePostRecord = request.getParameter("deletePostRecord");
        String searchPostId = request.getParameter("searchPostId");
        String thisPostId = request.getParameter("thisPostId");
        String newPostTitle = textFilter.filterHtml(request.getParameter("newPostTitle"));
        String newPostBody = textFilter.filterHtml(request.getParameter("newPostBody"));

        try{
            // delete post
            if("Delete Post".equals(deletePostAction)){
                int post_id;
                try
                {
                    post_id = Integer.parseInt(deletePostRecord);
                    boolean postDeleted = postService.deleteAPost(post_id);
                    if (postDeleted){
                        response.sendRedirect("/blog/user_profile_updated.html");
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

            // get a post
            if("Get This Post".equals(searchPostAction)){
                selectedPost = postService.getPostById(Integer.parseInt(searchPostId));
                if(selectedPost != null) {
                    request.setAttribute("selectedPost", selectedPost);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/my_posts.jsp");
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
                    response.sendRedirect("/blog/user_profile_updated.html");
                }else{
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('Update failed！');" +
                                    "history.go(-1);" +
                                    "</script>"
                    );
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
