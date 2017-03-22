package com.excilys.formation.java.computerdatabase.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.excilys.formation.java.computerdatabase.dao.mysql.CompanyDAO;
import com.excilys.formation.java.computerdatabase.dao.mysql.DAOUtils;

@Configuration
//@ComponentScan(basePackages = { "com.excilys.formation.java.computerdatabase.dao" })
public class AppContextDAO {

  @Bean()
  public DAOUtils daoUtils() {
    
    return new DAOUtils();
  }
  /* 
  @Bean
  public ICompanyDAO companyDAO() {
    return new CompanyDAO();
  }
  
  
  @Bean
  public ComputerDAO computerDAO() {
    return new ComputerDAO();
  }


   @Bean
  public IComputerDAO iComputerDAO() {return new ComputerDAO();}
  @Bean
  public ICompanyService iCompanyService() {return new CompanyService();}
  @Bean
  public IComputerService iComputerService() {return new ComputerService();}*/
}
