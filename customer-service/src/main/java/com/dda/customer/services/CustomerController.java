package com.dda.customer.services;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Customer Rest controller class
 * 
 * @author Krishan AGRAWAL
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    protected Logger logger = Logger.getLogger(CustomerController.class.getName());

    /**
     * Get all customer info from DB
     * @return
     */
    @RequestMapping ("customer/all")
    public List<Customer> getAllCustomerAddress(){
        logger.info("Get all list of customer");
        
        List<Customer> customerList =customerRepository.findAll();
 
        logger.info("No of customer in db:"+customerList.size());
        
		return customerList;
	}
    
    /**
     * Get Customer info based on custId
     * @param custId
     * @return
     */
    @RequestMapping (value = "customer/{custId}")
    public Customer getCustomerInfoById(@PathVariable Integer custId){
        
    	logger.info("Get getCustomerInfoById:"+custId);
    	
    	Customer customer =customerRepository.findById(custId).get();
        
        logger.info("Selected delivery address:"+customer.getAddress());
        
        return customer;
    }
    
    /**
     * Get nearest customer based on given latitude and longitude value
     * @param latitude
     * @param longitude
     * @return
     */
    @RequestMapping ("customer/{latitude}/{longitude}")
    public Customer getDroneByCustomerAddress(@PathVariable("latitude") double latitude, @PathVariable("longitude") double longitude){
        logger.info("Delivry address's Coordinates:- latitude:"+latitude +" longitude:"+longitude);
        
        List<Customer> customerList =customerRepository.findAll();

        Customer customer= compareLocations(customerList, latitude, longitude);
        
        logger.info("Customer choosen based on minimum distance from delivery location:"+customer.getCustomerId());
        
		return customer;
	}

    /**
     * Compare customers from given list with latitude and longitude and nearest customer info
     * @param custList
     * @param latitude
     * @param longitude
     * @return
     */
	public Customer compareLocations(List<Customer> custList, double latitude, double longitude) {
		SortedMap<Double, Customer> map = new TreeMap<Double, Customer>();
		for (Customer cust : custList) {
			double distance = CustomerUtils.calculateDistance(cust.getLatitude(), cust.getLongitude(), latitude, longitude);
			logger.info("Customer " + cust.getCustomerId() + " has distance :" + distance);
			map.put(distance, cust);
		}
		return map.get(map.firstKey());
	}
}
