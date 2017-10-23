/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.controller;

import com.sg.hero.dao.HeroDao;
import com.sg.hero.model.Champion;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayIndexPage(Map<String, Object> model) {
        
        
        //have the home page be able to show 10 most recent sightings
        
    return "index";
    }
    
    @RequestMapping(value = "/hero", method = RequestMethod.GET)
    public String displayHeroPage(Model model) {
        List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        //have the home page be able to show all current heros
        
    return "hero";
    }
    @RequestMapping(value = "/villain", method = RequestMethod.GET)
    public String displayVillainPage(Map<String, Object> model) {
        
        
        //have the home page be able to show all current villains
        
    return "villain";
    }
    
    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public String displayLocationPage(Map<String, Object> model) {
        
        
        //have the home page be able to show all current locations
        
    return "location";
    }
    
    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    public String displayOrganizationPage(Map<String, Object> model) {
        
        
        //have the home page be able to show all current organizations
        
    return "organization";
    }
    
    @RequestMapping(value = "/sighting", method = RequestMethod.GET)
    public String displaySightingPage(Map<String, Object> model) {
        
        
        //have the home page be able to show all current sightings
        
    return "sighting";
    }
}
