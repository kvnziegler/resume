/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.controller;

import com.sg.hero.dao.HeroDao;
import com.sg.hero.model.Champion;
import com.sg.hero.model.Contact;
import com.sg.hero.model.Location;
import com.sg.hero.model.Organization;
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
public class OrganizationController {
    private HeroDao dao;

    @Inject
    public OrganizationController(HeroDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    public String displayOrganizationPage(Model model) {
        List<Organization> orgs = dao.getAllOrganizations();
        model.addAttribute("orgList", orgs);
        
        List<Location> locs = dao.getAllLocations();
        model.addAttribute("locList", locs);
        
        List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        
        List<Contact> cont = dao.getAllContacts();
        model.addAttribute("contList", cont);
        
        //have the home page be able to show all current organizations
        
    return "organization";
    }
    
    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(HttpServletRequest request, Model model) {
        // grab the incoming values from the form and create a new Contact
        // object
        Organization org = new Organization();
        org.setName(request.getParameter("orgName"));
        org.setOrgDesc(request.getParameter("orgDesc"));

        String[] champsToAdd = request.getParameterValues("championsToAdd");
        List<Champion> allChamps = dao.getAllChampions();
        List<Champion> addChamps = new ArrayList<>();

        for (String currentChamp : champsToAdd) {
            for (Champion champ : allChamps) {
                if ((Integer.parseInt(currentChamp)) == champ.getChampionID()) {
                    Champion champion = dao.getChampionById(Integer.parseInt(currentChamp));
                    addChamps.add(champion);
                }
            }
        }
        
        org.setChampions(addChamps);
        
        String[] locsToAdd = request.getParameterValues("championsToAdd");
        List<Location> alllocs = dao.getAllLocations();
        List<Location> addLocs = new ArrayList<>();

        
        for (String currentLoc : locsToAdd) {
            for (Location loc : alllocs) {
                if ((Integer.parseInt(currentLoc)) == loc.getLocationId()) {
                    Location location = dao.getLocationById(Integer.parseInt(currentLoc));
                    addLocs.add(location);
                }
            }
        }
        
        org.setLocations(addLocs);
        
        String[] contsToAdd = request.getParameterValues("contactsToAdd");
        List<Contact> allConts = dao.getAllContacts();
        List<Contact> addConts = new ArrayList<>();

        
        for (String currentCont : contsToAdd) {
            for (Contact cont : allConts) {
                if ((Integer.parseInt(currentCont)) == cont.getContactId()) {
                    Contact contact = dao.getContactById(Integer.parseInt(currentCont));
                    addConts.add(contact);
                }
            }
        }
        
        org.setContacts(addConts);

        dao.addOrganization(org);
    
         List<Organization> orgs = dao.getAllOrganizations();
        model.addAttribute("orgList", orgs);
        
        List<Location> locs = dao.getAllLocations();
        model.addAttribute("locList", locs);
        
        List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        
        List<Contact> cont = dao.getAllContacts();
        model.addAttribute("contList", cont);
        
        //have the home page be able to show all current organizations
        
    return "organization";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request, Model model) {

        String orgIdParameter = request.getParameter("organizationId");
        int orgId = Integer.parseInt(orgIdParameter);
        Organization org = dao.getOrganizationById(orgId);

        dao.deleteOrganization(org);

         List<Organization> orgs = dao.getAllOrganizations();
        model.addAttribute("orgList", orgs);
        
        List<Location> locs = dao.getAllLocations();
        model.addAttribute("locList", locs);
        
        List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        
        List<Contact> cont = dao.getAllContacts();
        model.addAttribute("contList", cont);
        
        //have the home page be able to show all current organizations
        
    return "organization";
    }
    
    @RequestMapping(value = "/displayEditOrganization", method = RequestMethod.GET)
    public String displayEditOrganization(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        Organization org = dao.getOrganizationById(organizationId);
        
        model.addAttribute("organization", org);
        
        List<Location> locs = dao.getAllLocations();
        model.addAttribute("locList", locs);
        
        List<Champion> champs = dao.getAllChampions();
        model.addAttribute("heroList", champs);
        
        List<Contact> cont = dao.getAllContacts();
        model.addAttribute("contList", cont);
        
        return "editOrganization";
    }
    
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(HttpServletRequest request, Model model) {
        
        Organization org = new Organization();
        org.setName(request.getParameter("orgName"));
        org.setOrgDesc(request.getParameter("orgDesc"));
        org.setOrganizationID(Integer.parseInt(request.getParameter("organizationID")));

        String[] champsToAdd = request.getParameterValues("championsToAdd");
        List<Champion> allChamps = dao.getAllChampions();
        List<Champion> addChamps = new ArrayList<>();

        for (String currentChamp : champsToAdd) {
            for (Champion champ : allChamps) {
                if ((Integer.parseInt(currentChamp)) == champ.getChampionID()) {
                    Champion champion = dao.getChampionById(Integer.parseInt(currentChamp));
                    addChamps.add(champion);
                }
            }
        }
        
        org.setChampions(addChamps);
        
        String[] locsToAdd = request.getParameterValues("championsToAdd");
        List<Location> alllocs = dao.getAllLocations();
        List<Location> addLocs = new ArrayList<>();

        
        for (String currentLoc : locsToAdd) {
            for (Location loc : alllocs) {
                if ((Integer.parseInt(currentLoc)) == loc.getLocationId()) {
                    Location location = dao.getLocationById(Integer.parseInt(currentLoc));
                    addLocs.add(location);
                }
            }
        }
        
        org.setLocations(addLocs);
        
        String[] contsToAdd = request.getParameterValues("contactsToAdd");
        List<Contact> allConts = dao.getAllContacts();
        List<Contact> addConts = new ArrayList<>();

        
        for (String currentCont : contsToAdd) {
            for (Contact cont : allConts) {
                if ((Integer.parseInt(currentCont)) == cont.getContactId()) {
                    Contact contact = dao.getContactById(Integer.parseInt(currentCont));
                    addConts.add(contact);
                }
            }
        }
        
        org.setContacts(addConts);

        dao.updateOrganization(org);

        return "redirect:organization";
    }
}
