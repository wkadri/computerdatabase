package com.excilys.formation.java.computerdatabase.service;

import org.junit.Test;

public class CompanyServiceTest {
  @Test
  public void getDisplayTest() {
    final CompanyService service = new CompanyService();
    service.getCompanies();
  }
}
