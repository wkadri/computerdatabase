package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.mysql.ComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.mysql.DAOException;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

// TODO: Auto-generated Javadoc
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
   * @param companyID the company ID
   */
  public void updateComputer(final long id, final String newValue, final String newIntroduced, String companyID) {
    try {
      computerDAO.updateComputer(id, newValue, newIntroduced, companyID);
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

  /**
   * Gets the computers page.
   * @param l the l
   * @param nb the nb
   * @return the computers page
   */
  public ArrayList<ComputerDTO> getComputersPage(long l, int nb) {
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

    return computerDAO.getNumberInstances();
  }

  /**
   * Filter.
   * @param filtre the filtre
   * @param offset the offset
   * @param limit the limit
   * @return the array list
   */
  public ArrayList<ComputerDTO> filter(String filtre, long offset, int limit) {
    return computerDAO.filter(filtre, offset, limit);
  }
}
