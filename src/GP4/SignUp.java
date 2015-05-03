package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/signup.jsp")
public class SignUp extends HttpServlet {
    int id;
    String un, email, pwd;
    private static final long serialVersionUID = 1L;
    public SignUp() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        User user = new User();

        un = request.getParameter(user.getUn());
        email = request.getParameter(user.getEmail());
        pwd = request.getParameter(user.getPwd());

        response.sendRedirect("/blog/signup.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp");
        dispatcher.forward(request, response);
    }
}
