package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/signup.jsp")
public class SignUp extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SignUp() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/blog/signup.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp");
        dispatcher.forward(request, response);
    }
}
