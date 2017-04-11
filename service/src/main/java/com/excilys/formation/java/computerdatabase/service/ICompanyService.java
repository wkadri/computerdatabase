package com.excilys.formation.java.computerdatabase.service;

import java.util.List;

import com.excilys.formation.java.computerdatabase.core.Company;

/**
 * The Interface ICompanyService.
 */
public interface ICompanyService {

  /**
   * Gets the companies.
   * @return the companies
   */
  List<Company> getCompanies();

  /**
   * Gets the company name.
   * @param id the id
   * @return the company name
   */
 
  String getCompanyName(long id);
  void deleteCompany(Long id);

  
}
