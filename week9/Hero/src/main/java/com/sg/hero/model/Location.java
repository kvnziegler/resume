/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.model;

import java.util.Objects;

/**
 *
 * @author kvnzi
 */
public class Location {
    private int locationId;
    private String name;
    private String address;
    private String locationDesc;
    private double longitutde;
        private double lattitude;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public double getLongitutde() {
        return longitutde;
    }

    public void setLongitutde(double longitutde) {
        this.longitutde = longitutde;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.locationId;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.address);
        hash = 47 * hash + Objects.hashCode(this.locationDesc);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.longitutde) ^ (Double.doubleToLongBits(this.longitutde) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.lattitude) ^ (Double.doubleToLongBits(this.lattitude) >>> 32));
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitutde) != Double.doubleToLongBits(other.longitutde)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lattitude) != Double.doubleToLongBits(other.lattitude)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.locationDesc, other.locationDesc)) {
            return false;
        }
        return true;
    }

    
}
