package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/AdminPanelControl")
public class AdminPanelControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            String url = "jdbc:mysql://localhost/blog";
            String dbUserName = "root";
            String dbPassword = "haojun";
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection connection= DriverManager.getConnection(url, dbUserName, dbPassword);

            String searchValue = request.getParameter("searchRecord");

            if(!searchValue.equals("")){
                String searchQuery = "select * from users where userName like ? or userPassword like ?";
                PreparedStatement pstSearch = connection.prepareStatement(searchQuery);
                pstSearch.setString(1, "%" + searchValue + "%");
                pstSearch.setString(2, "%" + searchValue + "%");
                ResultSet resultSearch = pstSearch.executeQuery();
                while (resultSearch.next()) {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/adminpanelresult.jsp");
                    request.setAttribute("resultId", resultSearch.getString(1));
                    request.setAttribute("resultUserType", resultSearch.getString(2));
                    request.setAttribute("resultUserName", resultSearch.getString(3));
                    request.setAttribute("resultEmail", resultSearch.getString(4));
                    request.setAttribute("resultUserPassword", resultSearch.getString(5));
                    request.setAttribute("resultRealName", resultSearch.getString(6));
                    request.setAttribute("resultGender", resultSearch.getString(7));
                    request.setAttribute("resultBirthday", resultSearch.getString(8));
                    request.setAttribute("resultCountry", resultSearch.getString(9));
                    dispatcher.forward(request, response);
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
