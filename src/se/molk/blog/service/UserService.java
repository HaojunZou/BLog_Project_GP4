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
        else{getUserType = 0;}
        return getUserType;
    }

    public boolean deleteAnUser(String userName) throws SQLException {
        if(userDAO.deleteAnUser(userName)){return true;}
        else{return false;}
    }

    public void addUser(String userName, String email, String userPassword, String realName,
                        String gender, String birthday, String country) throws SQLException {

        userDAO.addUser(userName, email, userPassword, realName, gender, birthday, country);
    }

}