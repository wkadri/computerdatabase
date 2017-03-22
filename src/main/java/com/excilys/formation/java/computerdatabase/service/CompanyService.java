package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.excilys.formation.java.computerdatabase.AppContext;
import com.excilys.formation.java.computerdatabase.dao.ICompanyDAO;
import com.excilys.formation.java.computerdatabase.dao.mysql.CompanyDAO;
import com.excilys.formation.java.computerdatabase.dao.mysql.DAOUtils;
import com.excilys.formation.java.computerdatabase.domain.Company;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyService.
 * @author Walid Kadri
 */
public class CompanyService implements ICompanyService {

  /** The company DAO. */
  @Autowired
  private final ICompanyDAO companyDAO;

  /** The log. */
  private final Logger log = LoggerFactory.getLogger(CompanyService.class);

  /**
   * Instantiates a new company service.
   */
  public CompanyService() {
    companyDAO = new CompanyDAO();
    //ApplicationContext context = new AnnotationConfigApplicationContext(AppContextDAO.class);
    //companyDAO = (ICompanyDAO) context.getBean(ICompanyDAO.class);
  }

  /**
   * Gets the companies.
   * @return the companies
   */
  public ArrayList<Company> getCompanies() {
    return companyDAO.getCompanies();
  }

  /**
   * Gets the company name.
   * @param id the id
   * @return the company name
   */
  public String getCompanyName(int id) {
    Company company = companyDAO.getCompanyByID(id);
    log.info(company.toString());
    return company.getName();
  }
}
