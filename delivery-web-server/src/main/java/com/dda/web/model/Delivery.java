package com.dda.web.model;

public class Delivery {

	private Store store;
	private Drone drone;

	private double distStoreToDrone;
	private double distCustToStore;

	private double travelTimeStoreToDrone;
	private double travelTimeCustToStore;
	private String totalTravelDistance;
	private String totalTravelTime;
	
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	public double getDistStoreToDrone() {
		return distStoreToDrone;
	}

	public void setDistStoreToDrone(double distStoreToDrone) {
		this.distStoreToDrone = distStoreToDrone;
	}

	public double getDistCustToStore() {
		return distCustToStore;
	}

	public void setDistCustToStore(double distCustToDrone) {
		this.distCustToStore = distCustToDrone;
	}

	public double getTravelTimeStoreToDrone() {
		return travelTimeStoreToDrone;
	}

	public void setTravelTimeStoreToDrone(double travelTimeStoreToDrone) {
		this.travelTimeStoreToDrone = travelTimeStoreToDrone;
	}

	public double getTravelTimeCustToStore() {
		return travelTimeCustToStore;
	}

	public void setTravelTimeCustToStore(double travelTimeCustToStore) {
		this.travelTimeCustToStore = travelTimeCustToStore;
	}

	public String getTotalTravelDistance() {
		return totalTravelDistance;
	}

	public void setTotalTravelDistance(String totalTravelDistance) {
		this.totalTravelDistance = totalTravelDistance;
	}

	public String getTotalTravelTime() {
		return totalTravelTime;
	}

	public void setTotalTravelTime(String totalTravelTime) {
		this.totalTravelTime = totalTravelTime;
	}

}
