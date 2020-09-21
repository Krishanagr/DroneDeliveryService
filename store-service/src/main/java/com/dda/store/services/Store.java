package com.dda.store.services;

import javax.persistence.*;
/**
 * Store model class which is used for DB column mapping
 * 
 * @author Krishan AGRAWAL
 */
@Entity
@Table(name="T_STORE")
public class Store {
    @Column(name="STORE_ID")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int storeId;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="LATITUDE")
    private double latitude;

    @Column(name="LONGITUDE")
    private double longitude;

    @Transient
    private double distance;
   
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
    
    public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
    
}
