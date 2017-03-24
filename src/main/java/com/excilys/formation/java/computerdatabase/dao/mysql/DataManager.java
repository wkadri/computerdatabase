package com.excilys.formation.java.computerdatabase.dao.mysql;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataManager extends DriverManagerDataSource {

  public DataManager() {
    super();
    this.setDriverClassName("com.mysql.cj.jdbc.Driver");
    this.setUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull&&useSSL=false&&serverTimezone=Europe/Stockholm");
    this.setUsername("admincdb");
    this.setPassword("qwerty1234");
 
  }
}
