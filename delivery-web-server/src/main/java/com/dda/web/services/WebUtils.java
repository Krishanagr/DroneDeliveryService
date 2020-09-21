package com.dda.web.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

import com.dda.web.model.Delivery;

public class WebUtils {

	// Earth radius in meters
	public static final int EARTH_RADIUS_METERS = 6371000;

	// Drone velocity meter per seconds.
	public static final double DRONE_VELOCITY_MPS = 0.06;

	protected static Logger logger = Logger.getLogger(WebUtils.class.getName());

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

/*	public static Drone compareLocations(List<Drone> droneList, double latitude, double longitude) {
		SortedMap<Double, Drone> map = new TreeMap<Double, Drone>();
		for (Drone drone : droneList) {
			double distance = WebUtils.calculateDistance(drone.getLatitude(), drone.getLongitude(), latitude,
					longitude);
			logger.info("Drone " + drone.getDroneId() + " has distance :" + distance);
			drone.setDistance(distance);
			map.put(distance, drone);
		}
		return map.get(map.firstKey());
	}
*/
	public static void calculateTime(Delivery delivery) {
		double timeDroneToCust = roundOfdouble(delivery.getDistStoreToDrone() * WebUtils.DRONE_VELOCITY_MPS);
		logger.info("Time to pickup goods by drone(in secs):" + timeDroneToCust);
		double timeCustToDel = roundOfdouble(delivery.getDistCustToStore() * WebUtils.DRONE_VELOCITY_MPS);
		logger.info("Time to deliver goods from store to customer address by drone(in secs):" + timeCustToDel);
		delivery.setTravelTimeStoreToDrone(timeDroneToCust);
		delivery.setTravelTimeCustToStore(timeCustToDel);
		// Total Distance in kilometres
		delivery.setTotalTravelDistance(convertTotalDistanceInKm(delivery.getDistStoreToDrone() + delivery.getDistCustToStore()));
		// Total time in minutes and secs.
		delivery.setTotalTravelTime(convertTimeInMinutes(timeDroneToCust + timeCustToDel));
	}

	public static String convertTimeInMinutes(double timeInSecs) {
		String timeInMinSecs = null;

		BigDecimal actualTime = new BigDecimal(timeInSecs);
		BigDecimal seconds = new BigDecimal(60);

		// dividing original time to get time in minutes
		BigDecimal timeinMinutes = actualTime.divide(seconds, 2, RoundingMode.CEILING);

		// to get value of seconds from minutes
		double secondsValue = timeinMinutes.doubleValue() - Math.floor(timeinMinutes.doubleValue());

		// Seconds will be removed from Minutes to get only Minutes value
		BigDecimal deductiableSecondValue = new BigDecimal(secondsValue);
		timeinMinutes = timeinMinutes.subtract(deductiableSecondValue);
		timeinMinutes = new BigDecimal(Math.ceil(timeinMinutes.doubleValue()));

		// Converting remaining minutes value to seconds and discarding milliseconds
		int secondVal = (int) roundOfdouble((60 * secondsValue / 100) * 100);

		timeInMinSecs = timeinMinutes + " Mins & " + secondVal + " Secs";
		logger.info("Total delivery time:"+timeInMinSecs);
		return timeInMinSecs;
	}

	public static String convertTotalDistanceInKm(double totalDistInMeters) {
		return Double.toString(roundOfdouble(totalDistInMeters / 1000)) + " km";
	}

	public static double roundOfdouble(double value) {
		return (double) Math.round(value * 100) / 100;
	}
}
