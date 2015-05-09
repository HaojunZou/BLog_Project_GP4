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
        String checkExistQuery = "select id from users where userName=? or email=?"; //check if user or email is exist
        String idCheckQuery = "select * from users where id=?"; //check if this id number has been token
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

            if(!resultSetExist.next() && accept.equals("accept")){  //if this record is not exist and user accepted provision
                int idNumber = 1;
                for(int i = 1; i<1000000; i++){
                    PreparedStatement pstIdCheck = connection.prepareStatement(idCheckQuery);
                    pstIdCheck.setString(1, Integer.toString(i));
                    ResultSet resultSetId = pstIdCheck.executeQuery();  //check if this id number has been token
                    if(resultSetId.next()){     //if id number exist, than make it plus 1
                        pstIdCheck.setString(1, Integer.toString(i + 1));
                        pstIdCheck.close();
                    }else{                      //if not, use this id number
                        idNumber = i;
                        pstIdCheck.close();
                        break;
                    }
                }
                if(idNumber>=1000000){
                    out.print(
                        "<script type='text/javascript'>" +
                            "window.alert('Sorry! We cannot register more user...');" +
                            "history.go(-1)" +
                        "</script>"
                    ); //give warning if database is full
                }else {
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, Integer.toString(idNumber));
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
