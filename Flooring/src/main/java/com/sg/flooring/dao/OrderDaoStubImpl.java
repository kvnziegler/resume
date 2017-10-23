/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import com.sg.flooring.exceptions.PersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kvnzi
 */
public class OrderDaoStubImpl implements OrderDao{
    
    private Order onlyOrder;
    private ArrayList<Order> orderList = new ArrayList();
    
    public OrderDaoStubImpl(){
        onlyOrder = new Order();
        
        BigDecimal taxRate = new BigDecimal(5.00);
        BigDecimal area = new BigDecimal(197.00);
        BigDecimal cpsf = new BigDecimal(81);
        BigDecimal lcpsf = new BigDecimal(7);
        BigDecimal materialCost = new BigDecimal(15957);
        BigDecimal laborCost = new BigDecimal(1379);
        BigDecimal tax = new BigDecimal(866.80);
        BigDecimal total = new BigDecimal(18202.80);
        
        String date = "02142017";
        
        onlyOrder.setCustomeName("Tasha Yar");
        onlyOrder.setArea(area);
        onlyOrder.setCostPerSquareFoot(cpsf);
        onlyOrder.setDate("02/14/2017");
        onlyOrder.setLaborCost(laborCost);
        onlyOrder.setLaborCostPerSquareFoot(lcpsf);
        onlyOrder.setMaterialCost(materialCost);
        onlyOrder.setOrderNumber(223);
        onlyOrder.setProductType("Exotic Halosium");
        onlyOrder.setState("MO");
        onlyOrder.setTax(tax);
        onlyOrder.setTaxRate(taxRate);
        onlyOrder.setTotal(total);
        onlyOrder.setDate(date);
        
        orderList.add(onlyOrder);
    }

    @Override
    public void loadOrders() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeOrders(ArrayList<String> dates) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order createOrder(int orderNumber, Order order) {
        if (orderNumber == onlyOrder.getOrderNumber()) {            
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Order> getOrders() {
        return orderList;
    }

    @Override
    public Order getOrder(int selection) {
        if (selection == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }    
    }

    @Override
    public Order editOrder(int selection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(Order order) {
        if (order.equals(order)) {
            return onlyOrder;
        } else {
            return null;
        }   
    }
    
}
