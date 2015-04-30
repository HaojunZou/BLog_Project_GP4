package GP4;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginServlet() {
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String page = "/WEB-INF/index.jsp";
        User user = null;
        if(password == "ok") {
            user = new User();
            user.setName("arne");
            user.setEmail("arne@parallell.com");
            page = "/WEB-INF/loggedIn.jsp";
        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(page);
        request.setAttribute("user", user);
        dispatcher.forward(request, response);

/*
        response.setContentType("text/html;charset=uft-8"); //返回文件类型
        PrintWriter out = response.getWriter(); //输出流，通过http输出到html文件到浏览器
        out.println("<html>");
        out.println("<head>");
        out.println("<title>blog</title>");
        out.println("<body>");
        out.println(new Date());
        out.println("<br>");
        out.println("welcome to my first Servlet");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
*/
    }
}
