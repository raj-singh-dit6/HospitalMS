package com.hospitalms.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.hospitalms.repository")
public class DataSourceConfig {

  private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);
	
   @Autowired
   private Environment springEnvironment;

   @Bean
   public DataSource dataSource() {
	  
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(springEnvironment.getProperty("hms.database.classname",
              "com.mysql.jdbc.Driver"));
      dataSource.setUrl(springEnvironment.getProperty("hms.database.url", 
              "jdbc:mysql://localhost:3306/master_db") + "?createDatabaseIfNotExist=true");
      dataSource.setUsername(springEnvironment.getProperty("hms.database.user", "root"));
      dataSource.setPassword(springEnvironment.getProperty("hms.database.password", "admin"));
      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean 
              = new LocalContainerEntityManagerFactoryBean();
      entityManagerFactoryBean.setDataSource(dataSource());
      entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
      entityManagerFactoryBean.setPackagesToScan(new String[]{"com.hospitalms.model"});
      entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
      entityManagerFactoryBean.setPersistenceUnitName("hms");
      return entityManagerFactoryBean;
   }

   private Properties getHibernateProperties() {
      Properties properties = new Properties();
      properties.put("hibernate.dialect", springEnvironment.getProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect"));
      properties.put("hibernate.show_sql", springEnvironment.getProperty("hibernate.show_sql", "true"));
      properties.put("hibernate.format_sql", springEnvironment.getProperty("hibernate.format_sql", "true"));
      properties.put("hibernate.hbm2ddl.auto", springEnvironment.getProperty("hibernate.hbm2ddl.auto", "update"));
      properties.put("hibernate.id.new_generator_mappings", springEnvironment.getProperty("hibernate.id.new_generator_mappings", "false"));
      properties.put("hibernate.physical_naming_strategy", "com.hospitalms.configuration.CustomNamingStrategy");
      
      
      return properties;
   }

   @Bean
   public JpaTransactionManager transactionManager(EntityManagerFactory entityManager) {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManager);
      return transactionManager;
   }

}
