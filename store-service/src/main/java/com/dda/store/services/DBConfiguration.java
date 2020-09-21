package com.dda.store.services;

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
@EntityScan("com.dda.store.services")
@EnableJpaRepositories("com.dda.store.services")
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
		List<Map<String, Object>> locs = jdbcTemplate.queryForList("SELECT STORE_ID FROM T_STORE");
		logger.info("System has " + locs.size() + " Store locations.");

		return dataSource;
	}
}
