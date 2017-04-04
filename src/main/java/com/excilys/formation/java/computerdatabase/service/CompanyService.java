package com.excilys.formation.java.computerdatabase.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.computerdatabase.dao.mysql.CompanyDAO;
import com.excilys.formation.java.computerdatabase.domain.Company;

/**
 * The Class CompanyService.
 * @author Walid Kadri
 */
@Service
public class CompanyService implements ICompanyService {

  /** The company DAO. */
  @Autowired
  private CompanyDAO companyDAO;

  /** The log. */
  private final static Logger log = LoggerFactory.getLogger(CompanyService.class);

  /**
   * Instantiates a new company service.
   */
  public CompanyService() {}

  /**
   * Gets the companies.
   * @return the companies
   */
  @Override
  public List<Company> getCompanies() {
    return companyDAO.getCompanies();
  }

  /**
   * Gets the company name.
   * @param id the id
   * @return the company name
   */
  @Override
  public String getCompanyName(final int id) {
    final Company company = companyDAO.getCompanyByID(id);
    log.info(company.toString());
    return company.getName();
  }
}
