/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Product;
import com.sg.flooring.exceptions.PersistenceException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kvnzi
 */
public class ProductDaoImpl implements ProductDao {

    public static final String DELIMITER = ",";
    private HashMap<String, Product> productByType = new HashMap<>();

    @Override
    public void loadProducts() throws PersistenceException {
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader("Products.txt")));
        } catch (FileNotFoundException e) {
            throw new PersistenceException("could not load products", e);
        }

        String currentString;
        String[] currentTokens;

        while (sc.hasNextLine()) {
            currentString = sc.nextLine();
            currentTokens = currentString.split(DELIMITER);

            Product currentproduct = new Product();

            currentproduct.setProductType(currentTokens[0]);
            currentproduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            //ArrayIndexOutOfBoundsException^^^
            currentproduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));

            productByType.put(currentproduct.getProductType(), currentproduct);
        }
        sc.close();
    }

    @Override
    public void writeProducts() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product addProduct(String name,Product product) {
    productByType.put(name, product);
        return product;    
    }

    @Override
    public ArrayList<Product> getProducts() {
        return new ArrayList<Product>(productByType.values());
    }

    @Override
    public Product getProduct(String name) {
        return productByType.get(name);
    }

    @Override
    public Product editProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product removeProduct(Product product) {
        productByType.remove(product.getProductType());
        return productByType.get(product);
    }

}
