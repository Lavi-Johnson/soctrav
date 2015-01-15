package com.yanni.sotrav.configs.database;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yanni.sotrav.manager.UserManager;
import com.yanni.sotrav.models.User;

/**
 * Contains database configurations.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

  // ==============
  // PRIVATE FIELDS
  // ==============
  
  @Autowired
  private Environment _env;

  @Autowired
  //@Qualifier("masterDataSource")
  //@Qualifier("dataSource")
  private DataSource _dataSource;

  @Autowired
  private LocalContainerEntityManagerFactoryBean _entityManagerFactory;

  // ==============
  // PUBLIC METHODS
  // ==============

  /**
   * DataSource definition for database connection. Settings are read from
   * the application.properties file (using the _env object).
   */
  @Bean(name = "masterDataSource")
  @Primary
  public DataSource dataSource() {	
	BasicDataSource basicDataSource = new BasicDataSource();
	basicDataSource.setDriverClassName(_env.getProperty("db.driver"));
	basicDataSource.setUrl(_env.getProperty("db.url"));
	basicDataSource.setUsername(_env.getProperty("db.username"));
	basicDataSource.setPassword(_env.getProperty("db.password"));
	//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	//    dataSource.setDriverClassName(_env.getProperty("db.driver"));
	//    dataSource.setUrl(_env.getProperty("db.url"));
	//    dataSource.setUsername(_env.getProperty("db.username"));
	//    dataSource.setPassword(_env.getProperty("db.password"));
    //dataSource.setConnectionProperties(connectionProperties);
    return basicDataSource;
  }
  
  @Bean(name = "dataSource")
  public DataSource slaveDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(_env.getProperty("db.driver"));
		basicDataSource.setUrl(_env.getProperty("db.url"));
		basicDataSource.setUsername(_env.getProperty("db.username"));
		//basicDataSource.setPassword(_env.getProperty("db.password"));
	    return basicDataSource;
  }

  /**
   * Declare the JPA entity manager factory.
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactory =
        new LocalContainerEntityManagerFactoryBean();
    
    entityManagerFactory.setDataSource(_dataSource);
    
    // Classpath scanning of @Component, @Service, etc annotated class
    entityManagerFactory.setPackagesToScan(
        _env.getProperty("entitymanager.packagesToScan"));
    
    // Vendor adapter
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
    
    // Hibernate properties
    //System.out.println("debugg "+_env.getProperty("hibernate.dialect"));
    Properties additionalProperties = new Properties();
    additionalProperties.put(
        "hibernate.dialect", 
        _env.getProperty("hibernate.dialect"));
    additionalProperties.put(
        "hibernate.show_sql", 
        _env.getProperty("hibernate.show_sql"));
    additionalProperties.put(
        "hibernate.hbm2ddl.auto", 
        _env.getProperty("hibernate.hbm2ddl.auto"));
    entityManagerFactory.setJpaProperties(additionalProperties);
    
    return entityManagerFactory;
  }

  /**
   * Declare the transaction manager.
   */
  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = 
        new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        _entityManagerFactory.getObject());
    return transactionManager;
  }
  
  /**
   * PersistenceExceptionTranslationPostProcessor is a bean post processor
   * which adds an advisor to any bean annotated with Repository so that any
   * platform-specific exceptions are caught and then rethrown as one
   * Spring's unchecked data access exceptions (i.e. a subclass of 
   * DataAccessException).
   */
  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

} // class DatabaseConfig
