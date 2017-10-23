/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Stock;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kvnzi
 */
public class VendingMachineDaoTest {

    private VendingMachineDao dao;

    public VendingMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("VendingMachineDao", VendingMachineDao.class);

        //dlear stock list
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addStock method, of class VendingMachineDao.
     */
    @Test
    public void testAddStock() {
        Stock stock = new Stock();

        stock.setId(12);
        stock.setName("snickers");
        stock.setPrice(1.95);
        stock.setStock(25);

        dao.addStock(stock);
        Stock fromDb = dao.getStockById(stock.getId());
        assertEquals(fromDb, stock);
        dao.removeStock(stock.getId());
        assertNull(dao.getStockById(stock.getId()));

    }

    /**
     * Test of removeStock method, of class VendingMachineDao.
     */
    @Test
    public void testRemoveStock() {
        Stock stock = new Stock();

        stock.setId(12);
        stock.setName("snickers");
        stock.setPrice(1.95);
        stock.setStock(25);

        dao.addStock(stock);
        Stock fromDb = dao.getStockById(stock.getId());
        assertEquals(fromDb, stock);
        dao.removeStock(stock.getId());
        assertNull(dao.getStockById(stock.getId()));
    }

    /**
     * Test of updateStock method, of class VendingMachineDao.
     */
    @Test
    public void testUpdateStock() {

        Stock stock = new Stock();

        stock.setId(12);
        stock.setName("snickers");
        stock.setPrice(1.95);
        stock.setStock(25);

        dao.addStock(stock);
        stock.setName("crunch");
        dao.updateStock(stock);
        Stock fromDb = dao.getStockById(stock.getId());
        assertEquals(fromDb, stock);
    }

    /**
     * Test of getAllStock method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllStock() {

        List allStock = dao.getAllStock();

        assertEquals(1, allStock.size());
    }

    /**
     * Test of getStockById method, of class VendingMachineDao.
     */
    @Test
    public void testGetStockById() {
        Stock stock = new Stock();

        stock.setId(12);
        stock.setName("snickers");
        stock.setPrice(1.95);
        stock.setStock(25);

        dao.addStock(stock);
        Stock fromDb = dao.getStockById(stock.getId());
        assertEquals(fromDb, stock);

    }

}
