/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.servicelayer;

import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.Tax;
import com.sg.flooring.exceptions.PersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kvnzi
 */
public interface ServiceLayer {
    
    
    public void load() throws PersistenceException;
    
    public void loadProducts() throws PersistenceException;
    
    public void loadTaxes() throws PersistenceException;
    
    public boolean validateDate(String date);
    
    public ArrayList<Order> ordersWithDate(String date);
    
    public Order calculate(Order newOrder);
    
    public Order editOrder(int userSelection, Order order);
      
    public Order findOrder(int userSelection, ArrayList<Order> blankList);
    
    public Order removeOrder(Order order);
    
    public Order addOrder(Order calcOrder);
    
    public void save(ArrayList<String> dates) throws PersistenceException;
    
    public ArrayList<Order> getAllOrders();
    
    public boolean readMode() throws PersistenceException;
    
    public Order getOrder(int userChoice);
    
    public ArrayList<Product> getAllProducts();
    
    public ArrayList<Tax> getAllStates();
    
    public boolean validateState(String state);
    
    public boolean validateProduct(String product);
    
    public boolean validateDateEdit(String date);
    
}
