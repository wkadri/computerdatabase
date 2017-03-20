package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;

import com.excilys.formation.java.computerdatabase.dao.ICompanyDAO;
import com.excilys.formation.java.computerdatabase.dao.mysql.CompanyDAO;
import com.excilys.formation.java.computerdatabase.domain.Company;

/**
 * The Class CompanyService.
 * @author Walid Kadri
 */
public class CompanyService {

  /** The company DAO. */
  private final ICompanyDAO companyDAO;

  ///** The log. */
  //private final Logger log = LoggerFactory.getLogger(CompanyService.class);

  /**
   * Instantiates a new company service.
   */
  public CompanyService() {
    companyDAO = new CompanyDAO();
  }

  /**
   * Gets the companies.
   * @return the companies
   */
  public ArrayList<Company> getCompanies() {
    //companyDAO.getCompanies().forEach(t -> log.info(t.toString()));
    return companyDAO.getCompanies();
  }

  public String getCompanyName(int id) {
    Company company = companyDAO.getCompanyByID(id);
    return company.getName();
  }
}
