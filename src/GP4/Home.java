package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(filterHtml(request.getParameter("blogText") + "<br/>"));
    /*
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        response.getWriter().write("Post:\n" + "Name: " + name + "\n" + "Password: " + password);
    */
    }

    public String filterHtml(String value){ //html filter to prevent special characters in text
        value = value.replaceAll("&", "&amp;");
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        value = value.replaceAll(" ", "&nbsp;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("\"", "&quot;");
        value = value.replaceAll("\n", "<br/>;");
        return value;
    }

}
