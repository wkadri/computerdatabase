package com.excilys.formation.java.computerdatabase.dao;

import java.util.List;

import com.excilys.formation.java.computerdatabase.domain.Company;

/**
 * Interface for the companies.
 * Allow to get the exhausted list of company.
 * @author Walid KADRI
 */
public interface ICompanyDAO {

  /**
   * Method to return the list of companies.
   * @return the companies
   */
  List<Company> getCompanies();

  /**
   * Gets the company by ID.
   * @param id the id
   * @return the company by ID
   */
  Company getCompanyByID(int id);
}
