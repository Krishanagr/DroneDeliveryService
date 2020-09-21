package com.dda.customer.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import com.dda.customer.services.DBConfiguration;
/**
 * Customer Service starter main class 
 * 
 * @author Krishan AGRAWAL
 *
 */
@EnableEurekaClient
@SpringBootApplication
@Import(DBConfiguration.class)
public class CustomerServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
}
