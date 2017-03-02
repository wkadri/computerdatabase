package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.sql.ComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.sql.DAOException;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

/**
 * The Class ComputerService.
 * @author Walid KADRI
 */

public class ComputerService {
  /** Computer DAO. */
  private final IComputerDAO computerDAO;
  /** The log. */
  private final Logger log;

  /**
   * Instantiates a new computer service.
   */
  public ComputerService() {
    computerDAO = new ComputerDAO();
    log = LoggerFactory.getLogger(ComputerService.class);
  }

  /**
   * Gets the computers.
   * @return the computers
   */
  public ArrayList<ComputerDTO> getComputers() {
    final ArrayList<ComputerDTO> computers = computerDAO.getComputers();
    //computers.forEach(t -> log.info(t.toString()));
    return computers;
  }

  /**
   * Describe computer by ID.
   * @param id the id
   * @return the computer DTO
   */
  public ComputerDTO describeComputerByID(final long id) {
    ComputerDTO computer = null;
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
    System.out.println(computer);
    return computer;
  }

  /**
   * Creates the computer.
   * @param name the name
   * @param entries the entries
   */
  public void createComputer(final String name, final String... entries) {
    try {
      if (name.isEmpty()) {
        log.warn("Name empty");
        computerDAO.addComputer("computer", entries[0], entries[1]);
        log.info("Computer ( with default name : computer )added");
      } else {
        final ComputerDTO computer = (computerDAO.addComputer(name.trim(), entries)).get();
        log.info("Computer " + computer + " added");
      }
    } catch (final DAOException e) {
      log.error("Can't create the computer-Reason:");
      log.error(e.getMessage());
    }
  }

  /**
   * Update computer.
   * @param id the id
   * @param newValue the new value
   * @param newIntroduced the new introduced
   */
  public void updateComputer(final long id, final String newValue, final String newIntroduced) {
    try {
      computerDAO.updateComputer(id, newValue, newIntroduced);
      log.info("Computer id :" + id + " modified");
    } catch (final DAOException e) {
      log.error("Can't update the computer-Reason:");
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
    } catch (final DAOException e) {
      log.error("Can't delete the computer-Reason:");
      log.error(e.getMessage());
    }

  }
}
