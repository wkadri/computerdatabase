package com.excilys.formation.java.computerdatabase.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.computerdatabase.dao.ICompanyDAO;
import com.excilys.formation.java.computerdatabase.domain.Company;
import com.excilys.formation.java.computerdatabase.domain.Computer;

/**
 * The Class CompanyService.
 * @author Walid Kadri
 */
@Service
public class CompanyService implements ICompanyService {

  /** The company DAO. */
  @Autowired
  private ICompanyDAO companyDAO;

  @Autowired
  private ComputerService computerService;

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
    return companyDAO.findAll();
  }

  /**
   * Gets the company name.
   * @param id the id
   * @return the company name
   */
  @Override
  public String getCompanyName(final int id) {
    final Company company = companyDAO.findOne((long) id);
    log.info(company.toString());
    return company.getName();
  }

//TODO à refacto
  @Override
  @Transactional
  public void deleteCompany(final int id) {
    for (final Computer comp : computerService.getComputers()) {
      if (comp.getCompany() != null && comp.getCompany().getId() == id) {
        computerService.deleteComputer(comp.getId());
      }
    }
    companyDAO.delete((long) id);
  }
}
