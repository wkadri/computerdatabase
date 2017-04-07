package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.domain.Computer;

/**
 * The Class ComputerService.
 * @author Walid KADRI
 */
@Service
public class ComputerService implements IComputerService {
  /** Computer DAO. */
  @Resource
  private IComputerDAO computerDAO;
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
    List<Computer> computers = new ArrayList<>();
    computers = (List<Computer>) computerDAO.findAll();
    return computers;
  }

  /**
   * Describe computer by ID.
   * @param id the id
   * @return the computer
   */
  @Override
  public Computer describeComputerByID(final long id) {

    return computerDAO.findOne(id);
  }

  /**
   * Creates the computer.
   * @param computer the computer
   * @return
   */
  @Override
  public Computer createComputer(final Computer computer) {
    log.info(computer + " added");
    return computerDAO.save(computer);

  }

  /**
   * Delete computer.
   * @param l the l
   */
  @Override
  public void deleteComputer(final long l) {
    final Computer comp = computerDAO.findOne(l);
    computerDAO.delete(comp);
  }

  /**
   * Gets the computers page.
   * @param l the l
   * @param nb the nb
   * @return the computers page
   */

  @Override
  public Page<Computer> getComputersPage(final long l, final int nb) {
    final PageRequest page = new PageRequest((int) l, nb);
    final Page<Computer> computers = computerDAO.findAll(page);

    return computers;
  }

  /**
   * Gets the number instances.
   * @return the number instances
   */
  @Override
  public int getNumberInstances() {
    return (int) computerDAO.count();
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
    computers = computerDAO.filter(filtre, offset, limit);
    return computers;
  }

  /**
   * Update computer.
   * @param computer the computer
   */
  @Override
  public Computer updateComputer(final Computer computer) {
    computerDAO.save(computer);
    return computer;
  }

  //TODO
  //@Transactional
  public void deleteComputers(final int... ids) {
    for (final int id : ids) {
      deleteComputer(id);
    }

  }

}
