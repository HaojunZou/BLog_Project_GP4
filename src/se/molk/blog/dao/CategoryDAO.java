package se.molk.blog.dao;

import se.molk.blog.domain.Category;

import java.sql.*;
import java.util.*;

public class CategoryDAO {
    private static final String driver = "org.gjt.mm.mysql.Driver";
    private static final String url = "jdbc:mysql://localhost/blog";
    private static final String dbUserName = "root";
    private static final String dbPassword = "haojun";

    public CategoryDAO() throws Exception {
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            throw  new Exception("Error creating driver");
        }
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categoryList = new LinkedList<Category>();
        Category category = new Category();

        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String categorySearchQuery = "select * from Categories";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(categorySearchQuery);
            while(resultSet.next()){
                category.setCate_id(resultSet.getInt("cate_id"));
                category.setName(resultSet.getString("cateName"));

                categoryList.add(category);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return categoryList;
    }

    public Category getCategoryById(int cate_id) throws SQLException {
        Category category = new Category();
        Connection connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        try{
            String categorySearchQuery = "select * from Categories where cate_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(categorySearchQuery);
            preparedStatement.setInt(1, cate_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                category.setCate_id(resultSet.getInt("cate_id"));
                category.setName(resultSet.getString("cateName"));
            }
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return category;
    }
}
