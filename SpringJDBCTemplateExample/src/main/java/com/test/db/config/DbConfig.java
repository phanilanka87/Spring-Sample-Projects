package com.test.db.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DbConfig {

	@Bean
	@Profile("dev")
	public DataSource getEmbeddedDataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase ds = builder.setType(EmbeddedDatabaseType.DERBY)
				.addScripts("db/sql/Create-Schema.sql", "db/sql/Insert-Data.sql")
				.build();
		return ds;
	}

	@Bean
	@Profile("test")
	public DataSource getTestDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.apachederby.jdbc.ClientDriver");
		ds.setUrl("jdbc:derby://localhost:1527/sample;create=true");
		ds.setUsername("app");
		ds.setPassword("pass");
		return ds;
	}
}
