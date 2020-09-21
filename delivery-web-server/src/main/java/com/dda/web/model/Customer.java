package com.dda.web.model;

import com.fasterxml.jackson.annotation.JsonRootName;
/**
 * Customer model class which is used for DB column mapping
 * 
 * @author Krishan AGRAWAL
 */
@JsonRootName("Customer")
public class Customer {
    private int customerId;

    private String name;

    private String address;

    private double latitude;

    private double longitude;

    public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
