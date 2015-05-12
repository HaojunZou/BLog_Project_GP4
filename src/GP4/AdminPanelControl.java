package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/AdminPanelControl")
public class AdminPanelControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> userList = new ArrayList<String>();

        try{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            String url = "jdbc:mysql://localhost/blog";
            String dbUserName = "root";
            String dbPassword = "haojun";
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection connection= DriverManager.getConnection(url, dbUserName, dbPassword);

            String searchValue = request.getParameter("searchRecord");
            String searchQuery = "select * from users where userName like ? or userPassword like ?";
            PreparedStatement pstSearch = connection.prepareStatement(searchQuery);
            pstSearch.setString(1, "%" + searchValue + "%");
            pstSearch.setString(2, "%" + searchValue + "%");
            ResultSet resultSearch = pstSearch.executeQuery();
            while(resultSearch.next()) {
                userList.add(resultSearch.getString(1));
                if (resultSearch.getString(2).equals("2")) {
                    userList.add("Normal User");
                }
                else if (resultSearch.getString(2).equals("1")) {
                    userList.add("Administrator");
                }
                else{
                    userList.add("Unknown");
                }
                userList.add(resultSearch.getString(3));
                userList.add(resultSearch.getString(4));
                userList.add(resultSearch.getString(5));
                userList.add(resultSearch.getString(6));
                userList.add(resultSearch.getString(7));
                userList.add(resultSearch.getString(8));
                userList.add(resultSearch.getString(9));
                userList.add(resultSearch.getString(10));
            }
            request.setAttribute("resultUserList", userList);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/admin_panel_result.jsp");
            dispatcher.forward(request, response);
            pstSearch.close();
            connection.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
