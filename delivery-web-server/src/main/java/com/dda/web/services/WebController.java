package com.dda.web.services;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dda.web.model.Customer;
import com.dda.web.model.Delivery;
import com.dda.web.model.Drone;
import com.dda.web.model.Store;

@Controller
public class WebController {

	@Autowired
	protected WebDroneService droneService;

	@Autowired
	protected WebCustomerService customerService;

	@Autowired
	protected WebStoreService storeService;

	protected Logger logger = Logger.getLogger(WebController.class.getName());

	public WebController(WebDroneService droneService, WebCustomerService customerService,
			WebStoreService storeService) {
		this.droneService = droneService;
		this.storeService = storeService;
		this.customerService = customerService;
	}

	@RequestMapping("/store/all")
	public String getStoreLocations(Model model) {

		Store[] delAddresses = storeService.getAllStoreLocation();
		model.addAttribute("delAddresses", delAddresses);

		return "storeList";
	}
/*
	@RequestMapping("/store/{storeId}")
	public String getShortestPath(Model model, @PathVariable("storeId") int storeId) {

		logger.info("Store id :" + storeId);
		Store del = storeService.getStoreLocation(storeId);
		if (del != null) {
			logger.info("Store Address :" + del.getAddress());
			Customer cust = customerService.findCustomerByDeliveryLocation(del.getLatitude(), del.getLongitude());

			if (cust != null) {
				logger.info("Nearest Customer Address :" + cust.getAddress() + " with distance:" + cust.getDistance());
				Drone drone = droneService.findDroneByCustomerLocation(cust.getLatitude(), cust.getLongitude(),
						cust.getDistance());
				if (drone != null) {
					logger.info(
							"Nearest Drone Address :" + drone.getAddress() + " with distance:" + drone.getDistance());

					model.addAttribute("del", del);
					model.addAttribute("cust", cust);
					model.addAttribute("drone", drone);
					// model.addAttribute("", attributeValue);
				}
			}
		} else {
			throw new RuntimeException("Error while getting selected store address.");
		}
		return "store";
	}*/

	@RequestMapping("/customer/all")
	public String getCustomerLocations(Model model) {

		Customer[] delAddresses = customerService.getAllCustomerLocation();
		model.addAttribute("custAddresses", delAddresses);

		return "customerList";
	}

	@RequestMapping("/customer/{custId}")
	public String getShortestPathForCustomer(Model model, @PathVariable("custId") int custId) {

		logger.info("Store id :" + custId);
		Customer cust = customerService.getCustomerInfo(custId);
		if (cust != null) {
			logger.info("Customer Store Address :" + cust.getAddress());
			Delivery del= getStoreWithCustDistances(cust);
			logger.info("Store selected:"+del.getStore().getStoreId());
			logger.info("Drone selected:"+del.getDrone().getDroneId());
			WebUtils.calculateTime(del);
			model.addAttribute("del", del);
			model.addAttribute("cust", cust);

			
		} else {
			throw new RuntimeException("Error while getting selected store address.");
		}
		return "customer";
	}

	private Delivery getStoreWithCustDistances( Customer cust) {
		Store[] stores = storeService.getAllStoreLocation();
		SortedMap<Double, Delivery> map = new TreeMap<Double, Delivery>();
		Drone[] drones = droneService.findAllDrones();
		for (Store store : stores) {
			double distStoreCust = WebUtils.calculateDistance(store.getLatitude(), store.getLongitude(), cust.getLatitude(),
					cust.getLongitude());
			logger.info("Store " + store.getStoreId() + " has distance :" + distStoreCust);
			// Get drones distance from Store
			for (Drone drone : drones) {
				double distStoreDrone = WebUtils.calculateDistance(store.getLatitude(), store.getLongitude(), drone.getLatitude(),
						drone.getLongitude());
				logger.info("Store " + store.getStoreId() + " & drone " + drone.getDroneId() + " has distance :" + distStoreDrone);
				Delivery del=new Delivery();
				del.setStore(store);
				del.setDrone(drone);
				del.setDistCustToStore(distStoreCust);
				del.setDistStoreToDrone(distStoreDrone);
				map.put(distStoreCust+distStoreDrone, del);
			}
		}
		return map.get(map.firstKey());
	}
}
