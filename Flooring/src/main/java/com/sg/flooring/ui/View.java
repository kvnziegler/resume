/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.ui;

import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.Tax;
import com.sg.flooring.exceptions.IncompatableDataTypeException;
import java.lang.Thread.State;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author kvnzi
 */
public class View {

    private UserIO io;

    View(UserIO io) {
        this.io = io;
    }

    public int mainMenu() {
        io.print("");
        io.print("");
        io.print("");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n"
                + "    *  <<Ants in My Eyes Johnson Flooring>>\n"
                + "       \\ /       \\ /  \n"
                + "  ___  _@@       @@_  ___\n"
                + " (___)(_)   o-o  (_)(___)\n"
                + " //|| ||           || ||\\\n"
                + "    * 1. Display Orders\n"
                + "    * 2. Add an Order\n"
                + "    * 3. Edit an Order\n"
                + "    * 4. Remove an Order\n"
                + "    * 5. Save Current Work\n"
                + "    * 6. Quit\n"
                + "    * \n"
                + "    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        return io.readInt("Please enter number associated with desire choice.", 1, 6);
    }

    public void fileNotFound(String e) {
        io.print("");
        io.print("File not found... knew I should'a taken that left turn at Albuquerque...");
    }

    public void errorMessage(String e) {
        io.print("");
        io.print("I don't know what you just did but you better go back and try again...");
    }

    public void goodbye() {
        io.print("");
        io.print("");
        io.print("\"I'll always be there to catch you when you fall\"\n"
                + "\n"
                + "                                              ~the floor~\n");

        io.print("");
        io.print("==== GoodBye ====");
    }

    public String askForDate() {
        io.print("");
        return io.readString("Please enter the date of the orders you wish to see using \n"
                + "the following format: MM/dd/yyyy\n"
                + "Example :\n"
                + "09/20/1988 (representing September 20th 1988)\n"
                + "Date: ");
    }

    public void invalidate() {
        io.print("");
        io.print("The date you have requested may not fit the\n"
                + "format provided of MMddyyyy\n"
                + "or may be outside of our serviceable range");
    }

    public void displayOrdersWithDate(String date, ArrayList<Order> stuffs) {
        io.print("");
        io.print("all Orders on :" + date);
        io.print("");
        for (Order currentOrder : stuffs) {
            io.print("Order Number: " + Integer.toString(currentOrder.getOrderNumber()) + " | "
                    + "Name: " + currentOrder.getCustomeName() + " | "
                    + "Date: " + currentOrder.getDate() + " | "
                    + "State: " + currentOrder.getState() + " | "
                    + "Product: " + currentOrder.getProductType() + " | "
                    + "Total: $" + currentOrder.getTotal() + " | \n");
        }
    }

    public void unknownCommand() {
        io.print("");
        io.print("...I don't know what you just did...you better go back and try again...");
        io.print("");
    }

    public int savePrompt() {
        io.print("");
        io.print("would you like to save before exiting?");
        return io.readInt("select the number associated with your choice\n"
                + "1 - yes\n"
                + "2 - no", 1, 2);
    }

    public void informationSaved() {
        io.print("");
        io.print("information saved!");
    }

    public void unsavedDiscard() {
        io.print("");
        io.print("all unsaved data has been discarded...like an old tissue");
    }    
   
    public String newOrderName(){
       return io.readString("Customer Name: ");
    }
    
    public String newOrderState(){
        return io.readString("State(using abreviations): ");
    }

    public String newOrderProduct(){
        return io.readString("Product Type: ");
    }
    
    public BigDecimal newOrderArea(){
        return io.readBigDecimal("Area to be covered: ");
    }
    
    
    
    public void displayNewOrder(Order newOrder) {
        io.print("OrderNumber: " + newOrder.getOrderNumber() + "\n"
                + "Order Date: " + newOrder.getDate() + "\n"
                + "Customer Name: " + newOrder.getCustomeName() + "\n"
                + "State: " + newOrder.getState() + "\n"
                + "Tax Rate: " + newOrder.getTaxRate() + "\n"
                + "Product Type: " + newOrder.getProductType() + "\n"
                + "Area: " + newOrder.getArea() + "\n"
                + "Cost per Square Foot: $" + newOrder.getCostPerSquareFoot() + "\n"
                + "Cost of Labor per Square Foot: $" + newOrder.getLaborCostPerSquareFoot() + "\n"
                + "\n"
                + "---------------------------------\n"
                + "\n"
                + "Total Material Cost: $" + newOrder.getMaterialCost() + "\n"
                + "Total Labor Cost: $" + newOrder.getLaborCost() + "\n"
                + "Total tax: $" + newOrder.getTax() + "\n"
                + "\n"
                + "---------------------------------\n"
                + "\n"
                + "Total: $" + newOrder.getTotal());

    }

    public int confirmAddition() {
        return io.readInt("Would you like to add this order?\n"
                + "1 - yes\n"
                + "2 - no", 1, 2);
    }

    public int confirmEdit(Order newOrder) {
        displayNewOrder(newOrder);
        return io.readInt("Are these changes correct?\n"
                + "1 - yes\n"
                + "2 - no", 1, 2);
    }

    public void additionDiscarded() {
        io.print("submission has been discarded");
    }

    public void submissionAdded() {
        io.print("Ordered added to temporary memory, be sure to save before quitting in order to keep this inormation");
    }

    public int displayOrdersGetSelection(String date, ArrayList<Order> stuffs) {
        int lastIndex = 0;
        int index = 1;
        io.print("");
        io.print("all Orders on :" + date);
        io.print("");
        for (Order currentOrder : stuffs) {
            lastIndex = stuffs.indexOf(currentOrder);
            io.print("#" + index + "\n"
                    + "Order Number: " + Integer.toString(currentOrder.getOrderNumber()) + " | "
                    + "Date: " + currentOrder.getDate() + " | "
                    + "Name: " + currentOrder.getCustomeName() + " | "
                    + "State: " + currentOrder.getState() + " | "
                    + "Product: " + currentOrder.getProductType() + " | "
                    + "Total: $" + currentOrder.getTotal() + " | \n");
                    index++;
        }
        return io.readInt("Enter the List Number you wish to Edit", 1, lastIndex + 1);
    }

    public String editCustomerName(String name) {
        return io.readString("Enter customer name current name :" + name);
    }

    public String editState(String state) {
        return io.readString("Enter State (using abreviations) current state: " + state);
    }

    public String editProductType(String type) {
        return io.readString("Enter product type current type: " + type);
    }

    public String editArea(BigDecimal area) throws NumberFormatException {
        return io.readString("Enter squarefootage, current squarefootage: " + area);
        // can either do try catch or read a string and then parse to a BD
    }

    public String editDate(String date) {
        return io.readString("Enter order date current date: " + date);
    }

    public int confirmDelete(Order order) {
        return io.readInt("Are You sure you wish to delete Order Number: " + order.getOrderNumber()
                + " | Name: " + order.getCustomeName() + "\n"
                + "\n"
                + "1 - yes\n"
                + "2 - no", 1, 2);
    }

    public void orderDeleted() {
        io.print("");
        io.print("");
        io.print(" ==== Order successfully deleted ====");
        io.print("");
        io.print("");
    }

    public void orderNotDeleted() {
        io.print("");
        io.print("");
        io.print(" === Order was not deleted and is still in memory ===");
        io.print("");
        io.print("");

    }

    public void saveOrderTraining() {
        io.print("");
        io.print("");
        io.print(" === You are in training Mode but if this were real you would have saved just now === ");
        io.print("");
        io.print("");
    }

    public void trainingBanner() {
        io.print("**********    Training Mode    ***********");
    }

    public void wrongProductDisplayAll(ArrayList<Product> allProducts) {
        io.print("The product type you enter is one we do not offer");
        io.print("");
        io.print("Please select from the following options");
        for (Product currentProduct : allProducts) {
            io.print(currentProduct.getProductType());
        }
    }

    public void wrongStateDisplayAll(ArrayList<Tax> state) {
        io.print("The State you have selected is outside of our coverage map");
        io.print("");
        io.print("Please select from the following options");
        for (Tax currentState : state) {
            io.print(currentState.getState());
        }
    }

    public void needBiggerArea() {
        io.print("You cannot have a negative amount of flooring...where would you stand?");
    }
    
    public void noOrdersOnDate(String userDate){
        io.print("Sorry there don't seem to be any orders on that date");
    }
}
