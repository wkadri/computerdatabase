package com.excilys.formation.java.computerdatabase.service;

import org.junit.Test;

import com.excilys.formation.java.computerdatabase.dao.sql.CompanyDAO;

public class CompanyDAOTest {
  @Test
  public void daoTest() {
    final CompanyDAO companyDAO = new CompanyDAO();
    companyDAO.getCompanies();
  }
}
