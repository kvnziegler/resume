/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kvnzi
 */
public class OrderDaoImplTest {
    
    OrderDao testDao = new OrderDaoImpl();
    
    public OrderDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         ArrayList<Order> orderList = testDao.getOrders();
        for (Order order : orderList) {
            testDao.removeOrder(order);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadOrders method, of class OrderDaoImpl.
     */
    @Test
    public void testLoadOrders() throws Exception {
        
    }

    /**
     * Test of writeOrders method, of class OrderDaoImpl.
     */
    @Test
    public void testWriteOrders() throws Exception {
    }

    /**
     * Test of createOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testCreateOrder() {
         Order order = new Order();
        testDao.createOrder(0, order);
        
        assertEquals(0, order.getOrderNumber());
    }

    /**
     * Test of getOrders method, of class OrderDaoImpl.
     */
    @Test
    public void testGetOrders() {
        Order order = new Order();
        testDao.createOrder(0, order);
        
        ArrayList orders = testDao.getOrders();
        
        assertEquals(1, orders.size());
    }

    /**
     * Test of getOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testGetOrder() {
        Order order = new Order();
        testDao.createOrder(0, order);
        
        Order gottenOrder = testDao.getOrder(0);
        
        assertEquals(order, gottenOrder);
    }

    /**
     * Test of editOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testEditOrder() {
//        currently unsupported
//        Order order = new Order();
//        testDao.createOrder(0, order);
//        
//        testDao.editOrder(0).setCustomeName("bob");
//        
//        assertEquals("bob", order.getCustomeName());
    }

    /**
     * Test of removeOrder method, of class OrderDaoImpl.
     */
    @Test
    public void testRemoveOrder() {
        Order order = new Order();
         ArrayList orderList = testDao.getOrders();
         
        assertEquals(0,orderList.size());
        
        testDao.createOrder(0, order);
        
        orderList = testDao.getOrders();
        
        assertEquals(1, orderList.size());
        
        
        orderList.remove(order);
        //item is never removed...wtf
        
        assertEquals(0, orderList.size());
    }
    
}
