package com.spring.api.Config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.spring.api.Entities.*;

import static org.hibernate.cfg.Environment.*;

@Configuration
//@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.dao"),
    @ComponentScan("com.service") })
public class AppConfig {

  @Bean
  public LocalSessionFactoryBean getSessionFactory() {
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
    
    Properties props = new Properties();
    
    // Setting JDBC properties
    props.setProperty(DRIVER, "com.mysql.cj.jdbc.Driver");
    props.setProperty(URL, "jdbc:mysql://ubuntu-mysql.creativa.com:3306/login?useSSL=false");
    props.setProperty(USER, "developer");
    props.setProperty(PASS, "rjniKzBeWObf");
    
    props.setProperty(SHOW_SQL, "true");
    props.setProperty(HBM2DDL_AUTO, "validate");
    
    props.setProperty(C3P0_MIN_SIZE, "5");
    props.setProperty(C3P0_MAX_SIZE, "20");
    props.setProperty(C3P0_ACQUIRE_INCREMENT, "1");
    props.setProperty(C3P0_TIMEOUT, "1800");
    props.setProperty(C3P0_MAX_STATEMENTS, "150");
    
    factoryBean.setHibernateProperties(props);
    factoryBean.setAnnotatedClasses(Users.class, Role.class, UsersRole.class);
    
    return factoryBean;
  }

  @Bean
  public HibernateTransactionManager getTransactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(getSessionFactory().getObject());
    return transactionManager;
  }
}