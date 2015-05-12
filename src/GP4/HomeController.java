package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> resultList = new ArrayList<String>();

        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            String url = "jdbc:mysql://localhost/blog";
            String dbUserName = "root";
            String dbPassword = "haojun";
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);

            String searchQuery = "select userName from users";
            PreparedStatement pstSearch = connection.prepareStatement(searchQuery);
            ResultSet resultSearch = pstSearch.executeQuery();
            while (resultSearch.next()) {
                resultList.add(resultSearch.getString(1));
            }
            request.setAttribute("resultUserNames", resultList);
            pstSearch.close();
            connection.close();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

