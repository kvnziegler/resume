/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Product;
import com.sg.flooring.exceptions.PersistenceException;
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
public class ProductDaoTest {

    ProductDao testDao = new ProductDaoImpl();

    public ProductDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ArrayList<Product> productList = testDao.getProducts();
        for (Product product : productList) {
            testDao.removeProduct(product);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of loadProducts method, of class ProductDao.
     */
    @Test
    public void testLoadProducts() throws Exception {
         testDao.loadProducts();
        List<Product> product = testDao.getProducts();
    }

    /**
     * Test of writeProducts method, of class ProductDao.
     */
    @Test
    public void testWriteProducts() throws Exception {
    }

    /**
     * Test of addProduct method, of class ProductDao.
     */
    @Test
    public void testAddProduct() {             
        
        Product product = new Product();
        product.setProductType("Wood");
        testDao.addProduct("Wood", product);
        
        assertEquals("Wood", product.getProductType());
    }

    /**
     * Test of getProducts method, of class ProductDao.
     */
    @Test
    public void testGetProducts() {
        Product product = new Product();
        testDao.addProduct("Wood", product);
        
        ArrayList products = testDao.getProducts();
        
        assertEquals(1, products.size());
    }

    /**
     * Test of getProduct method, of class ProductDao.
     */
    @Test
    public void testGetProduct() {
         Product product = new Product();
        testDao.addProduct("Wood", product);
        
        Product gottenProduct = testDao.getProduct("Wood");
        
        assertEquals(product, gottenProduct);
    }

    /**
     * Test of editProduct method, of class ProductDao.
     */
    @Test
    public void testEditProduct() {
    }

    /**
     * Test of removeProduct method, of class ProductDao.
     */
    @Test
    public void testRemoveProduct() {
        Product product = new Product();
        ArrayList productList = testDao.getProducts();
         
        assertEquals(0,productList.size());
        
        testDao.addProduct("Wood", product);
        
        productList = testDao.getProducts();
        
        assertEquals(1, productList.size());
        
        Product newProduct = testDao.getProduct("Wood");
        
        
        productList.remove(product);
        
        
        assertEquals(0, productList.size());
    }


}
