package com.dda.drones.services;

import javax.persistence.*;
/**
 * Drone model class which is used for DB column mapping
 * 
 * @author Krishan AGRAWAL
 */
@Entity
@Table(name="T_DRONE")
public class Drone {
    @Column(name="DRONE_ID")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int droneId;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="LATITUDE")
    private double latitude;

    @Column(name="LONGITUDE")
    private double longitude;
    
    public int getDroneId() {
        return droneId;
    }

    public void setDroneId(int droneId) {
        this.droneId = droneId;
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
