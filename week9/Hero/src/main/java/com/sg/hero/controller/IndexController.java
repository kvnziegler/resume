/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.controller;

import com.sg.hero.dao.HeroDao;
import com.sg.hero.model.Sighting;
import java.util.List;
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
public class IndexController {

    private HeroDao dao;

    @Inject
    public IndexController(HeroDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayIndexPage(Model model) {
        List<Sighting> recentSights = dao.getMostRecentSighting();
        //get top 10 most recent
        //may need new dao method to run sql query to get 10 most recent sightings
        //right now getting all in no particular order.
        model.addAttribute("recentList", recentSights);

        //have the home page be able to show 10 most recent sightings
        return "index";
    }

}
