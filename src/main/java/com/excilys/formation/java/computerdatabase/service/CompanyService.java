package com.excilys.formation.java.computerdatabase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerdatabase.dao.ICompanyDAO;
import com.excilys.formation.java.computerdatabase.dao.sql.CompanyDAO;

public class CompanyService {

	private ICompanyDAO companyDAO;
	private Logger log = LoggerFactory.getLogger(CompanyService.class);

	public CompanyService() {
		companyDAO = new CompanyDAO();
	}

	public void getCompanies() {
		companyDAO.getCompanies().forEach(t -> log.info(t.toString()));
	}

	public static void main(String[] args) {
		(new CompanyService()).getCompanies();
	}
}
