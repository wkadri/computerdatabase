package com.excilys.formation.java.computerdatabase.service;

import org.junit.Assert;
import org.junit.Test;

import com.excilys.formation.java.computerdatabase.dao.sql.DAOException;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

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
  
}
