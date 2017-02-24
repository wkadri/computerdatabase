package com.excilys.formation.java.computerdatabase.service;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.excilys.formation.java.computerdatabase.dao.sql.DAOException;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

/**
 * The Class ComputerServiceTest.
 */
public class ComputerServiceTest {
  
  /** The service. */
  ComputerService service = new ComputerService();
  
  /**
   * Creates the test.
   * 
   * @throws DAOException exception
   */
  @Test
  public void createTest() throws DAOException {
    final int initSize = service.getComputers().size();
    service.createComputer("MacDo", "1991-10-10", "12");
    Assert.assertEquals(initSize + 1, service.getComputers().size());
  }
  
  /**
   * Delete test.
   *
   * @throws NumberFormatException the number format exception
   * @throws DAOException the DAO exception
   */
  
  @Test
  public void deleteTest() throws NumberFormatException, DAOException {
    final int initSize = service.getComputers().size();
    service.createComputer("MacDo", "1991-10-10", "12");
    service.deleteComputer(service.getComputers().get(service.getComputers().size() - 1).getId());
    Assert.assertEquals(initSize, service.getComputers().size());
  }
  
  /**
   * Update test.
   *
   * @throws DAOException the DAO exception
   */
  @Test
  public void updateTest() throws DAOException {
    final ComputerDTO before = service.describeComputerByID(96);
    service.updateComputer(96, "MacInTouch", "1991-10-10");
    service.describeComputerByID(96);
    Assert.assertNotEquals(before, service.describeComputerByID(96));
  }
  
  @Test
  public void updateWrongIDTest() {
    service.deleteComputer(13);
    service.updateComputer(15, "oui", "oui");
    service.describeComputerByID(15);
  }
  
  @Test
  public void getIDTest() {
    service.updateComputer(55, "oui", "1991-10-18");
    System.out.println("DATE" + service.describeComputerByID(55).getIntroduced());
    Assert.assertEquals(LocalDate.of(1991, 10, 18), service.describeComputerByID(55).getIntroduced());
    Assert.assertEquals("oui", service.describeComputerByID(55).getName());
  }
  
  @Test(expected = MysqlDataTruncation.class)
  public void wrongType() {
    service.updateComputer(15, "oui", "oui");
    
  }
}
