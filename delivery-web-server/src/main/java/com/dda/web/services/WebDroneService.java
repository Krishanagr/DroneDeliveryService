package com.dda.web.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dda.web.model.Drone;

/**
 * Hide the access to the microservice inside this local service.
 * 
 */
@Service
public class WebDroneService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebDroneService.class.getName());

	public WebDroneService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}

	public Drone findDroneByCustomerLocation(double custLatitude, double custLongitude,double CustDistance) {

		logger.info("findDroneByCustomerLocation() invoked: " + custLatitude);

		Drone drone = restTemplate.getForObject(serviceUrl + "/drones/{latitude}/{longitude}/{custDistance}", Drone.class, custLatitude, custLongitude,CustDistance);
		if (drone == null)
			throw new RuntimeException("Drone are not available in DB.");
		else
			return drone;

	}
	
	public Drone[] findAllDrones() {

		logger.info("findAllDrones() invoked");

		Drone[] drone = restTemplate.getForObject(serviceUrl + "/drones/all", Drone[].class);
		if (drone == null)
			throw new RuntimeException("Drone are not available in DB.");
		else
			return drone;

	}
}
