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
            String un = request.getParameter("un");
            String pwd = request.getParameter("pwd");
            user.setUn(un);
            user.setPwd(pwd);

            String url = "jdbc:mysql://localhost/blog";
            String userName = "root";
            String password = "haojun";
            String query = "select un, pwd from users where un=? and pwd=?";    //check un and pwd column in table users
            Class.forName("org.gjt.mm.mysql.Driver");     //load driver
            Connection connection= DriverManager.getConnection(url, userName, password);    //set connection
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUn()); //set first string
            preparedStatement.setString(2, user.getPwd());    //set second string
            ResultSet resultSet = preparedStatement.executeQuery(); //execute query and save it to ResultSet object

            if(resultSet.next()){   //if there's record
                response.sendRedirect("/blog/home.html");   //redirect to home.html
            }
            else{   //if there's no record
                out.print(
                    "<script type='text/javascript'>" +
                        "window.alert('User name or password is incorrect！');" +
                        "history.go(-1);" +
                    "</script>"
                ); //give warning
            }
            preparedStatement.close();
            connection.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }
}
