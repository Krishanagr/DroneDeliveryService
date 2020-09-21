package com.dda.web.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.dda.web.services.WebController;
import com.dda.web.services.WebCustomerService;
import com.dda.web.services.WebStoreService;
import com.dda.web.services.WebDroneService;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, //
        DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false) // Disable component scanner
public class WebServer {

    /**
     * URL uses the logical name of drones-service - upper or lower case, doesn't
     * matter.
     */
    public static final String DRONES_SERVICE_URL = "http://DRONES-SERVICE";
    public static final String CUSTOMER_SERVICE_URL = "http://CUSTOMER-SERVICE";
    public static final String STORE_SERVICE_URL = "http://STORE-SERVICE";

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     * 
     * @param args Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for web-server.properties or web-server.yml
    	System.setProperty("registration.server.hostname", "localhost");
        System.setProperty("spring.config.name", "web-server");
        SpringApplication.run(WebServer.class, args);
    }

    /**
     * A customized RestTemplate that has the ribbon load balancer build in. Note
     * that prior to the "Brixton"
     * 
     * @return
     */
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * The WebDroneService encapsulates the interaction with the micro-service.
     * 
     * @return A new service instance.
     */
    @Bean
    public WebDroneService webDroneService() {
        return new WebDroneService(DRONES_SERVICE_URL);
    }

    /**
     * The WebStoreService encapsulates the interaction with the micro-service.
     * 
     * @return A new service instance.
     */
    @Bean
    public WebStoreService webStoreService() {
        return new WebStoreService(STORE_SERVICE_URL);
    }
    
    /**
     * The WebCustomerService encapsulates the interaction with the micro-service.
     * 
     * @return A new service instance.
     */
    @Bean
    public WebCustomerService webCustomerService() {
        return new WebCustomerService(CUSTOMER_SERVICE_URL);
    }
    
    /**
     * Create the controller, passing it the {@link WebDroneService} to use.
     * 
     * @return
     */
    @Bean
    public WebController webController() {
        return new WebController(webDroneService(),webCustomerService(),webStoreService());
    }

}
