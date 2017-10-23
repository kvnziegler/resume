/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author kvnzi
 */
public class Tax {
    
    //TODO: all math on how much taxes are going to cost in this DTO
    
    private String state;
    private BigDecimal taxRate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate.setScale(2,RoundingMode.HALF_UP);
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate.setScale(2,RoundingMode.HALF_UP);
    }
    
}
