/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.dao;

import com.sg.hero.model.Champion;
import com.sg.hero.model.Contact;
import com.sg.hero.model.Location;
import com.sg.hero.model.Organization;
import com.sg.hero.model.Power;
import com.sg.hero.model.Sighting;
import java.util.List;

/**
 *
 * @author kvnzi
 */
public interface HeroDao {
    //crud for each
    
    //champion
    public void addChampion(Champion champion);

    public void deleteChampion(Champion champion);

    public void updateChampion(Champion champion);

    public Champion getChampionById(int id);

    public List<Champion> getAllChampions();
    
    //power
    public void addPower(Power power);

    public void deletePower(Power power);

    public void updatePower(Power power);

    public Power getPowerById(int id);

    public List<Power> getAllPowers();
    
    //organization
    public void addOrganization(Organization organization);

    public void deleteOrganization(Organization organiztion);

    public void updateOrganization(Organization organization);

    public Organization getOrganizationById(int id);

    public List<Organization> getAllOrganizations();
    
    //location
    public void addLocation(Location location);

    public void deleteLocation(Location location);

    public void updateLocation(Location location);

    public Location getLocationById(int id);

    public List<Location> getAllLocations();
    
    //contact
    public void addContact(Contact contact);

    public void deleteContact(Contact contact);

    public void updateContact(Contact contact);

    public Contact getContactById(int id);

    public List<Contact> getAllContacts();
    
    //sighting
    public void addSighting(Sighting sighting);

    public void deleteSighting(Sighting sighting);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingById(int id);

    public List<Sighting> getAllSightings();
}
