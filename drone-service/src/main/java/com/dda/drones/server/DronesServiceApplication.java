package com.dda.drones.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import com.dda.drones.services.DBConfiguration;
/**
 * Drone Service starter main class 
 * 
 * @author Krishan AGRAWAL
 *
 */
@EnableEurekaClient
@SpringBootApplication
@Import(DBConfiguration.class)
public class DronesServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DronesServiceApplication.class, args);
	}
}
