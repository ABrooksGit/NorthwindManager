package com.pluralsight.data;

import com.pluralsight.models.Category;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO {
    private BasicDataSource dataSource;

    public CategoriesDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Category> getCategories(){

        ArrayList<Category> result = new ArrayList<Category>();
        String query = """
                select\s
                CategoryID,
                CategoryName\s
                from Categories
                """;

        try(
                Connection c = dataSource.getConnection();
                PreparedStatement s = c.prepareStatement(query);
                ResultSet queryResult = s.executeQuery()
        ) {
            while(queryResult.next()){
                int categoryID = queryResult.getInt(1);
                String categoryName = queryResult.getString(2);
                Category category = new Category(categoryID, categoryName);
                result.add(category);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;

    }

    public Category getCategoryByName(String categoryName){
        Category result = null;

        String query = """
                SELECT\s
                CategoryID,
                CategoryName\s
                FROM Categories
                WHERE CategoryName = ?;
                """;

        try(
                Connection c = dataSource.getConnection();
                PreparedStatement s = c.prepareStatement(query);

        ) {
            s.setString(1, categoryName);


            try (ResultSet queryResult = s.executeQuery()) {
                if(queryResult.next()) {
                    int categoryID = queryResult.getInt(1);
                    return new Category(categoryID, categoryName);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }




        return null;
    }



}
