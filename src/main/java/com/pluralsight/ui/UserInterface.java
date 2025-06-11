package com.pluralsight.ui;

import com.pluralsight.data.NorthwindDataManager;
import com.pluralsight.models.Category;
import com.pluralsight.models.Employee;

import java.util.List;

public class UserInterface {


    private Employee currentEmployee;
    private Console console;
    private NorthwindDataManager dataManager;

    public UserInterface(NorthwindDataManager dataManager){
        this.console = new Console();
        this.dataManager = dataManager;
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
        List<Category> categories = dataManager.getCategories();
        if(categories.stream().count() <= 0 ){
            System.out.println("No categories found");
        }
        else {
            categories.stream().forEach(c -> System.out.println(c.getCategoryName()));
        }


    }

    private void listAllProducts() {
    }

    private void listProductsByCategory() {
    }

    private void listProductByPrice() {
    }

    private void listSuppliersAll() {
    }

    private void listProductBySupplier() {
    }


}
