package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/LogInControl")
public class LogInControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            User user = new User();
            String userName = request.getParameter("userName");
            String userPassword = request.getParameter("userPassword");
            user.setUserName(userName);
            user.setUserPassword(userPassword);

            String url = "jdbc:mysql://localhost/blog";
            String dbUserName = "root";
            String dbPassword = "admin";
            String query = "select * from users where userName=? and userPassword=?";    //check un and pwd column in table users
            String loggedQuery = "update users set userStatus = 'Logged' where userName = ? and userPassword = ?";
            Class.forName("org.gjt.mm.mysql.Driver");     //load driver
            Connection connection= DriverManager.getConnection(url, dbUserName, dbPassword);    //set connection
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUserName()); //set first string
            preparedStatement.setString(2, user.getUserPassword());    //set second string
            ResultSet resultSet = preparedStatement.executeQuery(); //execute query and save it to ResultSet object

            if(resultSet.next()){   //if there's record
                PreparedStatement pstLogged = connection.prepareStatement(loggedQuery);
                pstLogged.setString(1, resultSet.getString(3));
                pstLogged.setString(2, resultSet.getString(5));
                pstLogged.executeUpdate();

                if(user.getUserName().equals("admin") && resultSet.getString(2).equals("1")){   //log in as admin
                    pstLogged.close();
                    preparedStatement.close();
                    connection.close();
                    response.sendRedirect("/blog/admin_panel.jsp");
                }
                if(resultSet.getString(2).equals("2")){ //log in as normal user
                    pstLogged.close();
                    preparedStatement.close();
                    response.sendRedirect("/blog/home.jsp");   //redirect to home.html

                    connection.close();
                    //redirect to home.html
                    response.sendRedirect("/blog/home.html");
                }
            }
            else{   //if there's no record
                out.print(
                    "<script type='text/javascript'>" +
                        "window.alert('User name or password is incorrect！');" +
                        "history.go(-1);" +
                    "</script>"
                ); //give warning
            }
        connection.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }
}
