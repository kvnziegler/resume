/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Tax;
import com.sg.flooring.exceptions.PersistenceException;
import java.util.ArrayList;
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
public class TaxDaoTest {
    
    
    TaxDao testDao = new TaxDaoImpl();
    public TaxDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ArrayList<Tax> taxList = testDao.getTaxes();
        for (Tax tax : taxList) {
            testDao.remove(tax,taxList);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadTaxes method, of class TaxDao.
     */
    @Test
    public void testLoadTaxes() throws Exception {
        
        testDao.loadTaxes();
        ArrayList<Tax> taxes = testDao.getTaxes();
    }

    /**
     * Test of writeStock method, of class TaxDao.
     */
    @Test
    public void testWriteStock() throws Exception {
    }

    /**
     * Test of createTaxRate method, of class TaxDao.
     */
    @Test
    public void testCreateTaxRate() {
        Tax tax = new Tax();
        tax.setState("OH");
        testDao.createTaxRate("OH", tax);
        
        assertEquals("OH", tax.getState());
    }

    /**
     * Test of getTaxes method, of class TaxDao.
     */
    @Test
    public void testGetTaxes() {
        Tax tax = new Tax();
        testDao.createTaxRate("OH",tax);
        
        ArrayList taxes = testDao.getTaxes();
        
        assertEquals(1, taxes.size());
    }

    /**
     * Test of getTax method, of class TaxDao.
     */
    @Test
    public void testGetTax() {
        Tax tax = new Tax();
        tax.setState("OH");
        testDao.createTaxRate("OH",tax);
        
        Tax gottenTax = testDao.getTax("OH");
        
        assertEquals(tax, gottenTax);
    }

    /**
     * Test of editItem method, of class TaxDao.
     */
    @Test
    public void testEditItem() {
    }

    /**
     * Test of removeItem method, of class TaxDao.
     */
    @Test
    public void testRemoveItem() {
        Tax tax = new Tax();
        tax.setState("OH");
        ArrayList taxList = testDao.getTaxes();
         
        assertEquals(0,taxList.size());
        
        testDao.createTaxRate("OH",tax);
        
        taxList = testDao.getTaxes();
        
        assertEquals(1, taxList.size());
        
        
        taxList.remove(tax);
        
        assertEquals(0, taxList.size());
    }

    
}
