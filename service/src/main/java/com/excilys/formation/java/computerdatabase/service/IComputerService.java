package com.excilys.formation.java.computerdatabase.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.excilys.formation.java.computerdatabase.core.Computer;

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
   * @return
   */
  Computer createComputer(Computer computer);

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
  Page<Computer> getComputersPage(long l, int nb);

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

  Computer updateComputer(Computer computer);

}
