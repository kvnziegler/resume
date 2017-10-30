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
public class newContactController {

    private HeroDao dao;

    @Inject
    public newContactController(HeroDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/newContact", method = RequestMethod.GET)
    public String displayNewContact(Model model) {
        List<Contact> cont = dao.getAllContacts();
        model.addAttribute("contList", cont);

        return "newContact";
    }

    @RequestMapping(value = "/createContact", method = RequestMethod.POST)
    public String createContact(HttpServletRequest request, Model model) {
        // grab the incoming values from the form and create a new Contact
        // object

        Contact cont = new Contact();
        cont.setEmail(request.getParameter("email"));
        cont.setPhone(request.getParameter("phone"));

        dao.addContact(cont);
        

        List<Contact> contactList = dao.getAllContacts();
        model.addAttribute("contList", contactList);

        return "newContact";
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public String deleteContact(HttpServletRequest request, Model model) {

        String contIdParameter = request.getParameter("contactId");
        int contId = Integer.parseInt(contIdParameter);
        Contact cont = dao.getContactById(contId);

        dao.deleteContact(cont);

       List<Contact> contactList = dao.getAllContacts();
        model.addAttribute("contList", contactList);
        return "newContact";
    }

}
