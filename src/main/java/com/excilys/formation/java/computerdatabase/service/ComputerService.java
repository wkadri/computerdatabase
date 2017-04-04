package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  @Override
  public List<Computer> getComputers() {
    ArrayList<Computer> computers = new ArrayList<>();
    try {
      computers = computerDAO.getComputers();
      return computers;
    } catch (final DAOException e) {
      log.error("Can't update the computer-Reason:");
      log.error(e.getMessage());
    }
    return computers;

  }

  /**
   * Describe computer by ID.
   * @param id the id
   * @return the computer
   */
  @Override
  public Computer describeComputerByID(final long id) {
    Computer computer = null;
    try {
      computer = (computerDAO.getById(id)).get();
      log.info(computer.toString());
    } catch (final DAOException e) {
      log.error("Computer not in the database - Reason:");
      log.error(e.getMessage());
    }
    return computer;
  }

  /**
   * Creates the computer.
   * @param computer the computer
   */
  @Override
  public void createComputer(final Computer computer) {
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
  @Override
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
  @Override
  public ArrayList<Computer> getComputersPage(final long l, final int nb) {
    ArrayList<Computer> computers = new ArrayList<>();
    try {
      computers = computerDAO.getComputersPage(l, nb);
    } catch (final DAOException e) {
      log.error("Can't get the computer page-Reason:");
      log.error(e.getMessage());
    }
    return computers;
  }

  /**
   * Gets the number instances.
   * @return the number instances
   */
  @Override
  public int getNumberInstances() {
    int size = 0;
    try {
      size = computerDAO.getNumberInstances();
    } catch (final DAOException e) {
      log.error("Can't update the computer-Reason:");
      log.error(e.getMessage());
    }
    return size;
  }

  /**
   * Filter.
   * @param filtre the filtre
   * @param offset the offset
   * @param limit the limit
   * @return the array list
   */
  @Override
  public List<Computer> filter(final String filtre, final long offset, final int limit) {
    ArrayList<Computer> computers = new ArrayList<>();
    try {
      computers = computerDAO.filter(filtre, offset, limit);
    } catch (final DAOException e) {
      log.error(e.getMessage());
    }
    return computers;
  }

  /**
   * Update computer.
   * @param computer the computer
   */
  @Override
  public void updateComputer(final Computer computer) {
    try {
      computerDAO.updateComputer(computer);
      log.info("Computer :" + computer + " modified");
    } catch (final DAOException e) {
      log.error("Can't update the computer-Reason:");
      log.error(e.getMessage());
    }
  }

  //TODO
  @Transactional
  public void deleteComputers(final int... ids) {
    for (final int id : ids) {
      deleteComputer(id);
    }

  }
}
