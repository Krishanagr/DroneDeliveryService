package com.dda.web.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dda.web.model.Customer;

/**
 * Hide the access to the microservice inside this local service.
 * 
 */
@Service
public class WebCustomerService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebCustomerService.class.getName());

	public WebCustomerService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}

	public Customer findCustomerByDeliveryLocation(double delLatitude, double delLongitude) {

		logger.info("findCustomerByDeliveryLocation() invoked: " + delLatitude);

		Customer customer = restTemplate.getForObject(serviceUrl + "/customer/{latitude}/{longitude}", Customer.class, delLatitude, delLongitude);
		if (customer == null)
			throw new RuntimeException("Customer address not returned buy Customer Service.");
		else
			return customer;

	}
	
	public Customer[] getAllCustomerLocation() {

		logger.info("getAllCustomerLocation() invoked with URL:"+serviceUrl);

		Customer[] customer = restTemplate.getForObject(serviceUrl + "/customer/all", Customer[].class);
		
		if (customer == null)
			throw new RuntimeException("No customer location is available in DB.");
		else {
			logger.info("No of customer address returned by service:" +customer.length);
			
			return customer;
		}

	}
	
	public Customer getCustomerInfo(int custId) {

		logger.info("getCustomerInfo() for Id:"+custId);

		Customer customer = restTemplate.getForObject(serviceUrl + "/customer/{customerId}", Customer.class,custId);
		
		if (customer == null)
			throw new RuntimeException("No Customer location is available in DB.");
		else {
			logger.info("Customer address returned by service:" +customer.getAddress());
			
			return customer;
		}

	}
}
