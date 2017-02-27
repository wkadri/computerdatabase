package com.excilys.formation.java.computerdatabase.service;

import org.junit.Test;

/**
 * The Class CompanyServiceTest.
 */
public class CompanyServiceTest {
  /**
   * Gets the display test.
   */
  @Test public void getDisplayTest() {
    final CompanyService service = new CompanyService();
    service.getCompanies();
  }
}
