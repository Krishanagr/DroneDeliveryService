package com.dda.drones.services;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

public class DroneUtils {

	// Earth radius in meters
	public static final int EARTH_RADIUS_METERS = 6371000;

	// Drone velocity meter per seconds.
	public static final double DRONE_VELOCITY_MPS = 0.06;

	protected static Logger logger = Logger.getLogger(DroneUtils.class.getName());

	/*
	 * Haversine formula : a = sin²(Δφ/2) + cos φ1 ⋅ cos φ2 ⋅ sin²(Δλ/2) c = 2 ⋅
	 * atan2( √a, √(1−a) ) d = R ⋅ c where φ is latitude, λ is longitude, R is
	 * earth’s radius (mean radius = 6,371km);
	 */
	public static double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {

		logger.info(
				"calculate distance between " + latitude1 + ", " + longitude1 + ", " + latitude2 + ", " + longitude2);
		// Converting latitude in radians
		double latRadians1 = latitude1 * Math.PI / 180;

		// Converting longitude in radians
		double latRadians2 = latitude2 * Math.PI / 180;

		// Calculating delta of latitude and converting in radians
		double latDeltaRadians = (latitude2 - latitude1) * Math.PI / 180;

		// Calculating delta of longitude and converting in radians
		double longDeltaRadians = (longitude2 - longitude1) * Math.PI / 180;

		// Calculating square of half the chord length between the points
		double halfChordLength = Math.sin(latDeltaRadians / 2) * Math.sin(latDeltaRadians / 2) + Math.cos(latRadians1)
				* Math.cos(latRadians2) * Math.sin(longDeltaRadians / 2) * Math.sin(longDeltaRadians / 2);

		// Calculating angular distance
		double angularDistance = 2 * Math.atan2(Math.sqrt(halfChordLength), Math.sqrt(1 - halfChordLength));

		// distance between 2 GPS locations with round of value
		double distance = (double) Math.round(EARTH_RADIUS_METERS * angularDistance * 100) / 100; // in meters
		logger.info("Distance(in meters):" + distance);
		return distance;
	}

	public static Drone compareLocations(List<Drone> droneList, double latitude, double longitude) {
		SortedMap<Double, Drone> map = new TreeMap<Double, Drone>();
		for (Drone drone : droneList) {
			double distance = DroneUtils.calculateDistance(drone.getLatitude(), drone.getLongitude(), latitude,
					longitude);
			logger.info("Drone " + drone.getDroneId() + " has distance :" + distance);
			map.put(distance, drone);
		}
		return map.get(map.firstKey());
	}

	public static double roundOfdouble(double value) {
		return (double) Math.round(value * 100) / 100;
	}
}
