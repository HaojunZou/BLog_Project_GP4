package GP4;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Optimus on 2015-05-11.
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> resultList = new ArrayList<String>();

        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            String url = "jdbc:mysql://localhost/blog";
            String dbUserName = "root";
            String dbPassword = "admin";
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);

            String searchQuery = "select username from users";
            PreparedStatement pstSearch = connection.prepareStatement(searchQuery);
            ResultSet resultSearch = pstSearch.executeQuery();
            while (resultSearch.next()) {
                resultList.add(resultSearch.getString(1));
            }
            request.setAttribute("resultUserNames", resultList);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
            dispatcher.forward(request, response);
            pstSearch.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
