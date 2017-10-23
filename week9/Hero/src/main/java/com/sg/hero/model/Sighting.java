/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author kvnzi
 */
public class Sighting {
    private int sightingId;
    private Location location;
    private String sightDate;
    private String sightDesc;
    private List<Champion> heroes;

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSightDate() {
        return sightDate;
    }

    public void setSightDate(String sightDate) {
        this.sightDate = sightDate;
    }

    public String getSightDesc() {
        return sightDesc;
    }

    public void setSightDesc(String sightDesc) {
        this.sightDesc = sightDesc;
    }

    public List<Champion> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Champion> heroes) {
        this.heroes = heroes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.sightingId;
        hash = 29 * hash + Objects.hashCode(this.location);
        hash = 29 * hash + Objects.hashCode(this.sightDate);
        hash = 29 * hash + Objects.hashCode(this.sightDesc);
        hash = 29 * hash + Objects.hashCode(this.heroes);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.sightDate, other.sightDate)) {
            return false;
        }
        if (!Objects.equals(this.sightDesc, other.sightDesc)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.heroes, other.heroes)) {
            return false;
        }
        return true;
    }

    

    
}
