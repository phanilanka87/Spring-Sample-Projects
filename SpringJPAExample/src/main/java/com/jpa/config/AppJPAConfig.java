package com.jpa.config;

import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
	Configure Application managed Entity Manager
*/

@Configuration
@ComponentScan(basePackages = {"com.jpa.repo","com.jpa.domain"} )
@EnableTransactionManagement
@Profile("app")
public class AppJPAConfig {

	@Bean
	public JpaTransactionManager jpaTransMan(EntityManagerFactory entityMgrFactory){
		JpaTransactionManager jtManager = new JpaTransactionManager(
				entityMgrFactory);
		return jtManager;
	}
	
	@Bean
	@Profile("dev")
	public LocalEntityManagerFactoryBean devEntityManagerFactoryBean() {
		LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
		emf.setPersistenceUnitName("TestPersistanceUnitEM");
		return emf;
	}
	
	@Bean
	@Profile("test")
	public LocalEntityManagerFactoryBean testEntityManagerFactoryBean() {
		LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
		emf.setPersistenceUnitName("TestPersistanceUnitNW");
		return emf;
	}
}
