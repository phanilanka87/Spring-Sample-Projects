package com.jpa.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.jpa.repo","com.jpa.domain"} )
@EnableTransactionManagement
@Profile("container")
public class ContainerJPAConfig {
	
	@Bean
	public JpaTransactionManager transactionManager( EntityManagerFactory emf){
		JpaTransactionManager jpaTrxMgr = new JpaTransactionManager();
		jpaTrxMgr.setEntityManagerFactory(emf);
		return jpaTrxMgr;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEMFBean(DataSource dataSource, JpaVendorAdapter jpaAdaptor ){
		LocalContainerEntityManagerFactoryBean localEMF = new LocalContainerEntityManagerFactoryBean();
		localEMF.setDataSource(dataSource);
		localEMF.setJpaVendorAdapter(jpaAdaptor);
		localEMF.setPackagesToScan("com.jpa.domain");
		
		// Additional properties to drop the tables on each session creation
		Properties props = new Properties();
		props.setProperty("hibernate.hbm2ddl.auto", "create-drop");

		localEMF.setJpaProperties(props);
		
		return localEMF;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdaptor(){
		HibernateJpaVendorAdapter jpaAdaptor = new HibernateJpaVendorAdapter();
		jpaAdaptor.setDatabase(Database.DERBY);
		jpaAdaptor.setShowSql(true);
		jpaAdaptor.setGenerateDdl(true);
		jpaAdaptor.setDatabasePlatform("org.hibernate.dialect.DerbyTenSevenDialect");
		return jpaAdaptor;
	}
	
	@Bean
	@Profile("dev")
	public DataSource getEmbeddedDataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase ds = builder.setType(EmbeddedDatabaseType.DERBY)
				.build();
		return ds;
	}

	@Bean
	@Profile("test")
	public DataSource getNetworkDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
		ds.setUrl("jdbc:derby://localhost:1527/sample;create=true");
		ds.setUsername("app");
		ds.setPassword("pass");
		return ds;
	}
}
