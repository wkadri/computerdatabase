package com.excilys.formation.java.computerdatabase.service;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.formation.java.computerdatabase.dao.mysql.ComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.mysql.DAOException;
import com.excilys.formation.java.computerdatabase.domain.Company;
import com.excilys.formation.java.computerdatabase.domain.Computer;

/**
 * The Class ComputerServiceTest.
 */
public class ComputerServiceTest {
  /** The service. */
  private final ComputerService service = new ComputerService();
  ComputerDAO computerDAO = Mockito.mock(ComputerDAO.class);
  private Computer newComputer;

  /**
   * Instantiate the mock.
   * @throws DAOException exception
   */
  @Before
  public void mockInit() throws DAOException {
    newComputer = new Computer.ComputerBuilder("MAC DO").introduced(LocalDate.parse("1991-10-10")).company(new Company(12)).build();
    // Mockito.when(computerDAO.addComputer("ordi", "", "")).thenReturn(Optional.of(new Computer.ComputerBuilder("ordi").build()));
  }

  /**
   * Creates the test.
   * @throws DAOException exception
   */
  @Test
  public void createTest() throws DAOException {
    final int initSize = service.getComputers().size();
    service.createComputer(newComputer);
    Assert.assertEquals(initSize + 1, service.getComputers().size());
  }

  /**
   * Delete test.
   * @throws NumberFormatException the number format exception
   * @throws DAOException the DAO exception
   */
  @Test
  public void deleteTest() throws NumberFormatException, DAOException {
    final int initSize = service.getComputers().size();
    service.createComputer(newComputer);
    service.deleteComputer(service.getComputers().get(service.getComputers().size() - 1).getId());
    Assert.assertEquals(initSize, service.getComputers().size());
  }

  /**
   * Update test. *
   * @throws DAOException the DAO exception
   */
  @Test
  public void updateTest() throws DAOException {
    final int id = 88; // or Integer.valueOf((int) (Math.random()*500)); but dirty for the db
    final Computer before = service.describeComputerByID(id);
    //service.updateComputer(id, "MacInTouch" + id, "1991-10-10", "12");
    service.describeComputerByID(id);
    Assert.assertNotEquals(before, service.describeComputerByID(id));
  }

  /**
   * Update wrong ID test.
   */
  @Test
  public void updateWrongIDTest() {
    service.deleteComputer(13);
    // service.updateComputer(15, "oui", "oui", null);
    service.describeComputerByID(15);
  }

  /**
   * Gets the ID test.
   */
  @Test
  public void getIDTest() {
    //TODO service.updateComputer(55, "oui", "1991-10-11", null);
    // System.out.println("DATE" + service.describeComputerByID(55).getIntroduced());
    //Assert.assertEquals(LocalDate.of(1991, 10, 11), service.describeComputerByID(55).getIntroduced());
    // Assert.assertEquals("oui", service.describeComputerByID(55).getName());
  }

  /**
   * Wrong type date update test.
   */
  @Test
  public void wrongTypeDateUpdateTest() {
    try {
      //  service.updateComputer(15, "oui", "oui", null);
    } catch (final Exception e) {
      Assert.assertTrue(e.getMessage().contains("Incorrect datetime value"));
    }
  }

//TODO
  /**
   * Wrong type date create test.
   */
  @Test
  public void wrongTypeDateCreateTest() {
    //service.createComputer("oui", "oui");
  }
}
