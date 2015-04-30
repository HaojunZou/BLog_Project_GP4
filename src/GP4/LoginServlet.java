package GP4;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String page = "/jsp/index.jsp";
        User user = null;

        if(password == "ok"){
            user = new User();
            user.setName("haojun");
            user.setEmail("1@haojun.com");

            page = "/WEB-INF/blog.html";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        request.setAttribute("user", user); // Request scope
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
