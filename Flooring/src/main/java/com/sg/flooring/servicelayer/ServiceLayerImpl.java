/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.servicelayer;

import com.sg.flooring.dao.OrderDao;
import com.sg.flooring.dao.ProductDao;
import com.sg.flooring.dao.TaxDao;
import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.Tax;
import com.sg.flooring.exceptions.PersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author kvnzi
 */
public class ServiceLayerImpl implements ServiceLayer {

    private OrderDao orderDao;
    private TaxDao taxDao;
    private ProductDao productDao;

    //need to add ependancey in xml file
    public ServiceLayerImpl(OrderDao orderDao, TaxDao taxDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.taxDao = taxDao;
        this.productDao = productDao;

    }

    @Override
    public void load() throws PersistenceException {
        orderDao.loadOrders();
    }

    @Override
    public void loadTaxes() throws PersistenceException {
        taxDao.loadTaxes();
    }

    @Override
    public void loadProducts() throws PersistenceException {
        productDao.loadProducts();
    }

    @Override
    public boolean validateDate(String date) {
        boolean result;
        boolean valid = true;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (!(date == null) && !(date.equals(""))) {
            try {
                Date dateForm = formatter.parse(date);
                result = true;
            } catch (ParseException e) {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public ArrayList<Order> ordersWithDate(String date) {

        ArrayList<Order> allOrders = orderDao.getOrders();
        ArrayList ordersWithDate = new ArrayList();
        for (Order currentOrder : allOrders) {
            if (currentOrder.getDate().toString().equals(date.toString())) {
                ordersWithDate.add(currentOrder);
            }
        }
        return ordersWithDate;
    }

    public Order calculate(Order newOrder) {
        Order calcOrder = new Order();

        String name = newOrder.getCustomeName();
        String state = newOrder.getState();
        String productType = newOrder.getProductType();
        BigDecimal area = newOrder.getArea();
        String date = newOrder.getDate();

        ArrayList<Order> allOrders = new ArrayList();
        allOrders = orderDao.getOrders();
        int orderNumber = 0;
        for (Order currentOrder : allOrders) {
            if (currentOrder.getOrderNumber() >= orderNumber) {
                orderNumber = currentOrder.getOrderNumber() + 1;
            }
        }

        BigDecimal taxRate;
        taxRate = taxDao.getTax(state).getTaxRate();

        BigDecimal costPerSqurFoot;
        costPerSqurFoot = productDao.getProduct(productType).getCostPerSquareFoot();
        //nullpointer exception^^

        BigDecimal laborCostPerSqurFoot;
        laborCostPerSqurFoot = productDao.getProduct(productType).getLaborCostPerSquareFoot();

        BigDecimal laborCostTotal;
        laborCostTotal = laborCostPerSqurFoot.multiply(area);

        BigDecimal materialCostTotal;
        materialCostTotal = costPerSqurFoot.multiply(area);

        BigDecimal hundo = new BigDecimal(100);

        BigDecimal taxes;
        taxes = (laborCostTotal.add(materialCostTotal)).multiply(taxDao.getTax(state).getTaxRate().divide(hundo));

        BigDecimal totalCost;
        totalCost = laborCostTotal.add(materialCostTotal).add(taxes);

        calcOrder.setOrderNumber(orderNumber);
        calcOrder.setCustomeName(name);
        calcOrder.setState(state);
        calcOrder.setTaxRate(taxRate);
        calcOrder.setProductType(productType);
        calcOrder.setArea(area);
        calcOrder.setCostPerSquareFoot(costPerSqurFoot);
        calcOrder.setLaborCostPerSquareFoot(laborCostPerSqurFoot);
        calcOrder.setLaborCost(laborCostTotal);
        calcOrder.setMaterialCost(materialCostTotal);
        calcOrder.setTax(taxes);
        calcOrder.setTotal(totalCost);

        calcOrder.setDate(date);

        return calcOrder;
    }


    public Order editOrder(int userSelection, Order order) {
        
        Order editedOrder = new Order();
        Order tempOrder = new Order();

        ArrayList<Order> allOrders = orderDao.getOrders();
        ArrayList<Order> otherList = new ArrayList();

        for (Order currentOrder : allOrders) {
            otherList.add(currentOrder);
        }
        for (Order currentOrder : otherList) {
            if (currentOrder.getOrderNumber() == userSelection) {
                tempOrder = currentOrder;
            }
        }

        if (order.getCustomeName() == null || order.getCustomeName().equals("")) {
            editedOrder.setCustomeName(tempOrder.getCustomeName());
        } else {
            editedOrder.setCustomeName(order.getCustomeName());
        }
        if (order.getState() == null || order.getState().equals("")) {
            editedOrder.setState(tempOrder.getState());
        } else {
            editedOrder.setState(order.getState());
        }
        if (order.getProductType() == null || order.getProductType().equals("")) {
            editedOrder.setProductType(tempOrder.getProductType());
        } else {
            editedOrder.setProductType(order.getProductType());
        }
        if (order.getArea() == null || order.getArea().equals("")) {
            editedOrder.setArea(tempOrder.getArea());
        } else {
            editedOrder.setArea(order.getArea());
        }
        if (order.getDate() == null || order.getDate().equals("")) {
            editedOrder.setDate(tempOrder.getDate());
        } else {
            editedOrder.setDate(order.getDate());
        }

        Order calcOrder = calculate(editedOrder);
        return calcOrder;

        //once all new info is set up get rid of old object and add new one to the list
    }

    @Override
    public Order findOrder(int selection, ArrayList<Order> blankList) {
        Order order = new Order();
        for (Order currentOrder : blankList) {
            if (blankList.indexOf(currentOrder) == selection - 1) {
                order = currentOrder;

            }
        }
        return order;
    }

    @Override
    public Order removeOrder(Order order) {
         return orderDao.removeOrder(order);
    }

    @Override
    public Order addOrder(Order calcOrder) {
        int orderNumber = calcOrder.getOrderNumber();
        return orderDao.createOrder(orderNumber, calcOrder);
    }

    @Override
    public void save(ArrayList<String> dates) throws PersistenceException {
        orderDao.writeOrders(dates);
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> allOrders = new ArrayList();
        ArrayList<Order> daoOrders = orderDao.getOrders();
        for (Order currentOrder : daoOrders) {
            allOrders.add(currentOrder);
        }
        return allOrders;
    }

    @Override
    public boolean readMode() throws PersistenceException {
        Boolean isTraining = true;
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader("config.txt")));
        } catch (FileNotFoundException e) {
            throw new PersistenceException("could not load orders", e);
        }
        String currentLine;
        currentLine = scanner.nextLine();
        isTraining = !currentLine.equals("mode=Production");
        scanner.close();
        return isTraining;

    }

    @Override
    public Order getOrder(int userChoice) {
        return orderDao.getOrder(userChoice);
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        return productDao.getProducts();
    }

    @Override
    public ArrayList<Tax> getAllStates() {
        return taxDao.getTaxes();
    }

    @Override
    public boolean validateState(String state) {
        boolean ifValid;
        ArrayList<Tax> stateList;
        int validState = 0;
        int total = 0;
        stateList = taxDao.getTaxes();
        for (Tax currentTax : stateList) {
            if (currentTax.getState().equalsIgnoreCase(state)) {
                validState = 1;
            } else {
                validState = 0;
            }
            total += validState;
        }

        if (total > 0) {
            return ifValid = true;
        } else {
            return ifValid = false;
        }
    }

    @Override
    public boolean validateProduct(String product) {
        boolean ifValid;
        ArrayList<Product> productList;
        int validProduct = 0;
        int total = 0;
        productList = productDao.getProducts();
        for (Product currentProduct : productList) {
            if (currentProduct.getProductType().equalsIgnoreCase(product)) {
                validProduct = 1;
            } else {
                validProduct = 0;
            }
            total += validProduct;
        }

        if (total > 0) {
            return ifValid = true;
        } else {
            return ifValid = false;
        }
    }

    @Override
    public boolean validateDateEdit(String date) {
        boolean result;

        if (!(date == null) && !(date.equals(""))) {

            result = true;

        } else {
            result = false;
        }
        return result;
    }
}
