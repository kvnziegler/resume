/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.model;

import java.util.List;
import java.util.Objects;


/**
 *
 * @author kvnzi
 */
public class Organization {
    private int organizationID;
    private String name;
    private String orgDesc;
    private List<Champion> champions;
    private List<Location> locations;
    private List<Contact> contacts;

    public List getContacts() {
        return contacts;
    }

    public void setContacts(List contacts) {
        this.contacts = contacts;
    }

    public List getLocations() {
        return locations;
    }

    public void setLocations(List locations) {
        this.locations = locations;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public List getChampions() {
        return champions;
    }

    public void setChampions(List champions) {
        this.champions = champions;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.organizationID;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.orgDesc);
        hash = 43 * hash + Objects.hashCode(this.champions);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        if (this.organizationID != other.organizationID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.orgDesc, other.orgDesc)) {
            return false;
        }
        if (!Objects.equals(this.champions, other.champions)) {
            return false;
        }
        return true;
    }

    
}
