/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Stock;
import static java.lang.System.console;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author kvnzi
 */
@Controller
public class VendingMachineController {

    private VendingMachineDao dao;

    @Inject
    public VendingMachineController(VendingMachineDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayIndexPage(Model model) {
        // Get all the stock from the DAO
        List<Stock> stockList = dao.getAllStock();

        // Put the List of stock on the Model
        model.addAttribute("stockList", stockList);

        // Return the logical name of our View component
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String editStock(HttpServletRequest request, Model model) {

        String itemID = request.getParameter("numberInput");
        String money = request.getParameter("moneyHere");
        try {
            BigDecimal moneyD = new BigDecimal(money);

            int itemIDint = 0;

            try {
                itemIDint = Integer.parseInt(itemID);

                BigDecimal cost = new BigDecimal(dao.getStockById(itemIDint).getPrice());
                int stockC = dao.getStockById(itemIDint).getStock();

                if (moneyD.compareTo(cost) >= 0 && stockC > 0) {
                    int stock = dao.getStockById(itemIDint).getStock();
                    int newStock = stock - 1;
                    dao.getStockById(itemIDint).setStock(newStock);

                    BigDecimal newMoney = moneyD.subtract(cost);
                    String thanks = "Thank You!!";
                    request.setAttribute("thanks", thanks);
                    request.setAttribute("newMoney", newMoney);
                }

                if (moneyD.compareTo(cost) < 0) {
                    BigDecimal difference = cost.subtract(moneyD);

                    String message = "please insert $" + difference.toString();
                    request.setAttribute("notEnoughMoney", message);

                    request.setAttribute("newMoney", moneyD);
                }

                if (stockC < 1) {
                    String noMo = "out of stock";
                    request.setAttribute("outOfStock", noMo);

                    request.setAttribute("newMoney", moneyD);
                }
            } catch (NumberFormatException e) {
                System.out.println("why didn't that integer parse?");
                //tell user they need to select an item
            }
        } catch (NumberFormatException e) {
            System.out.println("...stock messing with the form and just use the provided inputs");
        }

        List<Stock> stockList = dao.getAllStock();

        // Put the List of stock on the Model
        model.addAttribute("stockList", stockList);

        
        BigDecimal moneyD = new BigDecimal(money);
        // Return the logical name of our View component
        money = request.getParameter("moneyHere");
        moneyD = new BigDecimal(money);
        request.setAttribute("jumpTheGun", moneyD);

        return "index";

    }

}
