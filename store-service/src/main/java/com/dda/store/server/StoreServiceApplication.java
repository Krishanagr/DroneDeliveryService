package com.dda.store.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import com.dda.store.services.DBConfiguration;
/**
 * Store Service starter main class 
 * 
 * @author Krishan AGRAWAL
 *
 */
@EnableEurekaClient
@SpringBootApplication
@Import(DBConfiguration.class)
public class StoreServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StoreServiceApplication.class, args);
	}
}
