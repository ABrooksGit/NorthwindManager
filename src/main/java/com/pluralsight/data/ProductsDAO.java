package com.pluralsight.data;

import com.pluralsight.models.Category;
import com.pluralsight.models.Product;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {

    private BasicDataSource dataSource;


    public ProductsDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
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

}
