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
        ArrayList<String> id = new ArrayList<String>();
        ArrayList<String> userType = new ArrayList<String>();
        ArrayList<String> userName = new ArrayList<String>();
        ArrayList<String> email = new ArrayList<String>();
        ArrayList<String> userPassword = new ArrayList<String>();
        ArrayList<String> realName = new ArrayList<String>();
        ArrayList<String> gender = new ArrayList<String>();
        ArrayList<String> birthday = new ArrayList<String>();
        ArrayList<String> country = new ArrayList<String>();
        ArrayList<String> userStatus = new ArrayList<String>();

        try{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            String url = "jdbc:mysql://localhost/blog";
            String dbUserName = "root";
            String dbPassword = "admin";
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection connection= DriverManager.getConnection(url, dbUserName, dbPassword);

            String searchValue = request.getParameter("searchRecord");
            String searchQuery = "select * from users where userName like ? or userPassword like ?";
            PreparedStatement pstSearch = connection.prepareStatement(searchQuery);
            pstSearch.setString(1, "%" + searchValue + "%");
            pstSearch.setString(2, "%" + searchValue + "%");
            ResultSet resultSearch = pstSearch.executeQuery();
            while(resultSearch.next()) {
                id.add(resultSearch.getString(1));
                if (resultSearch.getString(2).equals("1")) {
                    userType.add("Administrator");
                }
                if (resultSearch.getString(2).equals("2")) {
                    userType.add("Normal User");
                }
                else{
                    userType.add("Unknown");
                }
                userName.add(resultSearch.getString(3));
                email.add(resultSearch.getString(4));
                userPassword.add(resultSearch.getString(5));
                realName.add(resultSearch.getString(6));
                gender.add(resultSearch.getString(7));
                birthday.add(resultSearch.getString(8));
                country.add(resultSearch.getString(9));
                userStatus.add(resultSearch.getString(10));
            }
            request.setAttribute("resultId", id);
            request.setAttribute("resultUserType", userType);
            request.setAttribute("resultUserName", userName);
            request.setAttribute("resultEmail", email);
            request.setAttribute("resultUserPassword", userPassword);
            request.setAttribute("resultRealName", realName);
            request.setAttribute("resultGender", gender);
            request.setAttribute("resultBirthday", birthday);
            request.setAttribute("resultCountry", country);
            request.setAttribute("resultStatus", userStatus);
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
