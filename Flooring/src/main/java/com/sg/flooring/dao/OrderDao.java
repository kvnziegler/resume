/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import com.sg.flooring.exceptions.PersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author kvnzi
 */
public interface OrderDao {

    //persistence
    void loadOrders() throws PersistenceException;

    public void writeOrders(ArrayList<String> dates) throws PersistenceException;

    //create
    public Order createOrder(int orderNumber, Order order);

    //read
    public ArrayList<Order> getOrders();

    public Order getOrder(int selection);

    //update
    public Order editOrder(int selection);

    //delete
    public Order removeOrder(Order order);
}
