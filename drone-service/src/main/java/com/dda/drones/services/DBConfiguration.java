package com.dda.drones.services;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Configuration
@ComponentScan
@EntityScan("com.dda.drones.services")
@EnableJpaRepositories("com.dda.drones.services")
@PropertySource("classpath:db-config.properties")
public class DBConfiguration {

	protected Logger logger;

	public DBConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	/**
	 * Creates an in-memory "rewards" database populated with test data for fast
	 * testing
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some test data
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:initialdata/dbschema.sql")
				.addScript("classpath:initialdata/testdata.sql").build();

		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> locs = jdbcTemplate.queryForList("SELECT DRONE_ID FROM T_DRONE");
		logger.info("System has " + locs.size() + " Drone locations");

		return dataSource;
	}
}
