package com.excilys.formation.java.computerdatabase.dao.sql;

import org.junit.Test;

/**
 * The Class CompanyDAOTest.
 */
public class CompanyDAOTest {
  /**
   * Dao test.
   */
  @Test public void daoTest() {
    final CompanyDAO companyDAO = new CompanyDAO();
    companyDAO.getCompanies();
  }
}
