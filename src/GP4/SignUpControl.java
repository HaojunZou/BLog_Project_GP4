package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/SignUpControl")
public class SignUpControl extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        User user = new User();
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String userPassword = request.getParameter("userPassword");
        String realName = request.getParameter("realName");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String country = request.getParameter("country");
        String accept = request.getParameter("accept");
        user.setUserType(2);
        user.setUserName(userName);
        user.setEmail(email);
        user.setUserPassword(userPassword);
        user.setRealName(realName);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setCountry(country);

        String driver = "org.gjt.mm.mysql.Driver";
        String url = "jdbc:mysql://localhost/blog";
        String dbUserName = "root";
        String dbPassword = "haojun";
        int numberOfRowsReturned;
        String checkExistQuery = "select * from users where userName=? or email=?"; //check if user or email is exist
        String checkQuery = "select * from users";    //check the users table
        String insertQuery =
                "insert into users (id, userType, userName, email, userPassword, realName, gender, birthday, country)" +
                "values(?,?,?,?,?,?,?,?,?)";   //insert a record to user table

        try {
            Class.forName(driver);  //load driver
            Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);   //set connection

            PreparedStatement preparedStatementExist = connection.prepareStatement(checkExistQuery);
            preparedStatementExist.setString(1, user.getUserName());
            preparedStatementExist.setString(2, user.getEmail());
            ResultSet resultSetExist = preparedStatementExist.executeQuery();

            if(!resultSetExist.next() && accept.equals("accept")){
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
                preparedStatement.setString(2, Integer.toString(user.getUserType()));
                preparedStatement.setString(3, user.getUserName());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getUserPassword());
                preparedStatement.setString(6, user.getRealName());
                preparedStatement.setString(7, user.getGender());
                preparedStatement.setString(8, user.getBirthday());
                preparedStatement.setString(9, user.getCountry());
                preparedStatement.executeUpdate();
                preparedStatement.close();
                response.sendRedirect("/blog/home.html");
            }
            if(accept.equals("decline")){
                response.sendRedirect("/blog/signup.jsp");
            }
            else{
                out.print(
                    "<script type='text/javascript'>" +
                        "window.alert('User name or email has already been registered!');" +
                        "history.go(-1)" +
                    "</script>"
                ); //give warning
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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp");
        dispatcher.forward(request, response);
    }
}
