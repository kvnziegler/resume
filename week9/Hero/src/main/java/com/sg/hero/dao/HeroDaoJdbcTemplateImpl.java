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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kvnzi
 */
public class HeroDaoJdbcTemplateImpl implements HeroDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //champions
    private static final String SQL_INSERT_CHAMPION
            = "insert into Champion (Name, champDesc, isHero) "
            + "values (?, ?, ?)";

    private static final String SQL_DELETE_CHAMPION
            = "delete from Champion where ChampionID = ?";

    private static final String SQL_UPDATE_CHAMPION
            = "update Champion SET Name = ?, champDesc = ?, isHero = ? where ChampionID = ?";

    private static final String SQL_SELECT_CHAMPION
            = "select * from Champion where ChampionID = ?";

    private static final String SQL_SELECT_ALL_CHAMPIONS
            = "select * from Champion";

    //contact
    private static final String SQL_INSERT_CONTACT
            = "insert into Contact (phone, emial) "
            + "values (?, ?)";

    private static final String SQL_DELETE_CONTACT
            = "delete from Contact where contactID = ?";

    private static final String SQL_UPDATE_CONTACT
            = "update Contact set phone = ?, emial = ? where contactID = ?";

    private static final String SQL_SELECT_CONTACT
            = "select * from Contact where contactID = ?";

    private static final String SQL_SELECT_ALL_CONTACT
            = "select * from Contact";

    //location
    private static final String SQL_INSERT_LOCATION
            = "insert into Location (name, address, LocationDesc, Longitude, Latitude) "
            + "values (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from Location where LocationID = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update location set name = ?, address = ?, LocationDesc = ?, "
            + "Longitude = ?, Latitude = ? where LocationID = ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from Location where LocationID = ?";

    private static final String SQL_SELECT_LOCATION_BYSIGHTING
            = "select * from Location right join sighting on LocationID = location where sightID = ?";

    private static final String SQL_SELECT_ALL_LOCATION
            = "select * from Location";

    //organization
    private static final String SQL_INSERT_ORGANIZATION
            = "insert into Organization (name, OrgDesc) "
            + "values (?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from Organization where OrganizationID = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update Organization set name = ?, OrgDesc = ? where OrganizationID = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from Organization where OrganizationID = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATION
            = "select * from Organization";

    //powers
    private static final String SQL_INSERT_POWERS
            = "insert into Powers (Name, PowerDesc) "
            + "values (?, ?)";

    private static final String SQL_DELETE_POWERS
            = "delete from Powers where PowerID = ?";

    private static final String SQL_UPDATE_POWERS
            = "update Powers set name = ?, PowerDesc = ? where PowerID = ?";

    private static final String SQL_SELECT_POWERS
            = "select * from Powers where PowerID = ?";

    private static final String SQL_SELECT_ALL_POWERS
            = "select * from Powers";
    //sighting

    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting (location, sightDate, sightDesc) "
            + "values (?, ?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sighting where sightID = ?";

    private static final String SQL_DELETE_SIGHTING_BY_LOCATION
            = "delete from sighting where location = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set location = ?, sightDate = ?, sightDesc = ? where sightID = ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from sighting right join location on location = LocationID where sightID = ?";

    private static final String SQL_SELECT_ALL_SIGHTING
            = "select * from sighting inner join location on LocationID = location";

    private static final String SQL_MOST_RECENT_SIGHTINGS
            = "SELECT * FROM sighting inner join location on LocationID = location order by sightDate desc limit 10";
//bridgetables
    //heropower
    private static final String SQL_INSERT_HEROPOWER
            = "insert into HeroPower (HeroID, PowerID) "
            + "values (?, ?)";

    private static final String SQL_DELETE_HEROPOWER
            = "delete from HeroPower where HeroID = ?";

    private static final String SQL_DELETE_HEROPOWER_BYPOWER
            = "delete from HeroPower where PowerID = ?";

    private static final String SQL_DELETE_HEROPOWER_BY_HERO
            = "delete from HeroPower where HeroID = ?";

    private static final String SQL_SELECT_HERO_BY_POWER
            = "select * from HeroPower where PowerID = ?";

    private static final String SQL_SELECT_POWER_BY_HERO
            = "select PowerID from HeroPower where HeroID = ?";

    private static final String SQL_SELECT_ALL_HEROPOWER
            = "select * from HeroPower";

    //herosight
    private static final String SQL_INSERT_HEROSIGHT
            = "insert into HeroSight (HeroID, SightID) "
            + "values (?, ?)";

    private static final String SQL_DELETE_HEROSIGHT
            = "delete from HeroSight where HeroID = ?";

    private static final String SQL_DELETE_HEROSIGHT_BY_SIGHT
            = "delete from HeroSight where SightID = ?";

    private static final String SQL_SELECT_HERO_BY_SIGHTING
            = "select HeroID from HeroSight where SightID = ?";

    private static final String SQL_SELECT_SIGHTING_BY_HERO
            = "select * from HeroSight where HeroID = ?";

    private static final String SQL_SELECT_ALL_HEROSIGHT
            = "select * from HeroSight";
    //orgcont
    private static final String SQL_INSERT_ORGCONT
            = "insert into OrgCont (OrgID, ContID) "
            + "values (?, ?)";

    private static final String SQL_DELETE_ORGCONT
            = "delete from OrgCont where OrgID = ?";

    private static final String SQL_DELETE_ORGCONT_BY_CONT
            = "delete from OrgCont where ContID = ?";

    private static final String SQL_SELECT_CONT_BY_ORG
            = "select ContID from OrgCont where OrgID = ?";

    private static final String SQL_SELECT_ORG_BY_CONT
            = "select * from OrgCont where ContID = ?";

    private static final String SQL_SELECT_ALL_ORGCONT
            = "select * from OrgCont";

    //orgloc
    private static final String SQL_INSERT_ORGLOC
            = "insert into OrgLoc (OrgID, LocID) "
            + "values (?, ?)";

    private static final String SQL_DELETE_ORGLOC
            = "delete from OrgLoc where OrgID = ?";

    private static final String SQL_DELETE_ORGLOC_BY_LOC
            = "delete from OrgLoc where LocID = ?";

    private static final String SQL_SELECT_LOC_BY_ORG
            = "select LocID from OrgLoc where OrgID = ?";

    private static final String SQL_SELECT_ORG_BY_LOC
            = "select * from OrgLoc where LocID = ?";

    private static final String SQL_SELECT_ALL_ORGLOC
            = "select * from OrgLoc";

    //heroorg
    private static final String SQL_INSERT_HEROORG
            = "insert into HeroOrg (HeroID, OrgID) "
            + "values (?, ?)";

    private static final String SQL_DELETE_HEROORG
            = "delete from HeroOrg where HeroID = ?";

    private static final String SQL_DELETE_HEROORG_BYORG
            = "delete from HeroOrg where OrgID = ?";

    private static final String SQL_SELECT_HERO_BY_ORG
            = "select heroID from HeroOrg where OrgID = ?";

    private static final String SQL_SELECT_ORG_BY_HERO
            = "select * from HeroOrg where HeroID = ?";

    private static final String SQL_SELECT_ALL_HEROORG
            = "select * from HeroOrg";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addChampion(Champion champion) {
        jdbcTemplate.update(SQL_INSERT_CHAMPION,
                champion.getName(),
                champion.getChampionDesc(),
                champion.isIsHero());

        int championId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        champion.setChampionID(championId);
        insertHerosPowers(champion);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteChampion(Champion champion) {
        jdbcTemplate.update(SQL_DELETE_HEROPOWER, champion.getChampionID());
        jdbcTemplate.update(SQL_DELETE_HEROORG, champion.getChampionID());
        jdbcTemplate.update(SQL_DELETE_HEROSIGHT, champion.getChampionID());
        jdbcTemplate.update(SQL_DELETE_CHAMPION, champion.getChampionID());

    }

    @Override
    public void updateChampion(Champion champion) {
        jdbcTemplate.update(SQL_UPDATE_CHAMPION,
                champion.getName(),
                champion.getChampionDesc(),
                champion.isIsHero(),
                champion.getChampionID());
        insertHerosPowers(champion);
    }

    @Override
    public Champion getChampionById(int id) {
        try {
            Champion champ = jdbcTemplate.queryForObject(SQL_SELECT_CHAMPION,
                    new ChampionMapper(),
                    id);
            List<Integer> powerIDs = jdbcTemplate.queryForList(SQL_SELECT_POWER_BY_HERO,
                    Integer.class,
                    champ.getChampionID());
            List<Power> powers = new ArrayList<>();
            for (int currentInt : powerIDs) {
                powers.add(jdbcTemplate.queryForObject(SQL_SELECT_POWERS,
                        new PowerMapper(),
                        currentInt));
            }
            champ.setPowers(powers);
            return champ;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Champion> getAllChampions() {
        List<Champion> champs = jdbcTemplate.query(SQL_SELECT_ALL_CHAMPIONS,
                new ChampionMapper());
        for (Champion currentChamp : champs) {
            List<Integer> powerIDs = jdbcTemplate.queryForList(SQL_SELECT_POWER_BY_HERO,
                    Integer.class,
                    currentChamp.getChampionID());
            List<Power> powers = new ArrayList<>();
            for (int currentInt : powerIDs) {
                powers.add(jdbcTemplate.queryForObject(SQL_SELECT_POWERS,
                        new PowerMapper(),
                        currentInt));
            }
            currentChamp.setPowers(powers);
        }
        return champs;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWERS,
                power.getName(),
                power.getPowerDesc()
        );
        int powerId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        power.setPowerID(powerId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePower(Power power) {
        jdbcTemplate.update(SQL_DELETE_HEROPOWER_BYPOWER, power.getPowerID());
        jdbcTemplate.update(SQL_DELETE_POWERS, power.getPowerID());
    }

    @Override
    public void updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWERS,
                power.getName(),
                power.getPowerDesc(),
                power.getPowerID()
        );
    }

    @Override
    public Power getPowerById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POWERS,
                    new PowerMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS,
                new PowerMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization org) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                org.getName(),
                org.getOrgDesc()
        );

        int orgId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        org.setOrganizationID(orgId);
        insertOrganizationsInfo(org);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(Organization org) {
        jdbcTemplate.update(SQL_DELETE_HEROORG_BYORG, org.getOrganizationID());//deleteHeroOrg
        jdbcTemplate.update(SQL_DELETE_ORGCONT, org.getOrganizationID());//deleteOrgCont
        jdbcTemplate.update(SQL_DELETE_ORGLOC, org.getOrganizationID());//deleteOrgLoc
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, org.getOrganizationID());//Delete organization
    }

    @Override
    public void updateOrganization(Organization org) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                org.getName(),
                org.getOrgDesc(),
                org.getOrganizationID()
        );
    }

    @Override
    public Organization getOrganizationById(int id) {
        try {
            Organization org = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationMapper(),
                    id);
            List<Integer> contIDs = jdbcTemplate.queryForList(SQL_SELECT_CONT_BY_ORG,
                    Integer.class,
                    org.getOrganizationID());
            List<Contact> contacts = new ArrayList<>();
            for (int currentInt : contIDs) {
                contacts.add(jdbcTemplate.queryForObject(SQL_SELECT_CONTACT,
                        new ContactMapper(),
                        currentInt));
            }
            List<Integer> locIDs = jdbcTemplate.queryForList(SQL_SELECT_LOC_BY_ORG,
                    Integer.class,
                    org.getOrganizationID());
            List<Location> locations = new ArrayList<>();
            for (int currentInt : locIDs) {
                locations.add(jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                        new LocationMapper(),
                        currentInt));
            }
            List<Integer> champIDs = jdbcTemplate.queryForList(SQL_SELECT_HERO_BY_ORG,
                    Integer.class,
                    org.getOrganizationID());
            List<Champion> champions = new ArrayList<>();
            for (int currentInt : champIDs) {
                champions.add(jdbcTemplate.queryForObject(SQL_SELECT_CHAMPION,
                        new ChampionMapper(),
                        currentInt));
            }
            for (Champion currentChampion : champions) {
                List<Integer> powerIDs = jdbcTemplate.queryForList(SQL_SELECT_POWER_BY_HERO,
                        Integer.class,
                        currentChampion.getChampionID());
                List<Power> powers = new ArrayList<>();
                for (int current : powerIDs) {
                    powers.add(jdbcTemplate.queryForObject(SQL_SELECT_POWERS,
                            new PowerMapper(),
                            current));
                }
                currentChampion.setPowers(powers);
            }
            org.setContacts(contacts);
            org.setLocations(locations);
            org.setChampions(champions);
            return org;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgs = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATION,
                new OrganizationMapper());
        for (Organization currentOrg : orgs) {
            List<Integer> contIDs = jdbcTemplate.queryForList(SQL_SELECT_CONT_BY_ORG,
                    Integer.class,
                    currentOrg.getOrganizationID());
            List<Contact> contacts = new ArrayList<>();
            for (int currentInt : contIDs) {
                contacts.add(jdbcTemplate.queryForObject(SQL_SELECT_CONTACT,
                        new ContactMapper(),
                        currentInt));
            }
            List<Integer> locIDs = jdbcTemplate.queryForList(SQL_SELECT_LOC_BY_ORG,
                    Integer.class,
                    currentOrg.getOrganizationID());
            List<Location> locations = new ArrayList<>();
            for (int currentInt : locIDs) {
                locations.add(jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                        new LocationMapper(),
                        currentInt));
            }
            List<Integer> champIDs = jdbcTemplate.queryForList(SQL_SELECT_HERO_BY_ORG,
                    Integer.class,
                    currentOrg.getOrganizationID());
            List<Champion> champions = new ArrayList<>();
            for (int currentInt : champIDs) {
                champions.add(jdbcTemplate.queryForObject(SQL_SELECT_CHAMPION,
                        new ChampionMapper(),
                        currentInt));
            }
            for (Champion currentChampion : champions) {
                List<Integer> powerIDs = jdbcTemplate.queryForList(SQL_SELECT_POWER_BY_HERO,
                        Integer.class,
                        currentChampion.getChampionID());
                List<Power> powers = new ArrayList<>();
                for (int current : powerIDs) {
                    powers.add(jdbcTemplate.queryForObject(SQL_SELECT_POWERS,
                            new PowerMapper(),
                            current));
                }
                currentChampion.setPowers(powers);
            }
            currentOrg.setContacts(contacts);
            currentOrg.setLocations(locations);
            currentOrg.setChampions(champions);
        }

        return orgs;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location loc) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                loc.getName(),
                loc.getAddress(),
                loc.getLocationDesc(),
                loc.getLongitutde(),
                loc.getLattitude()
        );
        int locId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        loc.setLocationId(locId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteLocation(Location loc) {
        jdbcTemplate.update(SQL_DELETE_ORGLOC_BY_LOC, loc.getLocationId());//delete OrgLoc
        jdbcTemplate.update(SQL_DELETE_SIGHTING_BY_LOCATION, loc.getLocationId());//delete sighting entirely
        jdbcTemplate.update(SQL_DELETE_LOCATION, loc.getLocationId());//delete location
    }

    @Override
    public void updateLocation(Location loc) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                loc.getName(),
                loc.getAddress(),
                loc.getLocationDesc(),
                loc.getLongitutde(),
                loc.getLattitude(),
                loc.getLocationId()
        );
    }

    @Override
    public Location getLocationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATION,
                new LocationMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addContact(Contact cont) {
        jdbcTemplate.update(SQL_INSERT_CONTACT,
                cont.getPhone(),
                cont.getEmail()
        );
        int contId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        cont.setContactId(contId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteContact(Contact cont) {
        jdbcTemplate.update(SQL_DELETE_ORGCONT_BY_CONT, cont.getContactId());//delete orgCont
        jdbcTemplate.update(SQL_DELETE_CONTACT, cont.getContactId());//delete contact
    }

    @Override
    public void updateContact(Contact cont) {
        jdbcTemplate.update(SQL_UPDATE_CONTACT,
                cont.getPhone(),
                cont.getEmail(),
                cont.getContactId()
        );
    }

    @Override
    public Contact getContactById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CONTACT,
                    new ContactMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CONTACT,
                new ContactMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sight) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sight.getLocation().getLocationId(),
                sight.getSightDate(),
                sight.getSightDesc());

        int SightingId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        sight.setSightingId(SightingId);
        insertSightingInfo(sight);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(Sighting sight) {
        jdbcTemplate.update(SQL_DELETE_HEROSIGHT_BY_SIGHT, sight.getSightingId());//delete heroSight
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sight.getSightingId());//deletesighting
    }

    @Override
    public void updateSighting(Sighting sight) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sight.getLocation().getLocationId(),
                sight.getSightDate(),
                sight.getSightDesc(),
                sight.getSightingId()
        );
    }

    @Override
    public Sighting getSightingById(int id) {
        try {
            Sighting sight = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(),
                    id);
            List<Integer> heroIDs = jdbcTemplate.queryForList(SQL_SELECT_HERO_BY_SIGHTING,
                    Integer.class,
                    sight.getSightingId());
            List<Champion> champions = new ArrayList<>();
            for (int currentInt : heroIDs) {
                champions.add(jdbcTemplate.queryForObject(SQL_SELECT_CHAMPION,
                        new ChampionMapper(),
                        currentInt));
                //need to add powers to the champions
            }

            for (Champion currentChampion : champions) {
                List<Integer> powerIDs = jdbcTemplate.queryForList(SQL_SELECT_POWER_BY_HERO,
                        Integer.class,
                        currentChampion.getChampionID());
                List<Power> powers = new ArrayList<>();
                for (int current : powerIDs) {
                    powers.add(jdbcTemplate.queryForObject(SQL_SELECT_POWERS,
                            new PowerMapper(),
                            current));
                }
                currentChampion.setPowers(powers);

            }
            sight.setHeroes(champions);

            Location loc = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BYSIGHTING,
                    new LocationMapper(),
                    sight.getSightingId());
            sight.setLocation(loc);
            return sight;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sights = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTING,
                new SightingMapper());
        sightingHelper(sights);
        return sights;
    }

    @Override
    public List<Sighting> getMostRecentSighting() {
        List<Sighting> sights = jdbcTemplate.query(SQL_MOST_RECENT_SIGHTINGS,
                new SightingMapper());
        sightingHelper(sights);
        return sights;
    }

    // HELPER METHODS
    // ==============
    //hero helper methods
    //insert all of a hero's power to SQL
    private void sightingHelper(List<Sighting> sights) {
        for (Sighting currentSight : sights) {
            List<Integer> heroIDs = jdbcTemplate.queryForList(SQL_SELECT_HERO_BY_SIGHTING,
                    Integer.class,
                    currentSight.getSightingId());
            List<Champion> champions = new ArrayList<>();
            for (int currentInt : heroIDs) {
                champions.add(jdbcTemplate.queryForObject(SQL_SELECT_CHAMPION,
                        new ChampionMapper(),
                        currentInt));
                //need to add powers to the champions
            }

            for (Champion currentChampion : champions) {
                List<Integer> powerIDs = jdbcTemplate.queryForList(SQL_SELECT_POWER_BY_HERO,
                        Integer.class,
                        currentChampion.getChampionID());
                List<Power> powers = new ArrayList<>();
                for (int current : powerIDs) {
                    powers.add(jdbcTemplate.queryForObject(SQL_SELECT_POWERS,
                            new PowerMapper(),
                            current));
                }
                currentChampion.setPowers(powers);

            }
            currentSight.setHeroes(champions);

            Location loc = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BYSIGHTING,
                    new LocationMapper(),
                    currentSight.getSightingId());
            currentSight.setLocation(loc);
        }
    }

    private void insertHerosPowers(Champion champion) {
        final int champid = champion.getChampionID();
        final List<Power> powers = champion.getPowers();

        //delete all existing powers associated with the championID
        jdbcTemplate.update(SQL_DELETE_HEROPOWER_BY_HERO,
                champid);

        for (Power currentPower : powers) {
            jdbcTemplate.update(SQL_INSERT_HEROPOWER,
                    champid,
                    currentPower.getPowerID());
        }
    }

    private List<Power> findPowersForChampions(Champion champ) {
        return jdbcTemplate.query(SQL_SELECT_POWER_BY_HERO,
                new PowerMapper(),
                champ.getChampionID());
    }
//use this when getting multiple champions

    private List<Champion>
            associatePowersWithChampion(List<Champion> champList) {
        // set the complete list of author ids for each book
        for (Champion currentChamp : champList) {
            // add Authors to current book
            currentChamp.setPowers(findPowersForChampions(currentChamp));
        }
        return champList;
    }

//organization helpermethods
    // insert heros/ contacts/ locations to SQL
    private void insertOrganizationsInfo(Organization org) {
        final int OrganizationId = org.getOrganizationID();
        final List<Champion> champs = org.getChampions();
        final List<Contact> contacts = org.getContacts();
        final List<Location> locations = org.getLocations();

        for (Champion currentChamp : champs) {
            jdbcTemplate.update(SQL_INSERT_HEROORG,
                    currentChamp.getChampionID(),
                    OrganizationId
            );
        }
        for (Contact currentCont : contacts) {
            jdbcTemplate.update(SQL_INSERT_ORGCONT,
                    OrganizationId,
                    currentCont.getContactId());
        }
        for (Location currentLocation : locations) {
            jdbcTemplate.update(SQL_INSERT_ORGLOC,
                    OrganizationId,
                    currentLocation.getLocationId());
        }
    }

    private List<Champion> findHerosForOrganization(Organization organization) {
        return jdbcTemplate.query(SQL_SELECT_HERO_BY_ORG,
                new ChampionMapper(),
                organization.getChampions());
    }

    private List<Contact> findContactsForOrganization(Organization organization) {
        return jdbcTemplate.query(SQL_SELECT_CONT_BY_ORG,
                new ContactMapper(),
                organization.getContacts());
    }

    private List<Location> findLocationsForOrganization(Organization organization) {
        return jdbcTemplate.query(SQL_SELECT_LOC_BY_ORG,
                new LocationMapper(),
                organization.getLocations());
    }

    private List<Organization>
            associateAllWithOrganization(List<Organization> OrgList) {
        // set the complete list of author ids for each book
        for (Organization currentOrg : OrgList) {
            // add Authors to current book
            currentOrg.setChampions(findHerosForOrganization(currentOrg));
            currentOrg.setContacts(findContactsForOrganization(currentOrg));
            currentOrg.setLocations(findLocationsForOrganization(currentOrg));
        }
        return OrgList;
    }

    //sighting helper methods
    //insert heros to SQL
    private void insertSightingInfo(Sighting sight) {
        final int SightingId = sight.getSightingId();
        final List<Champion> champs = sight.getHeroes();
        final Location loc = sight.getLocation();

        for (Champion currentChamp : champs) {
            jdbcTemplate.update(SQL_INSERT_HEROSIGHT,
                    currentChamp.getChampionID(),
                    SightingId
            );
        }

    }

    private List<Champion> findHerosBySighting(Sighting sight) {
        return jdbcTemplate.query(SQL_SELECT_HERO_BY_SIGHTING,
                new ChampionMapper(),
                sight.getSightingId());
    }

    private List<Sighting> findSightingsByHeros(Champion champ) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_HERO,
                new SightingMapper(),
                champ.getChampionID());
    }

    private List<Sighting>
            associateHerosWithSighting(List<Sighting> sightList) {
        for (Sighting currentSight : sightList) {
            currentSight.setHeroes(findHerosBySighting(currentSight));
        }
        return sightList;
    }

    // MAPPERS
    // =======
    private static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {

            Power power = new Power();
            power.setPowerID(rs.getInt("PowerID"));
            power.setName(rs.getString("Name"));
            power.setPowerDesc(rs.getString("PowerDesc"));
            return power;
        }
    }

    private static final class ChampionMapper implements RowMapper<Champion> {

        @Override
        public Champion mapRow(ResultSet rs, int i)
                throws SQLException {
            Champion champ = new Champion();
            champ.setChampionID(rs.getInt("ChampionID"));
            champ.setName(rs.getString("Name"));
            champ.setChampionDesc(rs.getString("ChampDesc"));
            champ.setIsHero(rs.getBoolean("isHero"));
            return champ;
        }
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationID(rs.getInt("OrganizationID"));
            org.setName(rs.getString("name"));
            org.setOrgDesc(rs.getString("OrgDesc"));
            return org;
        }
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setLocationId(rs.getInt("LocationID"));
            loc.setName(rs.getString("name"));
            loc.setAddress(rs.getString("address"));
            loc.setLocationDesc(rs.getString("LocationDesc"));
            loc.setLongitutde(rs.getDouble("Longitude"));
            loc.setLattitude(rs.getDouble("Latitude"));
            return loc;
        }
    }

    private static final class ContactMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int i) throws SQLException {
            Contact cont = new Contact();
            cont.setContactId(rs.getInt("contactID"));
            cont.setEmail(rs.getString("emial"));
            cont.setPhone(rs.getString("phone"));
            return cont;
        }
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sight = new Sighting();
            Location location = new Location();
            sight.setSightingId(rs.getInt("sightID"));
            location.setLocationId(rs.getInt("location"));
            location.setName(rs.getString("name"));
            location.setAddress(rs.getString("address"));
            location.setLocationDesc(rs.getString("LocationDesc"));
            location.setLongitutde(rs.getDouble("Longitude"));
            location.setLattitude(rs.getDouble("Latitude"));
            sight.setLocation(location);
            sight.setSightDate(rs.getString("sightDate"));
            sight.setSightDesc(rs.getString("sightDesc"));
            return sight;
        }
    }

}
