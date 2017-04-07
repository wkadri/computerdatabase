package com.excilys.formation.java.computerdatabase.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.excilys.formation.java.computerdatabase.domain.Computer;

/**
 * DAO interface for computers.
 * @author Walid KADRI
 */
public interface IComputerDAO extends PagingAndSortingRepository<Computer, Long> {

  @Query(value = "SELECT * FROM computer LEFT JOIN company on computer.company_id=company.id  WHERE computer.name LIKE %?1%  LIMIT ?3 OFFSET ?2  ", nativeQuery = true)
  public ArrayList<Computer> filter(final String string, long offset, int limit);

}
