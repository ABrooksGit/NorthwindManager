package com.pluralsight.data;

import com.pluralsight.models.Category;
import com.pluralsight.models.Product;
import com.pluralsight.models.Supplier;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NorthwindDataManager {
    private BasicDataSource dataSource;

    public NorthwindDataManager(BasicDataSource dataSource) {
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

    public List<Product> getProducts(){
        ArrayList<Product> result = new ArrayList<Product>();
        String query = """
        SELECT products.ProductID,
            products.ProductName,
            products.SupplierID,
            products.CategoryID,
            products.UnitPrice
            FROM northwind.products;
        """;

        try(
                Connection c = dataSource.getConnection();
                PreparedStatement s = c.prepareStatement(query);
                ResultSet queryResult = s.executeQuery()
        ) {
            while(queryResult.next()){
                int productID = queryResult.getInt(1);
                String productName = queryResult.getString(2);
                int supplierID = queryResult.getInt(3);
                int categoryID = queryResult.getInt(4);
                double unitPrice = queryResult.getDouble(5);
                Product product = new Product(productID, productName, supplierID,categoryID,unitPrice);
                result.add(product);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public List<Product> getProductsByCategory(Category category){
        ArrayList<Product> result = new ArrayList<Product>();
        String query = """
        SELECT products.ProductID,
            products.ProductName,
            products.SupplierID,
            products.CategoryID,
            products.UnitPrice
            FROM northwind.products
            WHERE CategoryID = ?;
        """;

        try(
                Connection c = dataSource.getConnection();
                PreparedStatement s = c.prepareStatement(query);

        ) {
            s.setInt(1,category.getId());

            try(ResultSet queryResult = s.executeQuery()) {
                while (queryResult.next()) {
                    int productID = queryResult.getInt(1);
                    String productName = queryResult.getString(2);
                    int supplierID = queryResult.getInt(3);
                    int categoryID = queryResult.getInt(4);
                    double unitPrice = queryResult.getDouble(5);
                    Product product = new Product(productID, productName, supplierID, categoryID, unitPrice);
                    result.add(product);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;

    }

    public List<Product> getProductByPrice(double minPrice, double maxPrice){
        ArrayList<Product> result = new ArrayList<Product>();
        String query = """
        SELECT products.ProductID,
            products.ProductName,
            products.SupplierID,
            products.CategoryID,
            products.UnitPrice
            FROM northwind.products
            WHERE unitPrice >= ? AND unitPrice <= ?;
        """;

        try(
                Connection c = dataSource.getConnection();
                PreparedStatement s = c.prepareStatement(query);

        ) {
            s.setDouble(1, minPrice);
            s.setDouble(2, maxPrice);

            try (ResultSet queryResult = s.executeQuery()) {
                while (queryResult.next()) {
                    int productID = queryResult.getInt(1);
                    String productName = queryResult.getString(2);
                    int supplierID = queryResult.getInt(3);
                    int categoryID = queryResult.getInt(4);
                    double unitPrice = queryResult.getDouble(5);
                    Product product = new Product(productID, productName, supplierID, categoryID, unitPrice);
                    result.add(product);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }




    public List<Product> getProductsBySupplier(Supplier supplier){
        return null;
    }



    public List<Supplier> getSuppliers(){
        return null;
    }




}
