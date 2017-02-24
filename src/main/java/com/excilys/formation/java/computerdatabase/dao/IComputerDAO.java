package com.excilys.formation.java.computerdatabase.dao;

import java.util.ArrayList;
import java.util.Optional;

import com.excilys.formation.java.computerdatabase.dao.sql.DAOException;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

// TODO: Auto-generated Javadoc
/**
 * DAO interface for computers.
 * 
 * @author Walid KADRI
 */
public interface IComputerDAO {
  
  /**
   * Gets the computers.
   *
   * @return the computers
   */
  public ArrayList<ComputerDTO> getComputers();
  
  /**
   * Gets the by id.
   *
   * @param id the id
   * @return the by id
   * @throws DAOException the DAO exception
   */
  public Optional<ComputerDTO> getById(long id) throws DAOException;
  
  /**
   * Update computer.
   *
   * @param id the id
   * @param newName the new name
   * @param newIntroduced the new introduced
   * @throws DAOException the DAO exception
   */
  public void updateComputer(int id, String newName, String newIntroduced) throws DAOException;
  
  /**
   * Delete computer.
   *
   * @param id the id
   * @throws DAOException the DAO exception
   */
  public void deleteComputer(long id) throws DAOException;
  
  /**
   * Adds the computer.
   *
   * @param name the name
   * @param introduced the introduced
   * @return the optional
   * @throws DAOException the DAO exception
   */
  public Optional<ComputerDTO> addComputer(String name, String... introduced) throws DAOException;
  
}
