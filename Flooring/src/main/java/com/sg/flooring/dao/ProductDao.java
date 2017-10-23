/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Product;
import com.sg.flooring.exceptions.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kvnzi
 */
public interface ProductDao {

    //persistence
    void loadProducts() throws PersistenceException;

    public void writeProducts() throws PersistenceException;

    //create
    public Product addProduct(String name, Product product);

    //read
    public ArrayList<Product> getProducts();

    public Product getProduct(String name);

    //update
    public Product editProduct(Product product);

    //delete
    public Product removeProduct(Product product);
}
