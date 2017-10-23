/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import static com.sg.flooring.dao.ProductDaoImpl.DELIMITER;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.Tax;
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
public class TaxDaoImpl implements TaxDao {

    public static final String DELIMITER = ",";
    private Map<String, Tax> taxRateByState = new HashMap<>();

    @Override
    public void loadTaxes() throws PersistenceException {
        Scanner sc;


                try {
                    sc = new Scanner(new BufferedReader(new FileReader("Taxes.txt")));
                } catch (FileNotFoundException e) {
                    throw new PersistenceException("could not load products", e);
                }

                String currentString;
                String[] currentTokens;

                while (sc.hasNextLine()) {
                    currentString = sc.nextLine();
                    currentTokens = currentString.split(DELIMITER);

                    Tax currentTax = new Tax();

                    currentTax.setState(currentTokens[0]);
                    currentTax.setTaxRate(new BigDecimal(currentTokens[1]));

                    taxRateByState.put(currentTax.getState(), currentTax);
                }
                sc.close();
            }
        
    

    @Override
    public void writeStock() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax createTaxRate(String state, Tax tax) {
    taxRateByState.put(state, tax);
        return tax;    
    }

    @Override
    public ArrayList<Tax> getTaxes() {    
        return new ArrayList<Tax>(taxRateByState.values());
            
        
    }

    @Override
    public Tax getTax(String state) {
        return taxRateByState.get(state);
        
        //need to get a single tax rate by key of state
    }

    @Override
    public Tax editItem(Tax tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax remove(Tax tax, ArrayList taxes) {
        taxRateByState.remove(tax.getState());
        return taxRateByState.get(tax);
        }

}
