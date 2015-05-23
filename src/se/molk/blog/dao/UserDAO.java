package se.molk.blog.dao;

import se.molk.blog.domain.User;

import java.sql.*;
import java.util.*;

public class UserDAO {
    private static final String driver = "org.gjt.mm.mysql.Driver";
    private static final String url = "jdbc:mysql://localhost/blog";
    private static final String dbUserName = "root";
    private static final String dbPassword = "haojun";


    public UserDAO() throws Exception {
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            throw  new Exception("Error creating driver");
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new LinkedList<User>();

        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String userSearchQuery = "select * from Users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(userSearchQuery);
            while(resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserType(resultSet.getString("userType"));
                user.setUserName(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("email"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setRealName(resultSet.getString("realName"));
                user.setGender(resultSet.getString("gender"));
                user.setBirthday(resultSet.getString("birthday"));
                user.setCountry(resultSet.getString("country"));

                userList.add(user);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return userList;
    }

    public List<User> getUserByFuzzySearch(String fuzzy){
        List<User> userList = new LinkedList<User>();

        try {
            Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
            String fuzzySearchQuery = "select * from Users where userName like ? or userPassword like ?";
            PreparedStatement pstSearch = connection.prepareStatement(fuzzySearchQuery);
            pstSearch.setString(1, "%" + fuzzy + "%");
            pstSearch.setString(2, "%" + fuzzy + "%");
            ResultSet resultSet = pstSearch.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserType(resultSet.getString("userType"));
                user.setUserName(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("email"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setRealName(resultSet.getString("realName"));
                user.setGender(resultSet.getString("gender"));
                user.setBirthday(resultSet.getString("birthday"));
                user.setCountry(resultSet.getString("country"));

                userList.add(user);
            }
            pstSearch.close();
            connection.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUserById(int user_id) throws SQLException {
        List<User> userList = new LinkedList<User>();
        User user = null;
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String userSearchQuery = "select * from Users where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(userSearchQuery);
            preparedStatement.setString(1, Integer.toString(user_id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserType(resultSet.getString("userType"));
                user.setUserName(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("email"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setRealName(resultSet.getString("realName"));
                user.setGender(resultSet.getString("gender"));
                user.setBirthday(resultSet.getString("birthday"));
                user.setCountry(resultSet.getString("country"));

                userList.add(user);
            }
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return user;
    }

    public int getUserIdByUserName(String userName) throws SQLException {
        int user_id = 0;
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String userSearchQuery = "select user_id from Users where userName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(userSearchQuery);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user_id = resultSet.getInt("user_id");
            }
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return user_id;
    }

    public String [] getUserInfoByName(String userName) throws SQLException {
        String [] info = new String[7];
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String userSearchQuery = "select userName, email, userPassword, realName, gender, birthday, country from Users where userName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(userSearchQuery);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                info[0] = resultSet.getString(1);
                info[1] = resultSet.getString(2);
                info[2] = resultSet.getString(3);
                info[3] = resultSet.getString(4);
                info[4] = resultSet.getString(5);
                info[5] = resultSet.getString(6);
                info[6] = resultSet.getString(7);
            }
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return info;
    }

    public String logIn(String userName, String userPassword){
        String userType = null;
        try {
            Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
            String searchQuery = "select * from Users where userName = ? and userPassword = ?";
            PreparedStatement pstSearch = connection.prepareStatement(searchQuery);
            pstSearch.setString(1, userName);
            pstSearch.setString(2, userPassword);
            ResultSet resultSet = pstSearch.executeQuery();
            if(resultSet.next()) {
                userType = resultSet.getString("userType");
                pstSearch.close();
                connection.close();
            }else{
                userType = "Unknown";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userType;
    }

    public boolean checkUserByUserName(String userName) throws SQLException {
        boolean hasUser = false;
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try {
            String checkQuery = "select * from Users where userName = ?";
            PreparedStatement pstCheck = connection.prepareStatement(checkQuery);
            pstCheck.setString(1, userName);
            ResultSet resultSetCheck = pstCheck.executeQuery();
            if(resultSetCheck.next()){
                pstCheck.close();
                hasUser = true;
            }else {
                pstCheck.close();
                hasUser = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return hasUser;
    }

    public boolean deleteAnUser(String userName) throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        if(checkUserByUserName(userName)) {
            try {
                String deleteQuery = "delete from Users where userName = ?";
                PreparedStatement pstDelete = connection.prepareStatement(deleteQuery);
                pstDelete.setString(1, userName);
                pstDelete.executeUpdate();
                pstDelete.close();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            connection.close();
            return false;
        }
        return false;
    }

    public boolean addUser(String userName, String email, String userPassword, String realName,
                           String gender, String birthday, String country) throws SQLException {
        User user = new User();
        user.setUserType("Normal User");
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String checkExistQuery = "select user_id from Users where userName=? or email=?"; //check if user or email is exist
            String idCheckQuery = "select * from Users where user_id=?"; //check if this id number has been token
            String insertQuery =
                    "insert into Users (user_id, userType, userName, email, userPassword, realName, gender, birthday, country)" +
                            "values(?,?,?,?,?,?,?,?,?)";   //insert a record to user table
            PreparedStatement preparedStatementExist = connection.prepareStatement(checkExistQuery);
            preparedStatementExist.setString(1, userName);
            preparedStatementExist.setString(2, email);
            ResultSet resultSetExist = preparedStatementExist.executeQuery();

            if(!resultSetExist.next()) {  //if this record is not exist
                int idNumber = 1;
                for (int i = 1; i < 1000000; i++) {
                    PreparedStatement pstIdCheck = connection.prepareStatement(idCheckQuery);
                    pstIdCheck.setString(1, Integer.toString(i));
                    ResultSet resultSetId = pstIdCheck.executeQuery();  //check if this id number has been token
                    if (resultSetId.next()) {     //if id number exist, than make it plus 1
                        pstIdCheck.setString(1, Integer.toString(i + 1));
                        pstIdCheck.close();
                    } else {                      //if not, use this id number
                        idNumber = i;
                        pstIdCheck.close();
                        break;
                    }
                }
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, idNumber);
                preparedStatement.setString(2, user.getUserType());
                preparedStatement.setString(3, userName);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, userPassword);
                preparedStatement.setString(6, realName);
                preparedStatement.setString(7, gender);
                preparedStatement.setString(8, birthday);
                preparedStatement.setString(9, country);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                preparedStatementExist.close();
                connection.close();
                return true;
            }else{
                preparedStatementExist.close();
                connection.close();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean changePassword(String currentUserName, String oldUserPassword, String newUserPassword) throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        String user = logIn(currentUserName, oldUserPassword);
        if(user.equals("Normal User")){
            try{
                String changePwdQuery = "update Users set userPassword = ? where userName = ?";
                PreparedStatement pstChangePwd = connection.prepareStatement(changePwdQuery);
                pstChangePwd.setString(1, newUserPassword);
                pstChangePwd.setString(2, currentUserName);
                pstChangePwd.executeUpdate();
                pstChangePwd.close();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            connection.close();
            return false;
        }
        return false;
    }

    public boolean updateUserInfo(String currentUserName, String realName, String gender,
                                  String birthday, String country) throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        String[] info = getUserInfoByName(currentUserName);
        if(info!=null){
            if(realName == ""){realName = info[3];}
            if(gender == ""){gender = info[4];}
            if(birthday == ""){birthday = info[5];}
            if(country == ""){country = info[6];}
            try{
                String updateInfoQuery = "update Users set realName=?, gender=?, birthday=?, country=? where userName=?";
                PreparedStatement pstUpdateInfo = connection.prepareStatement(updateInfoQuery);
                pstUpdateInfo.setString(1, realName);
                pstUpdateInfo.setString(2, gender);
                pstUpdateInfo.setString(3, birthday);
                pstUpdateInfo.setString(4, country);
                pstUpdateInfo.setString(5, currentUserName);
                pstUpdateInfo.executeUpdate();
                pstUpdateInfo.close();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            connection.close();
            return false;
        }
        return false;
    }

    public String adminUpdateUserInfo(String userName, String newUsername, String newEmail, String newPassword,
                                      String newRealName, String newGender, String newBirthday, String newCountry) throws SQLException {
        String updateResult = null;
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);

        try{
            String checkQuery = "select * from Users where userName=? or email=?";
            String updateInfoQuery = "update Users set userName=?, email=?, userPassword=?, realName=?, gender=?, birthday=?, country=? where userName=?";
            if(getUserIdByUserName(userName)!=0) {
                PreparedStatement pstCheck = connection.prepareStatement(checkQuery);
                pstCheck.setString(1, newUsername);
                pstCheck.setString(2, newEmail);
                ResultSet checkResult = pstCheck.executeQuery();
                if (!checkResult.next()) {
                    String[] info = getUserInfoByName(userName);
                    if (newUsername.equals("")) {
                        newUsername = info[0];
                    }
                    if (newEmail.equals("")) {
                        newEmail = info[1];
                    }
                    if (newPassword.equals("")) {
                        newPassword = info[2];
                    }
                    if (newRealName.equals("")) {
                        newRealName = info[3];
                    }
                    if (newGender.equals("")) {
                        newGender = info[4];
                    }
                    if (newBirthday.equals("")) {
                        newBirthday = info[5];
                    }
                    if (newCountry.equals("")) {
                        newCountry = info[6];
                    }
                    PreparedStatement pstUpdateInfo = connection.prepareStatement(updateInfoQuery);
                    pstUpdateInfo.setString(1, newUsername);
                    pstUpdateInfo.setString(2, newEmail);
                    pstUpdateInfo.setString(3, newPassword);
                    pstUpdateInfo.setString(4, newRealName);
                    pstUpdateInfo.setString(5, newGender);
                    pstUpdateInfo.setString(6, newBirthday);
                    pstUpdateInfo.setString(7, newCountry);
                    pstUpdateInfo.setString(8, userName);
                    pstUpdateInfo.executeUpdate();
                    pstUpdateInfo.close();
                    pstCheck.close();
                    updateResult = "updateSuccessful";
                } else {
                    pstCheck.close();
                    updateResult = "updateFailed";
                }
            }else{
                updateResult = "userNotExist";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return updateResult;
    }

}