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

/**
 *
 * @author kvnzi
 */
public class TaxDaoStubImpl implements TaxDao{

    private Tax onlyTax;
    private ArrayList<Tax> taxList = new ArrayList();
    
    public TaxDaoStubImpl(){
        BigDecimal rate = new BigDecimal(6.25);
        onlyTax = new Tax();
        onlyTax.setState("OH");
        onlyTax.setTaxRate(rate);
        
        taxList.add(onlyTax);
    }
    
    
    @Override
    public void loadTaxes() throws PersistenceException {
        //not sure how to test for this
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeStock() throws PersistenceException {
        //not sure how to test for this
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax createTaxRate(String state, Tax tax) {
        if (state.equals(onlyTax.getState())) {
            return onlyTax;
        } else {
            return null;
        }       
    }

    @Override
    public ArrayList<Tax> getTaxes() {
        return taxList;
    }

    @Override
    public Tax getTax(String state) {
      if (state.equals(onlyTax.getState())) {
            return onlyTax;
        } else {
            return null;
        }       
    }

    @Override
    public Tax editItem(Tax tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax remove(Tax tax, ArrayList taxes) {
    if (tax.equals(onlyTax)) {
            return onlyTax;
        } else {
            return null;
        }       
    }
    
}
