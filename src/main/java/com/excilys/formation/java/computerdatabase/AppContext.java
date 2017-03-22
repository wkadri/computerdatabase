package com.excilys.formation.java.computerdatabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.excilys.formation.java.computerdatabase.dao.ICompanyDAO;
import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.mysql.CompanyDAO;
import com.excilys.formation.java.computerdatabase.dao.mysql.ComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.mysql.DAOUtils;
import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;
import com.excilys.formation.java.computerdatabase.service.ICompanyService;
import com.excilys.formation.java.computerdatabase.service.IComputerService;

@Configuration
public class AppContext {

  @Bean()
  public DAOUtils daoUtils() {

    return new DAOUtils();
  }

  @Bean
  public IComputerDAO iComputerDAO() {
    return new ComputerDAO();
  }



  @Bean
  public ComputerService computerService() {
    return new ComputerService();
  }
}
