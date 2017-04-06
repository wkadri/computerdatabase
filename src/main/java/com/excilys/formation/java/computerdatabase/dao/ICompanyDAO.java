package com.excilys.formation.java.computerdatabase.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.formation.java.computerdatabase.domain.Company;

/**
 * Interface for the companies.
 * Allow to get the exhausted list of company.
 * @author Walid KADRI
 */
public interface ICompanyDAO extends JpaRepository<Company, Long> {

}
