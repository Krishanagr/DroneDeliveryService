package com.dda.customer.services;

import javax.persistence.*;
/**
 * Customer model class which is used for DB column mapping
 * 
 * @author Krishan AGRAWAL
 */
@Entity
@Table(name="T_CUSTOMER")
public class Customer {
    @Column(name="CUSTOMER_ID")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int customerId;

    @Column(name="NAME")
    private String name;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="LATITUDE")
    private double latitude;

    @Column(name="LONGITUDE")
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
