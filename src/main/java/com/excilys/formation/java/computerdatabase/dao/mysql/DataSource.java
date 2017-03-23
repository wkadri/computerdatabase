package com.excilys.formation.java.computerdatabase.dao.mysql;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSource extends DriverManagerDataSource {

  public DataSource() {
    this.setDriverClassName("com.mysql.jdbc.Driver");
    this.setUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull");
    this.setUsername("admincdb");
    this.setPassword("qwerty1234");
  }
}
