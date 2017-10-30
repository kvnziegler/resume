/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.controller;

import com.sg.hero.dao.HeroDao;
import com.sg.hero.model.Champion;
import com.sg.hero.model.Power;
import java.util.ArrayList;
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
public class VillainController {
    private HeroDao dao;

    @Inject
    public VillainController(HeroDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/villain", method = RequestMethod.GET)
    public String displayVillainPage(Model model) {
        List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        
        List<Power> powers = dao.getAllPowers();
        model.addAttribute("powerList", powers);
        
        //have the home page be able to show all current villains
        
    return "villain";
    }
    
        @RequestMapping(value = "/createVillain", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request, Model model) {
        // grab the incoming values from the form and create a new Contact
        // object
        Champion champ = new Champion();
        champ.setName(request.getParameter("villainName"));
        champ.setChampionDesc(request.getParameter("villainDesc"));
        champ.setIsHero(false);

        String[] powersToAdd = request.getParameterValues("powersToAdd");
        List<Power> allPowers = dao.getAllPowers();
        List<Power> addPowers = new ArrayList<>();

        for (String currentPower : powersToAdd) {
            for (Power power : allPowers) {
                if ((Integer.parseInt(currentPower)) == power.getPowerID()) {
                    Power powerToAdd = dao.getPowerById(Integer.parseInt(currentPower));
                    addPowers.add(powerToAdd);
                }
            }
        }
        
        champ.setPowers(addPowers);

        dao.addChampion(champ);
        
        
        
        //TODO: still creating a new power everytime a champion is added
        
        
        
        
        List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        
        List<Power> powers = dao.getAllPowers();
        model.addAttribute("powerList", powers);
        
        //have the home page be able to show all current villains
        
    return "villain";
    }

    @RequestMapping(value = "/deleteVillain", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request, Model model) {

        String heroIdParameter = request.getParameter("villainId");
        int champId = Integer.parseInt(heroIdParameter);
        Champion champ = dao.getChampionById(champId);

        dao.deleteChampion(champ);

        List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        
        List<Power> powers = dao.getAllPowers();
        model.addAttribute("powerList", powers);
        
        //have the home page be able to show all current villains
        
    return "villain";
    }
    
    @RequestMapping(value = "/displayEditVillain", method = RequestMethod.GET)
    public String displayEditVillain(HttpServletRequest request, Model model) {
        String villainIdParameter = request.getParameter("villainId");
        int villainId = Integer.parseInt(villainIdParameter);
        Champion champ = dao.getChampionById(villainId);
        model.addAttribute("champion", champ);
        
        List<Power> powers = dao.getAllPowers();
        model.addAttribute("powerList", powers);
        
        
        return "editVillain";
    }
    
    @RequestMapping(value = "/editVillain", method = RequestMethod.POST)
    public String editVillain(HttpServletRequest request, Model model) {
        
        Champion champ = new Champion();
        champ.setName(request.getParameter("heroName"));
        champ.setChampionDesc(request.getParameter("heroDesc"));
        champ.setIsHero(false);
        champ.setChampionID(Integer.parseInt(request.getParameter("heroID")));

        String[] powersToAdd = request.getParameterValues("powersToAdd");
        List<Power> allPowers = dao.getAllPowers();
        List<Power> addPowers = new ArrayList<>();

        for (String currentPower : powersToAdd) {
            for (Power power : allPowers) {
                if ((Integer.parseInt(currentPower)) == power.getPowerID()) {
                    Power powerToAdd = dao.getPowerById(Integer.parseInt(currentPower));
                    addPowers.add(powerToAdd);
                }
            }
        }

        champ.setPowers(addPowers);

        dao.updateChampion(champ);

        return "redirect:villain";
    }
    
}
