package com.excilys.formation.java.computerdatabase.dao;

import java.util.ArrayList;

import com.excilys.formation.java.computerdatabase.dto.CompanyDTO;

/**
 * DAO interface for the companies
 * 
 * Allow to get the exhausted list of company
 * 
 * @author Walid KADRI
 */
public interface ICompanyDAO {

	public ArrayList<CompanyDTO> getCompanies();
}
