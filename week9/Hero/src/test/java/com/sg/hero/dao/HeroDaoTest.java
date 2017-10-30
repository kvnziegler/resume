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
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kvnzi
 */
public class HeroDaoTest {
    
    HeroDao dao;
    
    public HeroDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("HeroDao", HeroDao.class);

        // delete all champions
        List<Champion> Champions = dao.getAllChampions();
        for (Champion currentChampion : Champions) {
            dao.deleteChampion(currentChampion);
        }
        //delete all contacts
        List<Contact> contacts = dao.getAllContacts();
        for (Contact currentContacts : contacts) {
            dao.deleteContact(currentContacts);
        }
        // delete all locations
        List<Location> locations = dao.getAllLocations();
        for (Location currentLocation : locations) {
            dao.deleteLocation(currentLocation);
        }
        // delete all organizations
        List<Organization> organizations = dao.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            dao.deleteOrganization(currentOrganization);
        }
        // delete all powers
        List<Power> powers = dao.getAllPowers();
        for (Power currentPower : powers) {
            dao.deletePower(currentPower);
        }
        // delete all sightings
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            dao.deleteSighting(currentSighting);
        }
        
        Power tick = new Power();
        tick.setName("sample");
        tick.setPowerDesc("sample desc");
        
        dao.addPower(tick);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addChampion method, of class HeroDao.
     */
    
    //champion
    
    
    @Test
    public void testAddChampion() {
        Champion champ = new Champion();
        List<Power> powers = dao.getAllPowers();       
        champ.setName("The Tick");
        champ.setChampionDesc("Big and Blue");
        champ.setIsHero(true);
        champ.setPowers(powers);
        
        dao.addChampion(champ);
        
        Champion chump = dao.getChampionById(champ.getChampionID());
        assertEquals(champ,chump);
    }

    /**
     * Test of deleteChampion method, of class HeroDao.
     */
    @Test
    public void testDeleteChampion() {
        Champion champ = new Champion();
        List<Power> powers = dao.getAllPowers();
        champ.setName("The Tick");
        champ.setChampionDesc("Big and Blue");
        champ.setIsHero(true);
        champ.setPowers(powers);
        
        dao.addChampion(champ);
        
        assertEquals(1,dao.getAllChampions().size());
        
        Champion chump = dao.getChampionById(champ.getChampionID());
        assertEquals(champ,chump);
        
        dao.deleteChampion(champ);
        
        assertEquals(0,dao.getAllChampions().size());
    }

    /**
     * Test of updateChampion method, of class HeroDao.
     */
    @Test
    public void testUpdateChampion() {
         Champion champ = new Champion();
        List<Power> powers = dao.getAllPowers();
        champ.setName("The Tick");
        champ.setChampionDesc("Big and Blue");
        champ.setIsHero(true);
        champ.setPowers(powers);
        
        dao.addChampion(champ);
        
        champ.setName("bob");
        
        dao.updateChampion(champ);
        
        assertEquals(champ.getName(),"bob");
    }

    /**
     * Test of getChampionById method, of class HeroDao.
     */
    @Test
    public void testGetChampionById() {
                Champion champ = new Champion();
        List<Power> powers = dao.getAllPowers();
        champ.setName("The Tick");
        champ.setChampionDesc("Big and Blue");
        champ.setIsHero(true);
        champ.setPowers(powers);
        
        dao.addChampion(champ);
        
        Champion chump = dao.getChampionById(champ.getChampionID());
        assertEquals(champ,chump);
    }

    /**
     * Test of getAllChampions method, of class HeroDao.
     */
    @Test
    public void testGetAllChampions() {
        
        assertEquals(0, dao.getAllChampions().size());
        
        Champion champ = new Champion();
        List<Power> powers = dao.getAllPowers();
        champ.setName("The Tick");
        champ.setChampionDesc("Big and Blue");
        champ.setIsHero(true);
        champ.setPowers(powers);
        
        dao.addChampion(champ);
        List<Champion> champs = dao.getAllChampions();
        
        assertEquals(1,dao.getAllChampions().size());
    }

    /**
     * Test of addPower method, of class HeroDao.
     */
    
    //Powers
    
    
    
    @Test
    public void testAddPower() {
        Power power = new Power();
        power.setName("Lizard Vision");
        power.setPowerDesc("Shoots lizards from your eyes");
        
        dao.addPower(power);
        
        Power lizards = dao.getPowerById(power.getPowerID());
        
        assertEquals(power,lizards);
    }

    /**
     * Test of deletePower method, of class HeroDao.
     */
    @Test
    public void testDeletePower() {
        
        assertEquals(1, dao.getAllPowers().size());
        
        Power power = new Power();
        power.setName("Lizard Vision");
        power.setPowerDesc("Shoots lizards from your eyes");
        
        dao.addPower(power);
        
        Power lizards = dao.getPowerById(power.getPowerID());
        
        assertEquals(2, dao.getAllPowers().size());
        
        dao.deletePower(power);
        
        assertEquals(1, dao.getAllPowers().size());
        
        
    }

    /**
     * Test of updatePower method, of class HeroDao.
     */
    @Test
    public void testUpdatePower() {
        
        Power power = new Power();
        power.setName("Lizard Vision");
        power.setPowerDesc("Shoots lizards from your eyes");
        
        dao.addPower(power);
        
        Power lizards = dao.getPowerById(power.getPowerID());
        
        power.setName("lizards");
        dao.updatePower(power);
        
        assertEquals(power.getName(),"lizards");
        
    }

    /**
     * Test of getPowerById method, of class HeroDao.
     */
    @Test
    public void testGetPowerById() {
        Power power = new Power();
        power.setName("Lizard Vision");
        power.setPowerDesc("Shoots lizards from your eyes");
        
        dao.addPower(power);
        
        Power lizards = dao.getPowerById(power.getPowerID());
        
        assertEquals(power,lizards);
    }

    /**
     * Test of getAllPowers method, of class HeroDao.
     */
    @Test
    public void testGetAllPowers() {
        
        assertEquals(1, dao.getAllPowers().size());
        
        Power power = new Power();
        power.setName("Lizard Vision");
        power.setPowerDesc("Shoots lizards from your eyes");
        
        dao.addPower(power);
        
        Power lizards = dao.getPowerById(power.getPowerID());
        
        assertEquals(power,lizards);
        
        assertEquals(2, dao.getAllPowers().size());
    }

    /**
     * Test of addOrganization method, of class HeroDao.
     */
    
    //Organizattions
    
    
    
    @Test
    public void testAddOrganization() {
        List<Champion> champions = new ArrayList<>();
        List<Contact> contacts = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        List<Power> powers = dao.getAllPowers();
        
        Champion champ = new Champion();
        champ.setName("JavaMan");
        champ.setChampionDesc("Runs on coffee");
        champ.setPowers(powers);
        
        dao.addChampion(champ);
        
        assertEquals(1,dao.getAllChampions().size());
        
        champions.add(champ);
        
        Contact cont = new Contact();
        cont.setPhone("5555555");
        cont.setEmail("sample.sample@sample");
        
        dao.addContact(cont);
        assertEquals(1, dao.getAllContacts().size());
        
        contacts.add(cont);
        
        Location loc = new Location();
        loc.setName("Metrotham");
        loc.setLocationDesc("Home of SuperBat");
        loc.setAddress("...its a secret");
        loc.setLongitutde(1);
        loc.setLattitude(2);
        
        dao.addLocation(loc);
        assertEquals(1, dao.getAllLocations().size());
        locations.add(loc);
        
        Organization org = new Organization();
        org.setName("The Just Ice League");
        org.setOrgDesc("Deff... not justice league");
        org.setLocations(locations);
        org.setChampions(champions);
        org.setContacts(contacts);
        
        dao.addOrganization(org);
        
        Organization test = dao.getOrganizationById(org.getOrganizationID());
        
        assertEquals(org.getName(), test.getName());
        assertEquals(org.getOrgDesc(), test.getOrgDesc());
        assertEquals(org.getOrganizationID(), test.getOrganizationID());

        assertEquals(org.getChampions(), test.getChampions());
        assertEquals(org.getContacts(), test.getContacts());
        assertEquals(org.getLocations(), test.getLocations());
        
        assertEquals(org, test);
    }

    /**
     * Test of deleteOrganization method, of class HeroDao.
     */
    @Test
    public void testDeleteOrganization() {
        List<Champion> champions = new ArrayList<>();
        List<Contact> contacts = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        List<Power> powers = dao.getAllPowers();
        
        Champion champ = new Champion();
        champ.setName("JavaMan");
        champ.setChampionDesc("Runs on coffee");
        champ.setPowers(powers);
        
        dao.addChampion(champ);
        
        assertEquals(1,dao.getAllChampions().size());
        
        champions.add(champ);
        
        Contact cont = new Contact();
        cont.setPhone("5555555");
        cont.setEmail("sample.sample@sample");
        
        dao.addContact(cont);
        assertEquals(1, dao.getAllContacts().size());
        
        contacts.add(cont);
        
        Location loc = new Location();
        loc.setName("Metrotham");
        loc.setLocationDesc("Home of SuperBat");
        loc.setAddress("...its a secret");
        loc.setLongitutde(1);
        loc.setLattitude(2);
        
        dao.addLocation(loc);
        assertEquals(1, dao.getAllLocations().size());
        locations.add(loc);
        
        Organization org = new Organization();
        org.setName("The Just Ice League");
        org.setOrgDesc("Deff... not justice league");
        org.setLocations(locations);
        org.setChampions(champions);
        org.setContacts(contacts);
        
        dao.addOrganization(org);
        
        assertEquals(1, dao.getAllOrganizations().size());
        
        dao.deleteOrganization(org);
        
        assertEquals(0, dao.getAllOrganizations().size());
    }

    /**
     * Test of updateOrganization method, of class HeroDao.
     */
    @Test
    public void testUpdateOrganization() {
        List<Champion> champions = new ArrayList<>();
        List<Contact> contacts = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        List<Power> powers = dao.getAllPowers();
        
        Champion champ = new Champion();
        champ.setName("JavaMan");
        champ.setChampionDesc("Runs on coffee");
        champ.setPowers(powers);
        
        dao.addChampion(champ);
        
        assertEquals(1,dao.getAllChampions().size());
        
        champions.add(champ);
        
        Contact cont = new Contact();
        cont.setPhone("5555555");
        cont.setEmail("sample.sample@sample");
        
        dao.addContact(cont);
        assertEquals(1, dao.getAllContacts().size());
        
        contacts.add(cont);
        
        Location loc = new Location();
        loc.setName("Metrotham");
        loc.setLocationDesc("Home of SuperBat");
        loc.setAddress("...its a secret");
        loc.setLongitutde(1);
        loc.setLattitude(2);
        
        dao.addLocation(loc);
        assertEquals(1, dao.getAllLocations().size());
        locations.add(loc);
        
        Organization org = new Organization();
        org.setName("The Just Ice League");
        org.setOrgDesc("Deff... not justice league");
        org.setLocations(locations);
        org.setChampions(champions);
        org.setContacts(contacts);
        
        dao.addOrganization(org);
        
        org.setName("The Vindicators");
        
        dao.updateOrganization(org);
        
        assertEquals("The Vindicators", dao.getOrganizationById(org.getOrganizationID()).getName());
    }

    /**
     * Test of getOrganizationById method, of class HeroDao.
     */
    @Test
    public void testGetOrganizationById() {
        
         List<Champion> champions = new ArrayList<>();
        List<Contact> contacts = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        List<Power> powers = dao.getAllPowers();
        
        Champion champ = new Champion();
        champ.setName("JavaMan");
        champ.setChampionDesc("Runs on coffee");
        champ.setPowers(powers);
        
        dao.addChampion(champ);
        
        assertEquals(1,dao.getAllChampions().size());
        
        champions.add(champ);
        
        Contact cont = new Contact();
        cont.setPhone("5555555");
        cont.setEmail("sample.sample@sample");
        
        dao.addContact(cont);
        assertEquals(1, dao.getAllContacts().size());
        
        contacts.add(cont);
        
        Location loc = new Location();
        loc.setName("Metrotham");
        loc.setLocationDesc("Home of SuperBat");
        loc.setAddress("...its a secret");
        loc.setLongitutde(1);
        loc.setLattitude(2);
        
        dao.addLocation(loc);
        assertEquals(1, dao.getAllLocations().size());
        locations.add(loc);
        
        Organization org = new Organization();
        org.setName("The Just Ice League");
        org.setOrgDesc("Deff... not justice league");
        org.setLocations(locations);
        org.setChampions(champions);
        org.setContacts(contacts);
        
        dao.addOrganization(org);
        
        Organization test = dao.getOrganizationById(org.getOrganizationID());
        
        assertEquals(org.getName(), test.getName());
        assertEquals(org.getOrgDesc(), test.getOrgDesc());
        assertEquals(org.getOrganizationID(), test.getOrganizationID());

        assertEquals(org.getChampions(), test.getChampions());
        assertEquals(org.getContacts(), test.getContacts());
        assertEquals(org.getLocations(), test.getLocations());
        
        assertEquals(org, test);
    }

    /**
     * Test of getAllOrganizations method, of class HeroDao.
     */
    @Test
    public void testGetAllOrganizations() {
        List<Champion> champions = new ArrayList<>();
        List<Contact> contacts = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        List<Power> powers = dao.getAllPowers();
        
        Champion champ = new Champion();
        champ.setName("JavaMan");
        champ.setChampionDesc("Runs on coffee");
        champ.setPowers(powers);
        
        dao.addChampion(champ);
        
        assertEquals(1,dao.getAllChampions().size());
        
        champions.add(champ);
        
        Contact cont = new Contact();
        cont.setPhone("5555555");
        cont.setEmail("sample.sample@sample");
        
        dao.addContact(cont);
        assertEquals(1, dao.getAllContacts().size());
        
        contacts.add(cont);
        
        Location loc = new Location();
        loc.setName("Metrotham");
        loc.setLocationDesc("Home of SuperBat");
        loc.setAddress("...its a secret");
        loc.setLongitutde(1);
        loc.setLattitude(2);
        
        dao.addLocation(loc);
        assertEquals(1, dao.getAllLocations().size());
        locations.add(loc);
        
        Organization org = new Organization();
        org.setName("The Just Ice League");
        org.setOrgDesc("Deff... not justice league");
        org.setLocations(locations);
        org.setChampions(champions);
        org.setContacts(contacts);
        
        dao.addOrganization(org);
        
        List<Organization> orgList = dao.getAllOrganizations();
        
        assertEquals(1, dao.getAllOrganizations().size());
    }

    /**
     * Test of addLocation method, of class HeroDao.
     */
    
    
    //location
    
    
    @Test
    public void testAddLocation() {
        Location loc = new Location();
        loc.setName("Metrotham");
        loc.setLocationDesc("Home of SuperBat");
        loc.setAddress("...its a secret");
        loc.setLongitutde(1);
        loc.setLattitude(2);
        
        dao.addLocation(loc);
        
        Location test = dao.getLocationById(loc.getLocationId());
        
        assertEquals(loc,test);
    }

    /**
     * Test of deleteLocation method, of class HeroDao.
     */
    @Test
    public void testDeleteLocation() {
        Location loc = new Location();
        loc.setName("Metrotham");
        loc.setLocationDesc("Home of SuperBat");
        loc.setAddress("...its a secret");
        loc.setLongitutde(1);
        loc.setLattitude(2);
        
        dao.addLocation(loc);
        
        assertEquals(1, dao.getAllLocations().size());
        
        dao.deleteLocation(loc);
        
        assertEquals(0, dao.getAllLocations().size());
    }

    /**
     * Test of updateLocation method, of class HeroDao.
     */
    @Test
    public void testUpdateLocation() {
         Location loc = new Location();
        loc.setName("Metrotham");
        loc.setLocationDesc("Home of SuperBat");
        loc.setAddress("...its a secret");
        loc.setLongitutde(1);
        loc.setLattitude(2);
        
        dao.addLocation(loc);
        
        loc.setLocationDesc("deffinetly not hidden under Bruce Kent's conda...");
        
        dao.updateLocation(loc);
        
        Location test = dao.getLocationById(loc.getLocationId());
        
        assertEquals("deffinetly not hidden under Bruce Kent's conda...", test.getLocationDesc());
    }

    /**
     * Test of getLocationById method, of class HeroDao.
     */
    @Test
    public void testGetLocationById() {
        Location loc = new Location();
        loc.setName("Metrotham");
        loc.setLocationDesc("Home of SuperBat");
        loc.setAddress("...its a secret");
        loc.setLongitutde(1);
        loc.setLattitude(2);
        
        dao.addLocation(loc);
        
        Location test = dao.getLocationById(loc.getLocationId());
        
        assertEquals(loc,test);
    }

    /**
     * Test of getAllLocations method, of class HeroDao.
     */
    @Test
    public void testGetAllLocations() {
        Location loc = new Location();
        loc.setName("Metrotham");
        loc.setLocationDesc("Home of SuperBat");
        loc.setAddress("...its a secret");
        loc.setLongitutde(1);
        loc.setLattitude(2);
        
        dao.addLocation(loc);
        
        assertEquals(1, dao.getAllLocations().size());
    }

    /**
     * Test of addContact method, of class HeroDao.
     */
    
    //Contacts
    
    @Test
    public void testAddContact() {
        Contact contact = new Contact();
        contact.setPhone("5555555");
        contact.setEmail("non@ya.business");
        
        dao.addContact(contact);
        
        Contact test = dao.getContactById(contact.getContactId());
        
        assertEquals(contact,test);
    }

    /**
     * Test of deleteContact method, of class HeroDao.
     */
    @Test
    public void testDeleteContact() {
        Contact contact = new Contact();
        contact.setPhone("5555555");
        contact.setEmail("non@ya.business");
        
        dao.addContact(contact);
        
        Contact test = dao.getContactById(contact.getContactId());
        
        assertEquals(contact,test);
        
        assertEquals(1,dao.getAllContacts().size());
        
        dao.deleteContact(contact);
        
        assertEquals(0,dao.getAllContacts().size());
    }

    /**
     * Test of updateContact method, of class HeroDao.
     */
    @Test
    public void testUpdateContact() {
        
        Contact contact = new Contact();
        contact.setPhone("5555555");
        contact.setEmail("non@ya.business");
        
        dao.addContact(contact);
        
        contact.setPhone("1111111");
        
        dao.updateContact(contact);
        
        assertEquals(contact.getPhone(),"1111111");
        
    }

    /**
     * Test of getContactById method, of class HeroDao.
     */
    @Test
    public void testGetContactById() {
        
        Contact contact = new Contact();
        contact.setPhone("5555555");
        contact.setEmail("non@ya.business");
        
        dao.addContact(contact);
        
        Contact test = dao.getContactById(contact.getContactId());
        
        assertEquals(contact,test);
    }

    /**
     * Test of getAllContacts method, of class HeroDao.
     */
    @Test
    public void testGetAllContacts() {
        
        assertEquals(0,dao.getAllContacts().size());
        
        Contact contact = new Contact();
        contact.setPhone("5555555");
        contact.setEmail("non@ya.business");
        
        dao.addContact(contact);
        
        Contact test = dao.getContactById(contact.getContactId());
        
        assertEquals(contact,test);
        
        assertEquals(1,dao.getAllContacts().size());
        
    }

    /**
     * Test of addSighting method, of class HeroDao.
     */
    
    
    //Sightings
    
    
    
    @Test
    public void testAddSighting() {
        
        Champion champ = new Champion();
        List<Power> powers = dao.getAllPowers();
        champ.setName("The Tick");
        champ.setChampionDesc("Big and Blue");
        champ.setIsHero(true);
        champ.setPowers(powers);
        dao.addChampion(champ);
        
        
        List<Champion> champions = new ArrayList<Champion>();
        champions.add(champ);
        
        Location loc = new Location();
        loc.setName("down town");
        loc.setAddress("123 main St.");
        loc.setLattitude(1);
        loc.setLongitutde(1);
        loc.setLocationDesc("desc");
        dao.addLocation(loc);
        
        Sighting sight = new Sighting();
        sight.setSightDesc("down town");
        sight.setSightDate("2017-05-05");
        sight.setLocation(loc);
        sight.setHeroes(champions);
        dao.addSighting(sight);
        
        Sighting test = dao.getSightingById(sight.getSightingId());
        
        assertEquals(sight.getSightingId(),test.getSightingId());
        assertEquals(sight.getHeroes(),test.getHeroes());
        assertEquals(sight.getLocation(),test.getLocation());
        assertEquals(sight.getSightDate(),test.getSightDate());
        assertEquals(sight.getSightDesc(),test.getSightDesc());
        
        
        
    }

    /**
     * Test of deleteSighting method, of class HeroDao.
     */
    @Test
    public void testDeleteSighting() {
        Champion champ = new Champion();
        List<Power> powers = dao.getAllPowers();
        champ.setName("The Tick");
        champ.setChampionDesc("Big and Blue");
        champ.setIsHero(true);
        champ.setPowers(powers);
        dao.addChampion(champ);
        
        
        List<Champion> champions = new ArrayList<Champion>();
        champions.add(champ);
        
        Location loc = new Location();
        loc.setName("down town");
        loc.setAddress("123 main St.");
        loc.setLattitude(1);
        loc.setLongitutde(1);
        loc.setLocationDesc("desc");
        dao.addLocation(loc);
        
        Sighting sight = new Sighting();
        sight.setSightDesc("down town");
        sight.setSightDate("2017-05-05");
        sight.setLocation(loc);
        sight.setHeroes(champions);
        dao.addSighting(sight);
        
        assertEquals(1, dao.getAllSightings().size());
        
        dao.deleteSighting(sight);
        List<Sighting> sightings = dao.getAllSightings();
        
        assertEquals(0, dao.getAllSightings().size());
    }

    /**
     * Test of updateSighting method, of class HeroDao.
     */
    @Test
    public void testUpdateSighting() {
        Champion champ = new Champion();
        List<Power> powers = dao.getAllPowers();
        champ.setName("The Tick");
        champ.setChampionDesc("Big and Blue");
        champ.setIsHero(true);
        champ.setPowers(powers);
        dao.addChampion(champ);
        
        
        List<Champion> champions = new ArrayList<Champion>();
        champions.add(champ);
        
        Location loc = new Location();
        loc.setName("down town");
        loc.setAddress("123 main St.");
        loc.setLattitude(1);
        loc.setLongitutde(1);
        loc.setLocationDesc("desc");
        dao.addLocation(loc);
        
        Sighting sight = new Sighting();
        sight.setSightDesc("down town");
        sight.setSightDate("2017-05-05");
        sight.setLocation(loc);
        sight.setHeroes(champions);
        dao.addSighting(sight);
        
        sight.setSightDesc("OMG ITS BATTSY");
        
        dao.updateSighting(sight);
        
        assertEquals(sight.getSightDesc(), "OMG ITS BATTSY");
        

        
    }

    /**
     * Test of getSightingById method, of class HeroDao.
     */
    @Test
    public void testGetSightingById() {
        Champion champ = new Champion();
        List<Power> powers = dao.getAllPowers();
        champ.setName("The Tick");
        champ.setChampionDesc("Big and Blue");
        champ.setIsHero(true);
        champ.setPowers(powers);
        dao.addChampion(champ);
        
        
        List<Champion> champions = new ArrayList<Champion>();
        champions.add(champ);
        
        Location loc = new Location();
        loc.setName("down town");
        loc.setAddress("123 main St.");
        loc.setLattitude(1);
        loc.setLongitutde(1);
        loc.setLocationDesc("desc");
        dao.addLocation(loc);
        
        Sighting sight = new Sighting();
        sight.setSightDesc("down town");
        sight.setSightDate("2017-05-05");
        sight.setLocation(loc);
        sight.setHeroes(champions);
        dao.addSighting(sight);
        
        Sighting test = dao.getSightingById(sight.getSightingId());
        
        assertEquals(sight, test);
    }

    /**
     * Test of getAllSightings method, of class HeroDao.
     */
    @Test
    public void testGetAllSightings() {
        
        Champion champ = new Champion();
        List<Power> powers = dao.getAllPowers();
        champ.setName("The Tick");
        champ.setChampionDesc("Big and Blue");
        champ.setIsHero(true);
        champ.setPowers(powers);
        dao.addChampion(champ);
        
        
        List<Champion> champions = new ArrayList<Champion>();
        champions.add(champ);
        
        Location loc = new Location();
        loc.setName("down town");
        loc.setAddress("123 main St.");
        loc.setLattitude(1);
        loc.setLongitutde(1);
        loc.setLocationDesc("desc");
        dao.addLocation(loc);
        
        Sighting sight = new Sighting();
        sight.setSightDesc("down town");
        sight.setSightDate("2017-05-05");
        sight.setLocation(loc);
        sight.setHeroes(champions);
        dao.addSighting(sight);
        
        assertEquals(1, dao.getAllSightings().size());
    }

}
