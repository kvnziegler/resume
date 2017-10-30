/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.controller;

import com.sg.hero.dao.HeroDao;
import com.sg.hero.model.Contact;
import com.sg.hero.model.Power;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author kvnzi
 */
@Controller
public class newPowerContoller {

    private HeroDao dao;

    @Inject
    public newPowerContoller(HeroDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/newPower", method = RequestMethod.GET)
    public String displayNewPower(Model model) {
        List<Power> powers = dao.getAllPowers();
        model.addAttribute("powerList", powers);

        return "newPower";
    }

    @RequestMapping(value = "/createPower", method = RequestMethod.POST)
    public String createPower(HttpServletRequest request, Model model) {
        // grab the incoming values from the form and create a new Contact
        // object

        Power power = new Power();
        power.setName(request.getParameter("powerName"));
        power.setPowerDesc(request.getParameter("powerDesc"));

        dao.addPower(power);

        List<Power> powers = dao.getAllPowers();
        model.addAttribute("powerList", powers);

        return "newPower";
    }

    @RequestMapping(value = "/deletePower", method = RequestMethod.GET)
    public String deletePower(HttpServletRequest request, Model model) {

        String powerIdParameter = request.getParameter("powerId");
        int powerId = Integer.parseInt(powerIdParameter);
        Power power = dao.getPowerById(powerId);

        dao.deletePower(power);

        List<Power> powers = dao.getAllPowers();
        model.addAttribute("powerList", powers);

        return "newPower";
    }

}
