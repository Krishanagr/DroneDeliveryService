package com.dda.drones.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Drone Rest controller class
 * 
 * @author Krishan AGRAWAL
 */

@RestController
public class DronesController {

	@Autowired
	private DroneRepository droneRepository;

	protected Logger logger = Logger.getLogger(DronesController.class.getName());

	@RequestMapping("drones/{latitude}/{longitude}/{custDistance}")
	public Drone getDroneByDroneAddress(@PathVariable("latitude") double latitude,
			@PathVariable("longitude") double longitude, @PathVariable("custDistance") double custDistance) {
		logger.info("Customer's Coordinates:- latitude:" + latitude + " longitude:" + longitude + " with distance:"
				+ custDistance);
		List<Drone> droneList = droneRepository.findAll();

		logger.info("No of drones in DB:" + droneList.size());
		Drone drone = DroneUtils.compareLocations(droneList, latitude, longitude);

		return drone;
	}

	@RequestMapping("drones/all")
	public List<Drone> getAllCustomerAddress() {
		logger.info("Get all list of customer");

		List<Drone> droneList = droneRepository.findAll();

		logger.info("No of customer in db:" + droneList.size());

		return droneList;
	}

}
