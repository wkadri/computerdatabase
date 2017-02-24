package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.sql.ComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.sql.DAOException;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

/**
 * @author Walid KADRI
 */
public class ComputerService {
  /** Computer DAO */
  private final IComputerDAO computerDAO;
  private final Logger log;
  
  public ComputerService() {
    computerDAO = new ComputerDAO();
    log = LoggerFactory.getLogger(ComputerService.class);
  }
  
  public ArrayList<ComputerDTO> getComputers() {
    final ArrayList<ComputerDTO> computers = computerDAO.getComputers();
    //computers.forEach(t -> log.info(t.toString()));
    return computers;
  }
  
  public ComputerDTO describeComputerByID(final int id) {
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
  
  public void createComputer(final String name, final String... entries) {
    try {
      if (name.isEmpty()) {
        log.warn("Name empty");
        computerDAO.addComputer("computer", entries[0], entries[1]);
        log.info("Computer ( with default name : computer )added");
      } else {
        System.out.println(name);
        final ComputerDTO computer = (computerDAO.addComputer(name.trim(), entries[0], entries[1])).get();
        log.info("Computer " + computer + " added");
      }
    } catch (final DAOException e) {
      log.error("Can't create the computer-Reason:");
      log.error(e.getMessage());
    }
  }
  
  public void updateComputer(final int id, final String newValue, final String newIntroduced) {
    try {
      computerDAO.updateComputer(id, newValue, newIntroduced);
      log.info("Computer id :" + id + " modified");
    } catch (final DAOException e) {
      log.error("Can't update the computer-Reason:");
      log.error(e.getMessage());
    }
  }
  
  public void deleteComputer(final long l) {
    try {
      computerDAO.deleteComputer(l);
    } catch (final DAOException e) {
      log.error("Can't delete the computer-Reason:");
      log.error(e.getMessage());
    }
    
  }
}
