package com.excilys.formation.java.computerdatabase.dao.mysql;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataManager extends DriverManagerDataSource {

  public DataManager() {
    super();
    setDriverClassName("com.mysql.cj.jdbc.Driver");
    setUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull&&useSSL=false&&serverTimezone=Europe/Stockholm");
    setUsername("admincdb");
    setPassword("qwerty1234");

  }
}
