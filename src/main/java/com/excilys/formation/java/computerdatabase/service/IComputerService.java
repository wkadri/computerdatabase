package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.computerdatabase.domain.Computer;

/**
 * The Interface IServiceComputer.
 */
public interface IComputerService {

  /**
   * Gets the computers.
   * @return the computers
   */
  List<Computer> getComputers();

  /**
   * Describe computer by ID.
   * @param id the id
   * @return the computer DTO
   */
  Computer describeComputerByID(final long id);

  /**
   * Creates the computer.
   * @param computer the computer
   */
  void createComputer(Computer computer);

  /**
   * Delete computer.
   * @param l the l
   */
  void deleteComputer(final long l);

  /**
   * Gets the computers page.
   * @param l the l
   * @param nb the nb
   * @return the computers page
   */
  List<Computer> getComputersPage(long l, int nb);

  /**
   * Gets the number instances.
   * @return the number instances
   */
  int getNumberInstances();

  /**
   * Filter.
   * @param filtre the filtre
   * @param offset the offset
   * @param limit the limit
   * @return the array list
   */
  List<Computer> filter(String filtre, long offset, int limit);

  void updateComputer(Computer computer);

}
