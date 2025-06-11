package com.pluralsight.ui;

import com.pluralsight.data.CategoriesDAO;
import com.pluralsight.data.ProductsDAO;
import com.pluralsight.data.SuppliersDAO;
import com.pluralsight.models.Category;
import com.pluralsight.models.Employee;
import com.pluralsight.models.Product;

import java.util.List;

public class UserInterface {


    private Employee currentEmployee;
    private Console console;
    private CategoriesDAO categoriesDAO;
    private ProductsDAO productsDAO;
    private SuppliersDAO suppliersDAO;

    public UserInterface(CategoriesDAO categoriesDAO, ProductsDAO productsDAO, SuppliersDAO suppliersDAO) {
        this.console = new Console();
        this.categoriesDAO = categoriesDAO;
        this.productsDAO = productsDAO;
        this.suppliersDAO = suppliersDAO;
    }

    public void display(){
        System.out.println("Welcome to the Northwind Manager!");

        currentEmployee = loginUser();
        System.out.println("Welcome, " + this.currentEmployee.getFirstName() + "!");
        showHomeMenu();




    }


    private Employee loginUser(){
        String s = console.promptForString("Press Enter to Log in as Test", true);
        Employee e = new Employee(1, "Test", "Account");




        return e;

    }

    private void showHomeMenu(){


        while(true){
            String[] menuOptions = {
                    "list all categories",
                    "list all products",
                    "list products by category",
                    "list products by price",
                    "list all suppliers",
                    "list products by supplier",
                    "exit"
            };


            int userChoice = console.promptForOption(menuOptions);
            switch (userChoice){
                case 1:
                    listCategoriesAll();
                    break;
                case 2:
                    listAllProducts();
                    break;
                case 3:
                    listProductsByCategory();
                    break;
                case 4:
                    listProductByPrice();
                    break;
                case 5:
                    listSuppliersAll();
                    break;
                case 6:
                    listProductBySupplier();
                    break;
                case 7:
                    System.exit(0);

            }

            console.promptForString("Please press <Enter> to continue....",true);
            System.out.println(" ");
            System.out.println(" ");
        }


    }

    private void listCategoriesAll() {
        List<Category> categories = categoriesDAO.getCategories();
        if(categories.stream().count() <= 0 ){
            System.out.println("No categories found");
        }
        else {
            System.out.println(" ");
            categories.stream().forEach(c -> System.out.println(c.getCategoryName()));
        }


    }

    private void listAllProducts() {
        List<Product> products = productsDAO.getProducts();
        displayProducts(products);
    }

    private void listProductsByCategory() {
        String categoryName = console.promptForString("Enter Category Name: ");

        Category category = categoriesDAO.getCategoryByName(categoryName);
        if(category != null){
//            System.out.println("You selected category id : " + category.getId());
            List<Product> products = productsDAO.getProductsByCategory(category);
            displayProducts(products);

        } else {
            System.out.println("There is no categoryID");
        }
    }

    private void listProductByPrice() {

        double minPrice = console.promptForDouble("Enter the smallest price: ");
        double maxPrice = console.promptForDouble("Enter the largest Price: ");
        List<Product> products = productsDAO.getProductByPrice(minPrice, maxPrice);
        displayProducts(products);
    }

    private void listSuppliersAll() {
    }

    private void listProductBySupplier() {
    }

    private void displayProducts(List<Product> products){
        if(products.stream().count() <= 0){
            System.out.println("No Products found");
        }
        else {
            System.out.println(" ");
            products.stream().forEach(p -> System.out.println(p.getId() + ") " + p.getProductName() + " $" + p.getPrice()));
        }


    }


}
