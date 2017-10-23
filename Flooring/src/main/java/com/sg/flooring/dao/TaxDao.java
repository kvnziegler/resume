/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;


import com.sg.flooring.dto.Tax;
import com.sg.flooring.exceptions.PersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kvnzi
 */
public interface TaxDao {
    
    //persistence
    void loadTaxes() throws PersistenceException;

    public void writeStock() throws PersistenceException;

    //create
    public Tax createTaxRate(String state, Tax tax);

    //read
    public ArrayList<Tax> getTaxes();
    //cannot return hasmap no returning interanls
    public Tax getTax(String state);

    //update
    public Tax editItem(Tax tax);

    //delete
    public Tax remove(Tax tax, ArrayList taxes);
}
