/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Stock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kvnzi
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    private Map<Integer, Stock> stockMap = new HashMap<>();

    public VendingMachineDaoStubImpl() {
        Stock stock = new Stock();

        stock.setId(1);
        stock.setName("snickers");
        stock.setPrice(1.95);
        stock.setStock(25);
        stockMap.put(stock.getId(), stock);
    }

    private static int stockIdCounter = 0;

    @Override
    public Stock addStock(Stock stock) {

        stock.setId(stockIdCounter);

        stockIdCounter++;
        stockMap.put(stock.getId(), stock);
        return stock;
    }

    @Override
    public void removeStock(int stockId) {
        stockMap.remove(stockId);
    }

    @Override
    public void updateStock(Stock stock) {
        stockMap.put(stock.getId(), stock);
    }

    @Override
    public List<Stock> getAllStock() {
        Collection<Stock> c = stockMap.values();
        return new ArrayList(c);
    }

    @Override
    public Stock getStockById(int stockId) {
        return stockMap.get(stockId);
    }

}
