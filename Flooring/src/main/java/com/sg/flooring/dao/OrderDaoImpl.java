/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import static com.sg.flooring.dao.ProductDaoImpl.DELIMITER;
import com.sg.flooring.dto.Order;
import com.sg.flooring.exceptions.PersistenceException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author kvnzi
 */
public class OrderDaoImpl implements OrderDao {

    public static final String DELIMITER = ",";
    private Map<Integer, Order> ordersByNumbers = new HashMap<>();

    @Override
    public void loadOrders() throws PersistenceException {
        Scanner sc;

        File orderDirectory = new File("./Orders/");

        File[] fileList = orderDirectory.listFiles();

        if (fileList != null) {
            for (File orderFile : fileList) {
                try {
                    sc = new Scanner(new BufferedReader(new FileReader(orderFile)));
                } catch (FileNotFoundException e) {
                    throw new PersistenceException("could not load orders", e);
                }
                String fileDateString = orderFile.getName();
                fileDateString = fileDateString.substring(7, 15);
                String slash = "/";

                StringBuffer strg1 = new StringBuffer(fileDateString);
                strg1.insert(2, slash);
                strg1.insert(5, slash);
                String dateformat = strg1.toString();
                String currentString;
                String[] currentTokens;

                while (sc.hasNextLine()) {
                    currentString = sc.nextLine();
                    currentTokens = currentString.split(DELIMITER);

                    Order currentOrder = new Order();

                    currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                    currentOrder.setCustomeName(currentTokens[1]);
                    currentOrder.setState(currentTokens[2]);
                    currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                    currentOrder.setProductType(currentTokens[4]);
                    currentOrder.setArea(new BigDecimal(currentTokens[5]));
                    currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                    currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                    currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
                    currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                    currentOrder.setTax(new BigDecimal(currentTokens[10]));
                    currentOrder.setTotal(new BigDecimal(currentTokens[11]));
                    currentOrder.setDate(dateformat);

                    ordersByNumbers.put(currentOrder.getOrderNumber(), currentOrder);
                }
                sc.close();
            }
        }
    }

    @Override
    public void writeOrders(ArrayList<String> dates) throws PersistenceException {
        PrintWriter out;

        //currently writing all orders to everyfile
        ArrayList<Order> allOrders = new ArrayList();
        ArrayList<Order> ordersWithDate = new ArrayList();
        allOrders.addAll(ordersByNumbers.values());

        //take all the dates that the app started with and delete the files with names containing those dates
        for (String date : dates) {
            File file = new File("./Orders/Orders_" + date + ".txt");
            file.delete();
        }
        //can create a variable to iterate over all the files in a folder and delete everythig in 
        //find all
        for (Order thisOrder : allOrders) {
            String cdate = thisOrder.getDate();
            StringBuilder newDate = new StringBuilder(cdate).deleteCharAt(5);
            newDate.deleteCharAt(2);

            File file = new File("./Orders/Orders_" + newDate + ".txt");
            try {
                out = new PrintWriter(new FileWriter(file,
                        new File("./Orders/Orders_" + newDate + ".txt").exists()));
            } catch (IOException e) {
                throw new PersistenceException("...wat", e);
            }
            //write all orders with a certain date to the corresponding file

            out.println(thisOrder.getOrderNumber() + DELIMITER
                    + thisOrder.getCustomeName() + DELIMITER
                    + thisOrder.getState() + DELIMITER
                    + thisOrder.getTaxRate() + DELIMITER
                    + thisOrder.getProductType() + DELIMITER
                    + thisOrder.getArea() + DELIMITER
                    + thisOrder.getCostPerSquareFoot() + DELIMITER
                    + thisOrder.getLaborCostPerSquareFoot() + DELIMITER
                    + thisOrder.getMaterialCost() + DELIMITER
                    + thisOrder.getLaborCost() + DELIMITER
                    + thisOrder.getTax() + DELIMITER
                    + thisOrder.getTotal());

            out.flush();
            out.close();
        }
    }

    @Override
    public Order createOrder(int orderNumber, Order order
    ) {
        ordersByNumbers.put(orderNumber, order);
        return order;
    }

    @Override
    public ArrayList<Order> getOrders() {
        return new ArrayList<Order>(ordersByNumbers.values());
    }

    @Override
    public Order getOrder(int selection
    ) {
        return ordersByNumbers.get(selection);
    }

    @Override
    public Order editOrder(int Selection
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(Order order
    ) {
        return ordersByNumbers.remove(order.getOrderNumber());

    }
}
