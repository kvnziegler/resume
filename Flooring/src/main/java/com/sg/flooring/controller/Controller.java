/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.controller;

import com.sg.flooring.dto.Order;
import com.sg.flooring.exceptions.IncompatableDataTypeException;
import com.sg.flooring.exceptions.PersistenceException;
import com.sg.flooring.servicelayer.ServiceLayer;
import com.sg.flooring.ui.View;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kvnzi
 */
public class Controller {

    private View view;
    private ServiceLayer service;

    public Controller(View view, ServiceLayer service) {
        this.view = view;
        this.service = service;

    }

    public void run() {
        boolean ValidDate;
        String userDate;
        int savePrompt;
        Order newOrder;
        Order calcOrder;
        Order editedOrder;
        Order toGetRidOf;
        ArrayList<Order> allOrders;
        ArrayList<Order> dateOrders = new ArrayList();
        ArrayList<String> dates = new ArrayList();

        ArrayList<Order> orders = new ArrayList();
        ArrayList blankList;
        int conf;

        try {
            load();
            dates = getStartingDates();
            loadTaxes();
            loadProducts();
            boolean isMode = service.readMode();
            boolean keepGoing = true;
            int mainMenuSelection;
            int selection;
            do {
                blankList = new ArrayList();
                if (isMode) {
                    view.trainingBanner();
                }
                mainMenuSelection = view.mainMenu();
                switch (mainMenuSelection) {
                    case 1:
                        //display orders
                        do {
                            userDate = view.askForDate();
                            ValidDate = validateDate(userDate);
                            if (!ValidDate) {
                                view.invalidate();
                            }
                        } while (!ValidDate);
                        allOrders = service.getAllOrders();
                        for (Order currentOrder : allOrders) {
                            if (currentOrder.getDate().equalsIgnoreCase(userDate)) {
                                dateOrders.add(currentOrder);
                            }
                        }
                        if (dateOrders.isEmpty()) {
                            view.noOrdersOnDate(userDate);
                            dateOrders.clear();
                        } else {
                            view.displayOrdersWithDate(userDate, dateOrders);
                            dateOrders.clear();
                        }
                        break;
                    case 2:
                        //add an order

                        do {
                            userDate = view.askForDate();
                            ValidDate = validateDate(userDate);
                            if (!ValidDate) {
                                view.invalidate();
                            }
                        } while (!ValidDate);
                        if (ValidDate) {
                            newOrder = getOrderInfo(userDate);
                            calcOrder = calculateEverything(newOrder);
                            view.displayNewOrder(calcOrder);
                            int confirm = view.confirmAddition();
                            if (confirm == 1) {
                                service.addOrder(calcOrder);
                                view.submissionAdded();
                            } else {
                                view.additionDiscarded();
                            }
                        } else {
                            view.invalidate();
                        }
                        break;
                    case 3:
                        //edit order
                        do {
                            userDate = view.askForDate();
                            ValidDate = validateDate(userDate);
                            if (!ValidDate) {
                                view.invalidate();
                            }
                        } while (!ValidDate);
                        if (ValidDate) {
                            allOrders = service.getAllOrders();
                            for (Order currentOrder : allOrders) {
                                if (currentOrder.getDate().equalsIgnoreCase(userDate)) {
                                    dateOrders.add(currentOrder);
                                }
                            }
                            if (dateOrders.isEmpty()) {
                                view.noOrdersOnDate(userDate);
                                dateOrders.clear();
                            } else {

                                selection = view.displayOrdersGetSelection(userDate, dateOrders);
                                editedOrder = edit(selection, dateOrders);
                                dateOrders.clear();
                            }
                        }
                        break;
                    case 4:
                        //remove order                     

                        userDate = view.askForDate();
                        ValidDate = validateDate(userDate);
                        if (ValidDate) {
                            allOrders = service.getAllOrders();
                            for (Order currentOrder : allOrders) {
                                if (currentOrder.getDate().equalsIgnoreCase(userDate)) {
                                    dateOrders.add(currentOrder);
                                }
                            }
                            if (dateOrders.isEmpty()) {
                                view.noOrdersOnDate(userDate);
                            } else {
                                selection = view.displayOrdersGetSelection(userDate, dateOrders);
                                toGetRidOf = confirmDelete(selection, dateOrders);
                                conf = view.confirmDelete(toGetRidOf);

                                if (conf == 1) {
                                    removeOrder(toGetRidOf);
                                    //have remove order take in the actual order to be gotten ride of
                                    view.orderDeleted();
                                    dateOrders.clear();
                                    break;
                                } else {
                                    dateOrders.clear();
                                    break;
                                }
                            }
                        }
                        break;
                    case 5:
                        //save

                        if (isMode) {
                            view.saveOrderTraining();
                        } else {
                            save(dates);
                            view.informationSaved();

                        }
                        break;
                    case 6:
                        //exit

                        savePrompt = view.savePrompt();
                        if (savePrompt == 1) {
                            if (isMode) {
                                view.saveOrderTraining();
                                keepGoing = false;
                            } else {
                                save(dates);
                                view.informationSaved();
                                keepGoing = false;
                            }
                        } else {
                            view.unsavedDiscard();
                            keepGoing = false;
                        }
                        break;
                    default:
                        view.unknownCommand();
                        break;
                }
            } while (keepGoing);
            view.goodbye();
        } catch (PersistenceException e) {
            view.fileNotFound(e.getMessage());
        }
    }

    public Order getOrderInfo(String date) {
        Order calcOrder = new Order();
        String name;
        String state;
        String product;
        BigDecimal area;
        boolean validState;
        boolean validProduct;
        ArrayList allStates;
        ArrayList allProducts;
        BigDecimal zero = new BigDecimal(0);

        name = view.newOrderName();

        do {
            state = view.newOrderState();
            validState = service.validateState(state);
            if (validState == false) {
                allStates = service.getAllStates();
                view.wrongStateDisplayAll(allStates);
            }
        } while (validState == false);

        do {
            product = view.newOrderProduct();
            validProduct = service.validateProduct(product);
            if (validProduct != true) {
                allProducts = service.getAllProducts();
                view.wrongProductDisplayAll(allProducts);
            }
        } while (validProduct == false);

        do {
            try {
                area = view.newOrderArea();
            } catch (NumberFormatException e) {
                area = null;
            }
            if (area.compareTo(zero) <= 0) {
                view.needBiggerArea();
            }
        } while (area.compareTo(zero) <= 0);

        calcOrder.setDate(date);
        calcOrder.setCustomeName(name);
        calcOrder.setState(state);
        calcOrder.setProductType(product);
        calcOrder.setArea(area);

        return calcOrder;
    }

    public void load() throws PersistenceException {
        service.load();
    }

    public void loadProducts() throws PersistenceException {
        service.loadProducts();
    }

    public void loadTaxes() throws PersistenceException {
        service.loadTaxes();
    }

    public boolean validateDate(String userDate) {
        return service.validateDate(userDate);
    }

    public ArrayList<Order> displayOrdersFromDate(String date) {
        return service.ordersWithDate(date);
    }

    public void save(ArrayList<String> dates) {
        try {
            service.save(dates);
        } catch (PersistenceException e) {
            view.fileNotFound("file could not be saved");
        }
    }

    public Order calculateEverything(Order newOrder) {

        return service.calculate(newOrder);
    }

    public Order edit(int selection, ArrayList<Order> dateOrders) {

        //TODO:put all variables in to order and pass that in to the edit method in stead of all the pieces
        
        String areaString;
        BigDecimal area;
        int keep;
        Order editedOrder;
        Order originalOrder;
        Order order = new Order();
        boolean validState = false;
        boolean validProduct = false;
        boolean dateRes = false;
        boolean validSelection = false;
        BigDecimal zero = new BigDecimal(0);
        ArrayList allProducts;
        ArrayList allStates;
        ArrayList<Order> allOrders;
        String date;
        String product;
        String state;

        originalOrder = dateOrders.get(selection - 1);

        int userSelection = selection;
        String name = view.editCustomerName(originalOrder.getCustomeName());
        if (name.equalsIgnoreCase("")) {
            name = originalOrder.getCustomeName();
        }
        //TODO: check if name contains a comma if so...account for that

        do {
            state = view.editState(originalOrder.getState());
            if (state.equalsIgnoreCase("")) {
                state = originalOrder.getState();
            }
            validState = service.validateState(state);
            if (validState == false) {
                allStates = service.getAllStates();
                view.wrongStateDisplayAll(allStates);
            }
        } while (!validState);

        do {
            product = view.editProductType(originalOrder.getProductType());
            if (product.equalsIgnoreCase("")) {
                product = originalOrder.getProductType();
            }
            validProduct = service.validateProduct(product);
            if (validProduct != true) {
                allProducts = service.getAllProducts();
                view.wrongProductDisplayAll(allProducts);
            }
        } while (validProduct == false);

        do {
            date = view.editDate(originalOrder.getDate());
            if (date.equalsIgnoreCase("")) {
                date = originalOrder.getDate();
            }
            dateRes = service.validateDateEdit(date);

            if (!dateRes) {
                view.invalidate();
            }
        } while (!dateRes);

        do {
            try {
                areaString = view.editArea(originalOrder.getArea());
                if (areaString.equalsIgnoreCase("")) {
                    areaString = originalOrder.getArea().toString();
                }
                area = new BigDecimal(areaString);

            } catch (NumberFormatException e) {
                area = null;
            }
            if (area.compareTo(zero) <= 0) {
                view.needBiggerArea();
            }
        } while (area.compareTo(zero) <= 0);
        
        
        order.setCustomeName(name);
        order.setState(state);
        order.setProductType(product);
        order.setArea(area);
        order.setDate(date);

        editedOrder = service.editOrder(userSelection, order);
        keep = view.confirmEdit(editedOrder);
        if (keep == 1) {
            removeOrder(originalOrder);
            service.addOrder(editedOrder);
        }

        return editedOrder;
    }

    public Order confirmDelete(int selection, ArrayList blankList) {
        return service.findOrder(selection, blankList);
    }

    public void removeOrder(Order order) {
        service.removeOrder(order);
    }
    
    public ArrayList<String> getStartingDates(){
        ArrayList<Order> orders = service.getAllOrders();
        ArrayList<String> dates = new ArrayList();
        for(Order currentOrder: orders){
            String date = currentOrder.getDate();
            StringBuilder unSlash = new StringBuilder(date).deleteCharAt(5);
            unSlash.deleteCharAt(2);
            String newDate = unSlash.toString();
            dates.add(newDate);
            
        }
        return dates;
    }

}
