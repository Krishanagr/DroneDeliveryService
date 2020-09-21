package com.dda.store.services;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Store Rest controller class
 * 
 * @author Krishan AGRAWAL
 */

@RestController
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    protected Logger logger = Logger.getLogger(StoreController.class.getName());

  
    @RequestMapping (value = "store/all")
    public List<Store> getStoreAddress(/*Model model*/){
        
    	logger.info("Get all customer address...");
    	
        List<Store> storeList =storeRepository.findAll();
        return storeList;
    }
    
    @RequestMapping (value = "store/{storeId}")
    public Store getStoreAddressById(@PathVariable Integer storeId){
        
    	logger.info("Get getStoreAddressById:"+storeId);
    	
        Store store =storeRepository.findById(storeId).get();
        
        logger.info("Selected store address:"+store.getAddress());
        
        return store;
    }
    
    @RequestMapping ("store/{latitude}/{longitude}")
    public Store getDroneByStoreAddress(@PathVariable("latitude") double latitude, @PathVariable("longitude") double longitude){
        logger.info("Delivry address's Coordinates:- latitude:"+latitude +" longitude:"+longitude);
        
        List<Store> storeList =storeRepository.findAll();

        Store store= compareLocations(storeList, latitude, longitude);
        
        logger.info("Store choosen based on minimum distance from customer location:"+store.getStoreId());
        
		return store;
	}

	public Store compareLocations(List<Store> custList, double latitude, double longitude) {
		SortedMap<Double, Store> map = new TreeMap<Double, Store>();
		for (Store del : custList) {
			double distance = StoreUtils.calculateDistance(del.getLatitude(), del.getLongitude(), latitude, longitude);
			logger.info("Store " + del.getStoreId() + " has distance :" + distance);
			del.setDistance(distance);
			map.put(distance, del);
		}
		return map.get(map.firstKey());
	}
}
