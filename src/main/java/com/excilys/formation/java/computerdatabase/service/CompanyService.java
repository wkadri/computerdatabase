package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerdatabase.dao.ICompanyDAO;
import com.excilys.formation.java.computerdatabase.dao.sql.CompanyDAO;
import com.excilys.formation.java.computerdatabase.dto.CompanyDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyService.
 * @author Walid Kadri
 */
public class CompanyService {

  /** The company DAO. */
  private final ICompanyDAO companyDAO;

  /** The log. */
  private final Logger log = LoggerFactory.getLogger(CompanyService.class);

  /**
   * Instantiates a new company service.
   */
  public CompanyService() {
    companyDAO = new CompanyDAO();
  }

  /**
   * Gets the companies.
   */
  public ArrayList<CompanyDTO> getCompanies() {
   // companyDAO.getCompanies().forEach(t -> log.info(t.toString()));
    return companyDAO.getCompanies();
  }

  /**
   * The main method.
   * @param args the arguments
   */
  public static void main(final String[] args) {
    (new CompanyService()).getCompanies();
  }
}
