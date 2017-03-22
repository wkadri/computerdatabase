package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;

import com.excilys.formation.java.computerdatabase.domain.Company;

/**
 * The Interface ICompanyService.
 */
public interface ICompanyService {

  /**
   * Gets the companies.
   * @return the companies
   */
  ArrayList<Company> getCompanies();

  /**
   * Gets the company name.
   * @param id the id
   * @return the company name
   */
  String getCompanyName(int id);
}
