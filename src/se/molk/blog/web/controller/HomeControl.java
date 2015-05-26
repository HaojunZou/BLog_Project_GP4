package se.molk.blog.web.controller;

import se.molk.blog.dao.CommentDAO;
import se.molk.blog.dao.PostDAO;
import se.molk.blog.domain.Comment;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/HomeControl")
public class HomeControl extends HttpServlet {

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
        HttpSession session = request.getSession();
        List<Post> posts;
        List<Comment> comments;

        String fuzzySearchAction = request.getParameter("fuzzySearchAction");
        String sentCommentAction = request.getParameter("sentCommentAction");
        String sendBlogAction = request.getParameter("sendBlogAction");
        String fuzzySearchBlog = request.getParameter("fuzzySearchBlog");
        String blogTitle = textFilter.filterHtml(request.getParameter("blogTitle"));
        String blogBody = request.getParameter("blogBody");
        String blogUserName = (String) session.getAttribute("blogUserName");
        String commentBody = textFilter.filterHtml(request.getParameter("commentBody"));

        //String showUsersBlog = request.getParameter("showUsersBlog");
        //int postUserId = Integer.parseInt(request.getParameter("author"));
        //String category = request.getParameter("category");
        //String author = request.getParameter("author");
        try {
            comments = commentService.getAllComments();
            request.setAttribute("comments", comments);

            // search action
            if("Fuzzy Search".equals(fuzzySearchAction)){
                posts = postService.getPostsByFuzzySearch(fuzzySearchBlog);
                request.setAttribute("posts", posts);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
                dispatcher.forward(request, response);
            }

            // send blog action
            if("Send This Blog".equals(sendBlogAction)){
                if(blogUserName != null){
                    if(postService.publishNewPost(blogTitle, blogBody, blogUserName)){
                        response.sendRedirect("/blog/home.jsp");
                    }else {
                        out.print(
                                "<script type='text/javascript'>" +
                                        "window.alert('You can't post blog!');" +
                                        "history.go(-1)" +
                                        "</script>"
                        );
                    }
                }else{
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('You need to log in to write a blog!');" +
                                    "history.go(-1)" +
                                    "</script>"
                    );
                }
            }

            // send comment action
            if("Send Comment".equals(sentCommentAction)){
                if(commentBody.equals("")){
                    out.print(
                            "<script type='text/javascript'>" +
                                    "window.alert('Please enter a comment!');" +
                                    "history.go(-1)" +
                                    "</script>"
                    );
                }else{
                    if(commentService.postNewComment(commentBody)){
                        response.sendRedirect("/blog/home.jsp");
                    }else {
                        out.print(
                                "<script type='text/javascript'>" +
                                        "window.alert('You can't post comment!');" +
                                        "history.go(-1)" +
                                        "</script>"
                        );
                    }
                }
            }

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

