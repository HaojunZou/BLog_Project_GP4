package GP4;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ControllerServlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        response.getWriter().write("Post:\n" + "Name: " + name + "\n"+ "Password :" + password);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setName("Haojun");
        user.setEmail("1@haojun.com");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/blog.html");
        request.setAttribute("user", user); // Request scope
        dispatcher.forward(request, response);
    }
}
