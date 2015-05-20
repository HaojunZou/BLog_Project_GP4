package se.molk.blog.service;

import se.molk.blog.dao.UserDAO;
import se.molk.blog.domain.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public List<User> getUserByFuzzySearch(String fuzzy) throws SQLException {
        return userDAO.getUserByFuzzySearch(fuzzy);
    }

    public int logIn(String userName, String userPassword) throws SQLException {
        String userType = userDAO.logIn(userName, userPassword);
        int getUserType;
        if(userType.equals("Normal User")){getUserType = 2;}
        else if(userType.equals("Administrator")){getUserType = 1;}
        else if(userType.equals("Unknown")){getUserType = 3;}
        else{getUserType = 0;}
        return getUserType;
    }

    public boolean deleteAnUser(String userName) throws SQLException {
        return userDAO.deleteAnUser(userName);
    }

    public boolean addUser(String userName, String email, String userPassword, String realName,
                        String gender, String birthday, String country) throws SQLException {
        return userDAO.addUser(userName, email, userPassword, realName, gender, birthday, country);
    }

    public boolean changePassword(String currentUserName, String oldUserPassword, String newUserPassword) throws SQLException {
        return userDAO.changePassword(currentUserName, oldUserPassword, newUserPassword);
    }

    public boolean updateUserInfo(String currentUserName, String realName, String gender,
                                  String birthday, String country) throws SQLException {
        return userDAO.updateUserInfo(currentUserName, realName, gender, birthday, country);
    }

    public String adminUpdateUserInfo(String userName, String newUserName, String newEmail, String newPassword,
                  String newRealName, String newGender, String newBirthday, String newCountry) throws SQLException{
        return userDAO.adminUpdateUserInfo(userName, newUserName, newEmail, newPassword, newRealName, newGender, newBirthday, newCountry);
    }

    public boolean isLogged(String currentUserName){
        return currentUserName != null;
    }
}