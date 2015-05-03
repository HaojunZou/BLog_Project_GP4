package GP4;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


@WebServlet("/login.jsp")
public class LogIn extends HttpServlet {
    int id;
    String un = "", email = "", pwd = "";
    private static final long serialVersionUID = 1L;
    public LogIn() {
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

        try{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String un = request.getParameter("un");
            String pwd = request.getParameter("pwd");

            String url = "jdbc:mysql://localhost/blog";
            String userName = "root";
            String password = "haojun";
            String query = "select * from users where un=? and pwd=?";
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection connection= DriverManager.getConnection(url, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, un);
            preparedStatement.setString(2, pwd);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.html");
                response.sendRedirect("/blog/login.jsp");
                dispatcher.forward(request, response);
            }
            else{
                out.println("Username or Password incorrect");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
                dispatcher.include(request, response);
            }

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
