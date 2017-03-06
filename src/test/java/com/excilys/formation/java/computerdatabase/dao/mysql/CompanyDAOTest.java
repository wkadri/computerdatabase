package com.excilys.formation.java.computerdatabase.dao.mysql;

import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDAOTest.
 */
public class CompanyDAOTest {

  /**
   * Before.
   */
  @Before public void before() {

  }

  /**
   * Dao test.
   */
  @Test public void daoTest() {
    final CompanyDAO companyDAO = new CompanyDAO();
    companyDAO.getCompanies();
  }
}
