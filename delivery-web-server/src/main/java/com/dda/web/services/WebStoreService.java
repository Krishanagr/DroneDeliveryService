package com.dda.web.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dda.web.model.Store;

/**
 * Hide the access to the microservice inside this local service.
 * 
 */
@Service
public class WebStoreService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebStoreService.class.getName());

	public WebStoreService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}

	public Store[] getAllStoreLocation() {

		logger.info("getAllStoreLocation() invoked with URL:"+serviceUrl);

		Store[] store = restTemplate.getForObject(serviceUrl + "/store/all", Store[].class);
		
		if (store == null)
			throw new RuntimeException("No store location is available in DB.");
		else {
			logger.info("No of store address returned by service:" +store.length);
			
			return store;
		}

	}
	
	public Store getStoreLocation(int storeId) {

		logger.info("getStoreLocation() for Id:"+storeId);

		Store store = restTemplate.getForObject(serviceUrl + "/store/{storeId}", Store.class,storeId);
		
		if (store == null)
			throw new RuntimeException("No store location is available in DB.");
		else {
			logger.info("Store address returned by service:" +store.getAddress());
			
			return store;
		}

	}
	
	public Store findStoreByCustLocation(double custLatitude, double custLongitude) {

		logger.info("findStoreBystoreLocation() invoked: " + custLatitude);

		Store store = restTemplate.getForObject(serviceUrl + "/store/{latitude}/{longitude}", Store.class, custLatitude, custLongitude);
		if (store == null)
			throw new RuntimeException("Store address not returned by Store Service.");
		else
			return store;

	}	
}
