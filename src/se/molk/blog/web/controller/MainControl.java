package se.molk.blog.web.controller;

import se.molk.blog.dao.CommentDAO;
import se.molk.blog.dao.PostDAO;
import se.molk.blog.domain.Post;
import se.molk.blog.service.CommentService;
import se.molk.blog.service.PostService;
import se.molk.blog.utils.TextFilter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/MainControl")
public class MainControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        PostDAO post = null;
        CommentDAO comment = null;
        try {
            post = new PostDAO();
            comment = new CommentDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextFilter textFilter = new TextFilter();
        PostService postService = new PostService(post);
        CommentService commentService = new CommentService(comment);
        List<Post> resultPosts;
        //List<Post> usersPosts;

        String commentPost = request.getParameter("commentPost");
        String commentBody = textFilter.filterHtml(request.getParameter("commentBody"));
        String fuzzySearchBlog = request.getParameter("fuzzySearchBlog");
        //String showUsersBlog = request.getParameter("showUsersBlog");
        //int postUserId = Integer.parseInt(request.getParameter("author"));
        //String category = request.getParameter("category");
        //String author = request.getParameter("author");
        try {
            /*
            if("Show this person's blog".equals(showUsersBlog)){
                if(postUserId!=0){
                    usersPosts = postService.getPostsByUserId(postUserId);
                    request.setAttribute("usersPosts", usersPosts);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
                    dispatcher.forward(request, response);
                }else {
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('This user doesn't exist');" +
                                    "history.go(-1)" +
                                    "</script>"
                    );
                }
            }
            */

            if(!fuzzySearchBlog.equals("")){
                resultPosts = postService.getPostsByFuzzySearch(fuzzySearchBlog);
                request.setAttribute("resultPosts", resultPosts);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/main.jsp");
                dispatcher.forward(request, response);
            }

            if("Send Comment".equals(commentPost)){
                commentService.postNewComment(commentBody);
                response.sendRedirect("/blog/main.jsp");
            }

            else {
                out.print(
                        "<script type='text/javascript'>" +
                                "history.go(-1)" +
                                "</script>"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
