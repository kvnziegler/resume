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
public class Champion {
    
    private int championID;
    private String name;
    private String  championDesc;
    private boolean isHero;
    private List<Power> powers;

    public int getChampionID() {
        return championID;
    }

    public void setChampionID(int championID) {
        this.championID = championID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChampionDesc() {
        return championDesc;
    }

    public void setChampionDesc(String championDesc) {
        this.championDesc = championDesc;
    }

    public boolean isIsHero() {
        return isHero;
    }

    public void setIsHero(boolean isHero) {
        this.isHero = isHero;
    }

    public List getPowers() {
        return powers;
    }

    public void setPowers(List powers) {
        this.powers = powers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.championID;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.championDesc);
        hash = 41 * hash + (this.isHero ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.powers);
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
        final Champion other = (Champion) obj;
        if (this.championID != other.championID) {
            return false;
        }
        if (this.isHero != other.isHero) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.championDesc, other.championDesc)) {
            return false;
        }
        if (!Objects.equals(this.powers, other.powers)) {
            return false;
        }
        return true;
    }

    
            
            
    
}
