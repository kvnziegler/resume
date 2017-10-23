/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Stock;
import java.util.List;

/**
 *
 * @author kvnzi
 */
public interface VendingMachineDao {
    // add the given Contact to the data store
    public Stock addStock(Stock stock);

    // remove the Contact with the given id from the data store
    public void removeStock(int stockId);

    // update the given Contact in the data store
    public void updateStock(Stock stock);

    // retrieve all Contacts from the data store
    public List<Stock> getAllStock();

    // retrieve the Contact with the given id from the data store
    public Stock getStockById(int stockId);
}
