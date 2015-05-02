package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


@WebServlet("/home.html")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Home() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //User user = new User();
        //user.setName("demo");
        //user.setEmail("demo@demo.com");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.html");
        //request.setAttribute("user", user); // Request scope
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/blog/home.html");
    /*
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        response.getWriter().write("Post:\n" + "Name: " + name + "\n" + "Password: " + password);
    */
    }

}
