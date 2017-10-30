/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.controller;

import com.sg.hero.dao.HeroDao;
import com.sg.hero.model.Champion;
import com.sg.hero.model.Location;
import com.sg.hero.model.Power;
import com.sg.hero.model.Sighting;
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
public class SightingController {
    private HeroDao dao;
    
    @Inject
    public SightingController(HeroDao dao) {
        this.dao = dao;
    }
    
    
    @RequestMapping(value = "/sighting", method = RequestMethod.GET)
    public String displaySightingPage(Model model) {
        List<Sighting> sights = dao.getAllSightings();
        model.addAttribute("sightList", sights);
        
         List<Location> locs = dao.getAllLocations();
        model.addAttribute("locList", locs);
        
         List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        
        //have the home page be able to show all current sightings
        
    return "sighting";
    }
    
    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request, Model model) {

        Sighting sight= new Sighting();
        Location loc = dao.getLocationById(Integer.parseInt(request.getParameter("location")));
        sight.setLocation(loc);
        sight.setSightDesc(request.getParameter("sightDesc"));
        sight.setSightDate(request.getParameter("sightDate"));

        String[] herosToAdd = request.getParameterValues("herosToAdd");
        List<Champion> allChampions = dao.getAllChampions();
        List<Champion> addChampions = new ArrayList<>();

        for (String currentChampion : herosToAdd) {
            for (Champion champ : allChampions) {
                if ((Integer.parseInt(currentChampion)) == champ.getChampionID()) {
                    Champion championToAdd = dao.getChampionById(Integer.parseInt(currentChampion));
                    addChampions.add(championToAdd);
                }
            }
        }

        sight.setHeroes(addChampions);

        dao.addSighting(sight);

        
        
        //TODO: still creating a new power everytime a champion is added
        
        
        
        
       List<Sighting> sights = dao.getAllSightings();
        model.addAttribute("sightList", sights);
        
         List<Location> locs = dao.getAllLocations();
        model.addAttribute("locList", locs);
        
         List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        
        //have the home page be able to show all current sightings
        
    return "sighting";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request, Model model) {

        String sightIdParameter = request.getParameter("SightingId");
        int sightID = Integer.parseInt(sightIdParameter);
        Sighting sight = dao.getSightingById(sightID);

        dao.deleteSighting(sight);

       List<Sighting> sights = dao.getAllSightings();
        model.addAttribute("sightList", sights);
        
         List<Location> locs = dao.getAllLocations();
        model.addAttribute("locList", locs);
        
         List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        
        //have the home page be able to show all current sightings
        
    return "sighting";
    }
    
    @RequestMapping(value = "/displayEditSighting", method = RequestMethod.GET)
    public String displayEditSighting(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("SightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sight = dao.getSightingById(sightingId);
        
        model.addAttribute("sighting", sight);
        
        List<Location> locs = dao.getAllLocations();
        model.addAttribute("locList", locs);
        
         List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        
        return "editSighting";
    }
    
    
    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request, Model model) {
        
        Sighting sight= new Sighting();
        Location loc = dao.getLocationById(Integer.parseInt(request.getParameter("location")));
        sight.setLocation(loc);
        sight.setSightDesc(request.getParameter("sightDesc"));
        sight.setSightDate(request.getParameter("sightDate"));
        sight.setSightingId(Integer.parseInt(request.getParameter("sightingID")));

        String[] herosToAdd = request.getParameterValues("herosToAdd");
        List<Champion> allChampions = dao.getAllChampions();
        List<Champion> addChampions = new ArrayList<>();

        for (String currentChampion : herosToAdd) {
            for (Champion champ : allChampions) {
                if ((Integer.parseInt(currentChampion)) == champ.getChampionID()) {
                    Champion championToAdd = dao.getChampionById(Integer.parseInt(currentChampion));
                    addChampions.add(championToAdd);
                }
            }
        }

        sight.setHeroes(addChampions);
        
        dao.updateSighting(sight);

        return "redirect:sighting";
    }
    
}
