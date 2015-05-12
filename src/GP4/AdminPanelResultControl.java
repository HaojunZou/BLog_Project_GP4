package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/AdminPanelResultControl")
public class AdminPanelResultControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            String url = "jdbc:mysql://localhost/blog";
            String dbUserName = "root";
            String dbPassword = "admin";
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection connection= DriverManager.getConnection(url, dbUserName, dbPassword);
            String checkQuery = "select * from users where userName = ?";
            String deleteQuery = "delete from users where userName = ?";

            String deleteValue = request.getParameter("deleteRecord");

            if(!deleteValue.equals("")){
                PreparedStatement pstCheck = connection.prepareStatement(checkQuery);
                pstCheck.setString(1, deleteValue);
                ResultSet resultSetCheck = pstCheck.executeQuery();
                if(resultSetCheck.next()){
                    PreparedStatement pstDelete = connection.prepareStatement(deleteQuery);
                    pstDelete.setString(1, resultSetCheck.getString(3));
                    pstDelete.executeUpdate();
                    pstCheck.close();
                    pstDelete.close();
                    connection.close();
                    response.sendRedirect("/blog/admin_panel_executed.html");
                }else{
                    out.print(
                        "<script type='text/javascript'>" +
                            "window.alert('No such data！');" +
                            "history.go(-1);" +
                        "</script>"
                    );
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
