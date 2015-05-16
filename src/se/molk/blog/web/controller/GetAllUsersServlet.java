package se.molk.blog.web.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/test_get_all_users.jsp")
public class GetAllUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public GetAllUsersServlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/test_get_all_users.jsp");
        dispatcher.forward(request, response);
    }
}
