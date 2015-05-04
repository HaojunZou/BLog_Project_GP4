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
        TextFilter textFilter = new TextFilter();
        response.sendRedirect("/blog/home.html");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(textFilter.filterHtml(request.getParameter("blogText") + "<br/>"));
    }

}
