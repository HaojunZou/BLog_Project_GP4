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

        String un = request.getParameter("un");
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");

        String driver = "org.gjt.mm.mysql.Driver";
        String url = "jdbc:mysql://localhost/blog";
        String userName = "root";
        String password = "haojun";
        int numberOfRowsReturned;
        String checkExistQuery = "select * from users where un=? or email=?"; //check if user or email is exist
        String checkQuery = "select * from users";    //check the users table
        String insertQuery = "insert into users (id, un, email, pwd) values(?,?,?,?)";   //insert a record to user table

        try {
            Class.forName(driver);  //load driver
            Connection connection = DriverManager.getConnection(url, userName, password);   //set connection

            PreparedStatement preparedStatementExist = connection.prepareStatement(checkExistQuery);
            preparedStatementExist.setString(1, un);
            preparedStatementExist.setString(2, email);
            ResultSet resultSetExist = preparedStatementExist.executeQuery();

            if((!resultSetExist.next()) && (un.length()!=0) && (email.length()!=0)){
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(checkQuery); //execute query
                if (resultSet.next()) { //if there's record
                    resultSet.last();   //then set resultSet to the last one
                    numberOfRowsReturned = resultSet.getRow();  //to get the number of rows
                } else {
                    numberOfRowsReturned = 0;   //otherwise set row to 0
                }
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, Integer.toString(numberOfRowsReturned+1));
                preparedStatement.setString(2, un);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, pwd);
                preparedStatement.executeUpdate();
                response.sendRedirect("/blog/home.html");
                preparedStatement.close();
            }
            else{
                out.print("<script>alert('User Name or Password has already been registered!')</script>"); //give warning, but... not working???
                response.sendRedirect("/blog/signup.jsp");   //stay at the same page
            }
            preparedStatementExist.close();
            connection.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/signupconfirm.html");
        dispatcher.forward(request, response);
    }
}
