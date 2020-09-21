package com.dda.web.model;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Store model class which is used for DB column mapping
 * 
 * @author Krishan AGRAWAL
 */
@JsonRootName("Store")
public class Store {
    private int storeId;

    private String address;

    private double latitude;

    private double longitude;
 
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
}
