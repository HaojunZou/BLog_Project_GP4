package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/signupconfirm.html")
public class SignUpConfirm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int id;
        User user = new User();
        String un = request.getParameter("un");
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        user.setUn(un);
        user.setEmail(email);
        user.setPwd(pwd);

        String driver = "org.gjt.mm.mysql.Driver";
        String url = "jdbc:mysql://localhost/blog";
        String userName = "root";
        String password = "haojun";
        int numberOfRowsReturned;
        String checkQuery = "select * from users";    //check the users table
        String insertQuery = "insert into users (id, un, email, pwd) values(?,?,?,?)";   //insert a record to user table

        try {
            Class.forName(driver);  //load driver
            Connection connection = DriverManager.getConnection(url, userName, password);   //set connection
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(checkQuery);

            if (resultSet.next()) { //if there's record
                resultSet.last();   //then set resultSet to the last one
                numberOfRowsReturned = resultSet.getRow();  //to get the number of rows
            } else {
                numberOfRowsReturned = 0;   //otherwise set row to 0
            }

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, Integer.toString(numberOfRowsReturned+1));
            preparedStatement.setString(2, user.getUn());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPwd());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/blog/signupconfirm.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/signupconfirm.html");
        dispatcher.forward(request, response);
    }
}
