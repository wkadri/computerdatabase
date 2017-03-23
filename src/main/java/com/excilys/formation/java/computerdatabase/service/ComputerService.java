package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.computerdatabase.dao.DAOException;
import com.excilys.formation.java.computerdatabase.dao.mysql.ComputerDAO;
import com.excilys.formation.java.computerdatabase.domain.Computer;

/**
 * The Class ComputerService.
 * @author Walid KADRI
 */
@Service
public class ComputerService implements IComputerService {
  /** Computer DAO. */
  @Autowired
  private ComputerDAO computerDAO;
  /** The log. */
  private final static Logger log = LoggerFactory.getLogger(ComputerService.class);

  public ComputerService() {

  }

  /**
   * Gets the computers.
   * @return the computers
   */
  public List<Computer> getComputers() {
    try {
      final ArrayList<Computer> computers = (ArrayList<Computer>) computerDAO.getComputers();
      return computers;
    } catch (final DAOException e) {
      log.error("Can't update the computer-Reason:");
      log.error(e.getMessage());
    }
    return null;

  }

  /**
   * Describe computer by ID.
   * @param id the id
   * @return the computer DTO
   */
  public Computer describeComputerByID(final long id) {
    Computer computer = null;
    try {
      if (computerDAO.getById(id).isPresent()) {
        computer = (computerDAO.getById(id)).get();
        log.info(computer.toString());
      } else {
        log.error("Computer not in the database - Reason:");
      }
    } catch (final DAOException e) {

      e.printStackTrace();
    }
    return computer;
  }

  /**
   * Creates the computer.
   * @param computer the computer
   */
  public void createComputer(Computer computer) {
    try {
      computerDAO.addComputer(computer);
      log.info("Computer " + computer + " added");
    } catch (final DAOException e) {
      log.error("Can't create the computer-Reason:");
      log.error(e.getMessage());
    }
  }

  /**
   * Delete computer.
   * @param l the l
   */
  public void deleteComputer(final long l) {
    try {
      computerDAO.deleteComputer(l);
      log.info("Computer id " + l + " deleted");
    } catch (final DAOException e) {
      log.error("Can't delete the computer-Reason:");
      log.error(e.getMessage());
    }

  }

  /**
   * Gets the computers page.
   * @param l the l
   * @param nb the nb
   * @return the computers page
   */
  public List<Computer> getComputersPage(long l, int nb) {
    try {
      return computerDAO.getComputersPage(l, nb);
    } catch (DAOException e) {
      log.error("Can't get the computer page-Reason:");
      log.error(e.getMessage());
    }
    return null;
  }

  /**
   * Gets the number instances.
   * @return the number instances
   */
  public int getNumberInstances() {
    try {
      return computerDAO.getNumberInstances();
    } catch (final DAOException e) {
      log.error("Can't update the computer-Reason:");
      log.error(e.getMessage());
    }
    return 0;
  }

  /**
   * Filter.
   * @param filtre the filtre
   * @param offset the offset
   * @param limit the limit
   * @return the array list
   */
  public List<Computer> filter(String filtre, long offset, int limit) {
    try {
      return computerDAO.filter(filtre, offset, limit);
    } catch (final DAOException e) {
      log.error(e.getMessage());
    }
    return null;
  }

  /**
   * Update computer.
   * @param computer the computer
   */
  public void updateComputer(Computer computer) {
    try {
      computerDAO.updateComputer(computer);
      log.info("Computer :" + computer + " modified");
    } catch (final DAOException e) {
      log.error("Can't update the computer-Reason:");
      log.error(e.getMessage());
    }
  }

  //@Transactional
  public void deleteComputers(int... ids) {
    for (int id : ids) {
      deleteComputer(id);
    }

  }
}
