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
public class VendingMachineDaoImpl implements VendingMachineDao {

    private Map<Integer, Stock> stockMap = new HashMap<>();

    public VendingMachineDaoImpl() {
        Stock stock = new Stock();

        stock.setId(1);
        stock.setName("snickers");
        stock.setPrice(1.95);
        stock.setStock(25);
        stockMap.put(stock.getId(), stock);
        
        Stock stock2 = new Stock();

        stock2.setId(2);
        stock2.setName("BullyMong");
        stock2.setPrice(10.00);
        stock2.setStock(2);
        stockMap.put(stock2.getId(), stock2);
        
        Stock stock3 = new Stock();

        stock3.setId(3);
        stock3.setName("Varkid");
        stock3.setPrice(7.50);
        stock3.setStock(17);
        stockMap.put(stock3.getId(), stock3);
        
        Stock stock4 = new Stock();

        stock4.setId(4);
        stock4.setName("Oinion Dip");
        stock4.setPrice(0.75);
        stock4.setStock(0);
        stockMap.put(stock4.getId(), stock4);
        
        Stock stock5 = new Stock();

        stock5.setId(5);
        stock5.setName("Gas Station Sushi");
        stock5.setPrice(0.12);
        stock5.setStock(1);
        stockMap.put(stock5.getId(), stock5);
        
        Stock stock6 = new Stock();

        stock6.setId(6);
        stock6.setName("not a fake watch...");
        stock6.setPrice(2000);
        stock6.setStock(20);
        stockMap.put(stock6.getId(), stock6);
        
        Stock stock7 = new Stock();

        stock7.setId(7);
        stock7.setName("The Spanish Inquisition");
        stock7.setPrice(3.00);
        stock7.setStock(1);
        stockMap.put(stock7.getId(), stock7);
        
        Stock stock8 = new Stock();

        stock8.setId(8);
        stock8.setName("I didn't expect that");
        stock8.setPrice(3.75);
        stock8.setStock(18);
        stockMap.put(stock8.getId(), stock8);
        
        Stock stock27 = new Stock();

        stock27.setId(27);
        stock27.setName("loaded dice");
        stock27.setPrice(7.00);
        stock27.setStock(13);
        stockMap.put(stock27.getId(), stock27);
        
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
