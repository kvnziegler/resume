/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Product;
import com.sg.flooring.exceptions.PersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author kvnzi
 */
public class ProductDaoStubImpl implements ProductDao{

    private Product onlyProduct;
    private ArrayList<Product> productList = new ArrayList();
    
    public ProductDaoStubImpl(){
        BigDecimal cpsf = new BigDecimal(14.11);
        BigDecimal lcpsf = new BigDecimal(7.29);
        onlyProduct = new Product();
        onlyProduct.setCostPerSquareFoot(cpsf);
        onlyProduct.setLaborCostPerSquareFoot(lcpsf);
        onlyProduct.setProductType("Nonus");
        
        productList.add(onlyProduct);
    }
    
    @Override
    public void loadProducts() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeProducts() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product addProduct(String name, Product product) {
        if (name.equals(onlyProduct.getProductType())) {            
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Product> getProducts() {
        return productList;
    }

    @Override
    public Product getProduct(String name) {
        if (name.equals(onlyProduct.getProductType())) {            
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public Product editProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product removeProduct(Product product) {
        if (product.equals(onlyProduct)) {            
            return onlyProduct;
        } else {
            return null;
        }
    }
    
}
