/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.controller;

import com.sg.hero.dao.HeroDao;
import com.sg.hero.model.Location;
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
public class LocationController {

    private HeroDao dao;

    @Inject
    public LocationController(HeroDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public String displayLocationPage(Model model) {
        List<Location> locs = dao.getAllLocations();
        model.addAttribute("locList", locs);

        //have the home page be able to show all current locations
        return "location";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request, Model model) {
        // grab the incoming values from the form and create a new Contact
        // object
        Location loc = new Location();
        loc.setName(request.getParameter("locationName"));
        loc.setAddress(request.getParameter("address"));
        loc.setLocationDesc(request.getParameter("description"));
        loc.setLongitutde(Double.parseDouble(request.getParameter("longitude")));
        loc.setLattitude(Double.parseDouble(request.getParameter("latitude")));

        // persist the new Contact
        dao.addLocation(loc);

        List<Location> locs = dao.getAllLocations();
        model.addAttribute("locList", locs);
        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Contacts Page
        // so it can display the new Contact in the table.
        return "location";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, Model model) {

        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location loc = dao.getLocationById(locationId);

        dao.deleteLocation(loc);

        List<Location> locs = dao.getAllLocations();
        model.addAttribute("locList", locs);

        return "location";
    }

    @RequestMapping(value = "/displayEditLocation", method = RequestMethod.GET)
    public String displayEditLocation(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location loc = dao.getLocationById(locationId);
        model.addAttribute("location", loc);
        return "editLocation";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(HttpServletRequest request, Model model) {

        Location loc = new Location();
        loc.setName(request.getParameter("locationName"));
        loc.setLocationDesc(request.getParameter("locationDesc"));
        loc.setAddress(request.getParameter("address"));
        loc.setLocationId(Integer.parseInt(request.getParameter("locationID")));
        loc.setLongitutde(Double.parseDouble(request.getParameter("longitude")));
        loc.setLattitude(Double.parseDouble(request.getParameter("latitude")));

        dao.updateLocation(loc);

        return "redirect:location";
    }

}
