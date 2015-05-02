package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;


@WebServlet("/login.jsp")
public class logIn extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public logIn() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
        String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
        String url = "jdbc:microsoft:sqlserver://localhost:1433; DatabaseName=blog";
        String username = "";
        String password = "";
        String codetemp=request.getParameter("username");
        String passtemp=request.getParameter("password");
        String strsql = "select password from bas_czjbm where bas_code=" + codetemp;
        try {
            Class.forName(driver);
            Connection con=java.sql.DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:3306;DatabaseName=blog","","");

            Statement stmt = con.createStatement();
            ResultSet rdoset=stmt.executeQuery(strsql);
            while (rdoset.next()){
                //if rdoset.getString("password")!=request.getParameter("password"){

                //}else{

                //}

            }
            rdoset.close();
            stmt.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.print("<script>alert('Log in successfully!'); window.location.href='../WEB-INF/home.html'</script>");
*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = null;

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/login.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);

    }
}
