package com.excilys.formation.java.computerdatabase.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.formation.java.computerdatabase.dao.sql.ComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.sql.DAOException;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

/**
 * The Class ComputerServiceTest.
 */
public class ComputerServiceTest {
  /** The service. */
  private final ComputerService service = new ComputerService();
  ComputerDAO computerDAO = Mockito.mock(ComputerDAO.class);

  /**
   * Instantiate the mock.
   * @throws DAOException exception
   */
  @Before public void mockInit() throws DAOException {
    Mockito.when(computerDAO.addComputer("ordi", "", "")).thenReturn(Optional.of(new ComputerDTO()));
  }

  /**
   * Creates the test.
   * @throws DAOException exception
   */
  @Test public void createTest() throws DAOException {
    final int initSize = service.getComputers().size();
    service.createComputer("MacDo", "1991-10-10", "12");
    Assert.assertEquals(initSize + 1, service.getComputers().size());
  }

  /**
   * Delete test.
   * @throws NumberFormatException the number format exception
   * @throws DAOException the DAO exception
   */
  @Test public void deleteTest() throws NumberFormatException, DAOException {
    final int initSize = service.getComputers().size();
    service.createComputer("MacDo", "1991-10-10", "12");
    service.deleteComputer(service.getComputers().get(service.getComputers().size() - 1).getId());
    Assert.assertEquals(initSize, service.getComputers().size());
  }

  /**
   * Update test. *
   * @throws DAOException the DAO exception
   */
  @Test public void updateTest() throws DAOException {
    final int id = 88; // or Integer.valueOf((int) (Math.random()*500)); but dirty for the db
    final ComputerDTO before = service.describeComputerByID(id);
    service.updateComputer(id, "MacInTouch" + id, "1991-10-10");
    service.describeComputerByID(id);
    Assert.assertNotEquals(before, service.describeComputerByID(id));
  }

  /**
   * Update wrong ID test.
   */
  @Test public void updateWrongIDTest() {
    service.deleteComputer(13);
    service.updateComputer(15, "oui", "oui");
    service.describeComputerByID(15);
  }

  /**
   * Gets the ID test.
   */
  @Test public void getIDTest() {
    service.updateComputer(55, "oui", "1991-10-11");
    System.out.println("DATE" + service.describeComputerByID(55).getIntroduced());
    Assert.assertEquals(LocalDate.of(1991, 10, 11), service.describeComputerByID(55).getIntroduced());
    Assert.assertEquals("oui", service.describeComputerByID(55).getName());
  }

  /**
   * Wrong type date update test.
   */
  @Test public void wrongTypeDateUpdateTest() {
    try {
      service.updateComputer(15, "oui", "oui");
    } catch (final Exception e) {
      Assert.assertTrue(e instanceof MysqlDataTruncation);
      Assert.assertTrue(e.getMessage().contains("Incorrect datetime value"));
    }
  }

  /**
   * Wrong type date create test.
   */
  @Test public void wrongTypeDateCreateTest() {
    service.createComputer("oui", "oui");
  }
}
