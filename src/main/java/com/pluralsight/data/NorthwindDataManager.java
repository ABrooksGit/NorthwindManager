package com.pluralsight.data;

import com.pluralsight.models.Category;
import com.pluralsight.models.Product;
import com.pluralsight.models.Supplier;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class NorthwindDataManager {
    private BasicDataSource dataSource;

    public NorthwindDataManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Category> getCategories(){

        return null;
    }

    public Category getCategoryByName(String categoryName){
        return null;
    }

    public List<Product> getProducts(){

        return null;
    }

    public List<Product> getProductsByCategory(Category category){
        return null;
    }

    public List<Product> getProductByPrice(double minPrice, double maxPrice){
        return null;
    }
    public List<Product> getProductsBySupplier(Supplier supplier){
        return null;
    }
    public List<Supplier> getSuppliers(){
        return null;
    }




}
