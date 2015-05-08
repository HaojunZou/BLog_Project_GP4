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
            String dbPassword = "haojun";
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
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/admin_panel_executed.html");
                    dispatcher.forward(request, response);
/*
                    PreparedStatement pstDelete = connection.prepareStatement(deleteQuery);
                    pstDelete.setString(1, resultSetCheck.getString(3));
                    ResultSet resultSetDelete = pstDelete.executeQuery();
                    pstDelete.executeUpdate();
                    pstDelete.close();

                    PreparedStatement pstRecheck = connection.prepareStatement(checkQuery);
                    pstRecheck.setString(1, deleteValue);
                    ResultSet resultSetRecheck = pstRecheck.executeQuery();
                    if(!resultSetRecheck.next()){
                        out.print(
                            "<script type='text/javascript'>" +
                                "window.alert('User has been successfully deleted！');" +
                            "</script>"
                        );
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/admin_panel_executed.html");
                        dispatcher.forward(request, response);
                    }else{
                        out.print(
                            "<script type='text/javascript'>" +
                                "window.alert('Cannot delete user！');" +
                                "history.go(-1);" +
                            "</script>"
                        );
                    }
                    pstDelete.close();
                    pstRecheck.close();
                    */
                }else{
                    out.print(
                        "<script type='text/javascript'>" +
                            "window.alert('No such record！');" +
                            "history.go(-1);" +
                        "</script>"
                    );
                }
                pstCheck.close();
                connection.close();
            }

            else{
                out.print(
                    "<script type='text/javascript'>" +
                        "window.alert('Please enter a value！');" +
                        "history.go(-1);" +
                    "</script>"
                );
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
