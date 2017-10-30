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
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
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
public class HeroController {

    private HeroDao dao;

    @Inject
    public HeroController(HeroDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/hero", method = RequestMethod.GET)
    public String displayHeroPage(Model model) {
        List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);

        List<Power> powers = dao.getAllPowers();
        model.addAttribute("powerList", powers);

        return "hero";
    }
    
    

    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String createHero(HttpServletRequest request, Model model) {
        // grab the incoming values from the form and create a new Contact
        // object
        Champion champ = new Champion();
        champ.setName(request.getParameter("heroName"));
        champ.setChampionDesc(request.getParameter("heroDesc"));
        champ.setIsHero(true);

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

        List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);

        List<Power> powers = dao.getAllPowers();
        model.addAttribute("powerList", powers);

        return "hero";
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request, Model model) {

        String heroIdParameter = request.getParameter("heroId");
        int champId = Integer.parseInt(heroIdParameter);
        Champion champ = dao.getChampionById(champId);

        dao.deleteChampion(champ);

        List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);

        List<Power> powers = dao.getAllPowers();
        model.addAttribute("powerList", powers);

        return "hero";
    }
    
    @RequestMapping(value = "/displayEditHero", method = RequestMethod.GET)
    public String displayEditHero(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        Champion champ = dao.getChampionById(heroId);
        model.addAttribute("champion", champ);
        
        List<Power> powers = dao.getAllPowers();
        model.addAttribute("powerList", powers);
        
        return "editHero";
    }
    
    @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public String editHero(HttpServletRequest request, Model model) {
        
        Champion champ = new Champion();
        champ.setName(request.getParameter("heroName"));
        champ.setChampionDesc(request.getParameter("heroDesc"));
        champ.setIsHero(true);
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

        return "redirect:hero";
    }

}
