package GP4;

import java.sql.*;

public class Validate {
    public static boolean checkUser(String un , String email, String pwd)
    {
        boolean st =false;
        try{
            String url = "jdbc:mysql://localhost/blog";
            String userName = "root";
            String password = "haojun";
            String query = "select * from register where un=? and email=? and pwd=?";
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection connection= DriverManager.getConnection
                    (url, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, un);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, pwd);
            ResultSet resultSet = preparedStatement.executeQuery();
            st = resultSet.next();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return st;
    }
}
