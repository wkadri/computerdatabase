package com.excilys.formation.java.computerdatabase.dao;

import java.util.ArrayList;

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
  ArrayList<Company> getCompanies();



  Company getCompanyByID(int id);
}
