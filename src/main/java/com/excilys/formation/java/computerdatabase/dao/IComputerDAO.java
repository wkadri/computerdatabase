package com.excilys.formation.java.computerdatabase.dao;

import java.util.ArrayList;
import java.util.Optional;

import com.excilys.formation.java.computerdatabase.dao.mysql.DAOException;
import com.excilys.formation.java.computerdatabase.domain.Computer;

// TODO: Auto-generated Javadoc
/**
 * DAO interface for computers.
 * @author Walid KADRI
 */
public interface IComputerDAO {

  /**
   * Gets the computers.
   * @return the computers
   * @throws DAOException 
   */
  ArrayList<Computer> getComputers() throws DAOException;

  /**
   * Gets the by id.
   * @param id the id
   * @return the by id
   * @throws DAOException the DAO exception
   */
  Optional<Computer> getById(long id) throws DAOException;

  /**
   * Adds the computer.
   * @param computer the computer
   * @return the optional
   * @throws DAOException the DAO exception
   */
  Optional<Computer> addComputer(Computer computer) throws DAOException;

  /**
   * Delete computer.
   * @param id the id
   * @throws DAOException the DAO exception
   */
  void deleteComputer(long id) throws DAOException;

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
   * @throws DAOException 
   */
  int getNumberInstances() throws DAOException;

  /**
   * Filter.
   * @param string the string
   * @param offset the offset
   * @param limit the limit
   * @return the array list
   * @throws DAOException 
   */
  ArrayList<Computer> filter(String string, final long offset, final int limit) throws DAOException;

  void updateComputer(Computer computer) throws DAOException;

}
