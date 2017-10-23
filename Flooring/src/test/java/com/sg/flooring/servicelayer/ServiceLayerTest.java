/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.servicelayer;

import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kvnzi
 */
public class ServiceLayerTest {
    
    private ServiceLayer service;
        
    public ServiceLayerTest() {
        
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("testApplicationContext.xml");
        service = 
                ctx.getBean("ServiceLayer", ServiceLayer.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of load method, of class ServiceLayer.
     */
    @Test
    public void testLoad() throws Exception {
    }

    /**
     * Test of loadProducts method, of class ServiceLayer.
     */
    @Test
    public void testLoadProducts() throws Exception {
    }

    /**
     * Test of loadTaxes method, of class ServiceLayer.
     */
    @Test
    public void testLoadTaxes() throws Exception {
    }

    /**
     * Test of validateDate method, of class ServiceLayer.
     */
    @Test
    public void testValidateDate() {
        String date = "09/18/2011";
        boolean result = service.validateDate(date);
        
        assertEquals(true, result);
    }
    
    @Test
    public void testValidateDateBad() {
        String date = "09182011";
        boolean result = service.validateDate(date);
        
        assertEquals(false, result);
    }

    /**
     * Test of ordersWithDate method, of class ServiceLayer.
     */
    @Test
    public void testOrdersWithDate() {
        String date = "02/14/2017";
        
         boolean res = service.validateDate(date);
        
        assertEquals(true, res);
    }
    
    @Test
    public void testOrdersWithDatebadformat() {
        String date = "02142000";
        
         boolean res = service.validateDate(date);
        
        assertEquals(false, res);
    }

    /**
     * Test of calculate method, of class ServiceLayer.
     */
    @Test
    public void testCalculate() {
        BigDecimal one = new BigDecimal(100);
        
        Order order = new Order();
        order.setCustomeName("Bob");
        order.setProductType("Nonus");
        order.setState("OH");
        order.setArea(one);
        order.setDate("03/23/1985");
        
        Order calcOrder = service.calculate(order);
        
        BigDecimal biggie = new BigDecimal(6.25);
        
        assertEquals(biggie, calcOrder.getTaxRate());
    }
    
    @Test
    public void testCalculateCost() {
        BigDecimal one = new BigDecimal(100);
        
        Order order = new Order();
        order.setCustomeName("Bob");
        order.setProductType("Nonus");
        order.setState("OH");
        order.setArea(one);
        order.setDate("03/23/1985");
        
        Order calcOrder = service.calculate(order);
        
        BigDecimal biggie = new BigDecimal(14.11);
        
        assertEquals("Bob", calcOrder.getCustomeName());
    }

    /**
     * Test of editOrder method, of class ServiceLayer.
     */
    @Test
    public void testEditOrder() {
        BigDecimal area = new BigDecimal(100);
        Order order = new Order();
        order.setCustomeName("JimJam");
        order.setState("OH");
        order.setProductType("Nonus");
        order.setArea(area);
        order.setDate("02/14/2017");
        
                service.editOrder(223, order);
        
        assertEquals("JimJam", order.getCustomeName());
    }
    
     @Test
    public void testEditOrder2() {
        BigDecimal area = new BigDecimal(100);
         Order order = new Order();
        order.setCustomeName("JimJam");
        order.setState("OH");
        order.setProductType("Nonus");
        order.setArea(area);
        order.setDate("02/14/2017");
        
                service.editOrder(223, order);
        
        assertEquals("OH", order.getState());
    }

    /**
     * Test of OrigianlOrder method, of class ServiceLayer.
     */
//    @Test
//    public void testOrigianlOrder() {
//    }

    /**
     * Test of findOrder method, of class ServiceLayer.
     */
    @Test
    public void testFindOrder() {
        ArrayList<Order> orders = service.getAllOrders();
        
    Order order = service.findOrder(1, orders);
    
    assertEquals("Tasha Yar", order.getCustomeName());
                
    }
    
    @Test
    public void testFindOrderBad() {
        ArrayList<Order> orders = service.getAllOrders();
        
    Order order = service.findOrder(223, orders);
    
    assertEquals(null, order.getCustomeName());
                
    }

    /**
     * Test of removeOrder method, of class ServiceLayer.
     */
    @Test
    public void testRemoveOrder() {
        Order order = service.getOrder(223);
        
       Order gone =  service.removeOrder(order);
        
       assertEquals(order, gone);
    }

    /**
     * Test of addOrder method, of class ServiceLayer.
     */
    @Test
    public void testAddOrder() {
        Order order = new Order();
        
        service.addOrder(order);

    }

    /**
     * Test of save method, of class ServiceLayer.
     */
    @Test
    public void testSave() throws Exception {
    }

    /**
     * Test of getAllOrders method, of class ServiceLayer.
     */
    @Test
    public void testGetAllOrders() {
        ArrayList<Order> order = service.getAllOrders();
        
        assertEquals(1, order.size());
    }
    
    @Test
    public void testGetAllOrdersexact() {
        ArrayList<Order> order = service.getAllOrders();
        
        assertEquals("Tasha Yar", order.get(0).getCustomeName());
    }

    /**
     * Test of readMode method, of class ServiceLayer.
     */
    @Test
    public void testReadMode() throws Exception {
    }

    /**
     * Test of getOrder method, of class ServiceLayer.
     */
    @Test
    public void testGetOrder() {
        Order order = service.getOrder(223);
        
        assertEquals("Tasha Yar", order.getCustomeName());
    }
    
     @Test
    public void testGetOrderBad() {
        Order order = service.getOrder(0);
        
        assertNull(order);
    }

    /**
     * Test of getAllProducts method, of class ServiceLayer.
     */
    @Test
    public void testGetAllProducts() {
        ArrayList<Product> product = service.getAllProducts();
        
        assertEquals(1, product.size());
    }

    @Test
    public void testGetAllProductsexact() {
        ArrayList<Product> product = service.getAllProducts();
        
        assertEquals("Nonus", product.get(0).getProductType());
    }
    
    /**
     * Test of getAllStates method, of class ServiceLayer.
     */
    @Test
    public void testGetAllStates() {
        ArrayList<Tax> state = service.getAllStates();
        
        assertEquals(1, state.size());
    }

    @Test
    public void testGetAllStatesexact() {
        ArrayList<Tax> state = service.getAllStates();
        
        assertEquals("OH", state.get(0).getState());
    }
    
    /**
     * Test of validateState method, of class ServiceLayer.
     */
    @Test
    public void testValidateState() {
        String oh = "OH";
        
        boolean result = service.validateState(oh);
        
        assertEquals(true, result);
    }
    
    @Test
    public void testValidateStateBad() {
        String oh = "CA";
        
        boolean result = service.validateState(oh);
        
        assertEquals(false, result);
    }
    
    @Test
    public void testValidateStateRealBad() {
        String oh = "";
        
        boolean result = service.validateState(oh);
        
        assertEquals(false, result);
    }

    /**
     * Test of validateProduct method, of class ServiceLayer.
     */
    @Test
    public void testValidateProduct() {
        String product = "Nonus";
        
        boolean result = service.validateProduct(product);
        
        assertEquals(true, result);
    }
    
    @Test
    public void testValidateProductBad() {
        String product = "Wood";
        
        boolean result = service.validateProduct(product);
        
        assertEquals(false, result);
    }

    /**
     * Test of validateDateEdit method, of class ServiceLayer.
     */
    @Test
    public void testValidateDateEdit() {
        String date = "02/14/2017";
        
        boolean result = service.validateDateEdit(date);
        
        assertEquals(true, result);
    }

   @Test
    public void testValidateDateEditbad() {
        String date = "";
        
        boolean result = service.validateDateEdit(date);
        
        assertEquals(false, result);
    }
    
}
