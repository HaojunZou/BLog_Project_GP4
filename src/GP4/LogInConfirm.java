package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/loginconfirm.html")
public class LogInConfirm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String un = request.getParameter("un");
            String pwd = request.getParameter("pwd");

            String url = "jdbc:mysql://localhost/blog";
            String userName = "root";
            String password = "haojun";
            String query = "select un, pwd from users where un=? and pwd=?";    //check un and pwd column in table users
            Class.forName("org.gjt.mm.mysql.Driver");     //load driver
            Connection connection= DriverManager.getConnection(url, userName, password);    //set connection
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, un); //set first string
            preparedStatement.setString(2, pwd);    //set second string
            ResultSet resultSet = preparedStatement.executeQuery(); //execute query and save it to ResultSet object

            if(resultSet.next()){   //if there's record
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.html");
                response.sendRedirect("/blog/home.html");   //redirect to home.html
                dispatcher.forward(request, response);
            }
            else{   //if there's no record
                out.print("<script>alert('User Name or Password is incorrect!')</script>"); //give warning, but... not working???
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
                response.sendRedirect("/blog/login.jsp");   //stay at the same page
                dispatcher.include(request, response);
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/loginconfirm.html");
        dispatcher.forward(request, response);
    }
}
