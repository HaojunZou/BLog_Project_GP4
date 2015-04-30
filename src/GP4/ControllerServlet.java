package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ControllerServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setName("demo");
        user.setEmail("demo@demo.com");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/index.jsp");
        request.setAttribute("user", user); // Request scope
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        response.getWriter().write("Post:\n" + "Name: " + name + "\n" + "Password: " + password);
    }

}
