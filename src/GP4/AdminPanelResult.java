package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/adminpanelresult.jsp")
public class AdminPanelResult extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/adminpanelresult.jsp");
        dispatcher.forward(request, response);
    }
}
