package com.pluralsight.ui;

import com.pluralsight.models.Employee;

public class UserInterface {


    private Employee currentEmployee;
    private Console console;

    public UserInterface(){
        this.console = new Console();
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
        }


    }

    private void listCategoriesAll() {
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
