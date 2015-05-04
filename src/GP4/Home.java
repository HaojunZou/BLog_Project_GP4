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

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.html");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/blog/home.html");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(filterHtml(request.getParameter("blogText") + "<br/>"));

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
