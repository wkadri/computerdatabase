package com.excilys.formation.java.computerdatabase.dao;

import java.util.ArrayList;
import java.util.Optional;

import com.excilys.formation.java.computerdatabase.dao.mysql.DAOException;
import com.excilys.formation.java.computerdatabase.domain.Computer;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

// TODO: Auto-generated Javadoc
/**
 * DAO interface for computers.
 * @author Walid KADRI
 */
public interface IComputerDAO {

  /**
   * Gets the computers.
   * @return the computers
   */
  ArrayList<Computer> getComputers();

  /**
   * Gets the by id.
   * @param id the id
   * @return the by id
   * @throws DAOException the DAO exception
   */
  Optional<Computer> getById(long id) throws DAOException;

  /**
   * Delete computer.
   * @param id the id
   * @throws DAOException the DAO exception
   */
  void deleteComputer(long id) throws DAOException;

  /**
   * Adds the computer.
   * @param name the name
   * @param introduced the introduced
   * @return the optional
   * @throws DAOException the DAO exception
   */
  //Optional<Computer> addComputer(String name, String... introduced) throws DAOException;

  /**
   * Gets the computers page.
   * @param offset the offset
   * @param limit the limit
   * @return the computers page
   * @throws DAOException the DAO exception
   */
  ArrayList<Computer> getComputersPage(long offset, int limit) throws DAOException;

  /**
   * Gets the number instances.
   * @return the number instances
   */
  int getNumberInstances();

  /**
   * Update computer.
   * @param id the id
   * @param newName the new name
   * @param newIntroduced the new introduced
   * @param companyId the company id
   * @throws DAOException the DAO exception
   */
  void updateComputer(long id, String newName, String newIntroduced, String companyId) throws DAOException;

  /**
   * Filter.
   * @param string the string
   * @param offset the offset
   * @param limit the limit
   * @return the array list
   */
  ArrayList<Computer> filter(String string, final long offset, final int limit);

  Optional<Computer> addComputer(Computer computer) throws DAOException;
}
