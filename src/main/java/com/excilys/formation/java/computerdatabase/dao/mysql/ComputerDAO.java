package com.excilys.formation.java.computerdatabase.dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.computerdatabase.dao.DAOException;
import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.domain.Company;
import com.excilys.formation.java.computerdatabase.domain.Computer;

/**
 * Access to the table Computers.
 * @author Walid KADRI
 */
@Repository
public class ComputerDAO implements IComputerDAO {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  /** The log. */
  private Logger log = LoggerFactory.getLogger(CompanyDAO.class);

  public ComputerDAO() {

    jdbcTemplate = new JdbcTemplate();

  }

  /**
   * Return the complete list of computers.
   * @return the computers
   * @throws DAOException
   */
  @Override
  public ArrayList<Computer> getComputers() throws DAOException {
    return (ArrayList<Computer>) this.jdbcTemplate.query("select * from computer  LEFT JOIN company on computer.company_id=company.id", new ComputerMapper());
  }

  /**
   * return the computer with the specified id.
   * @param id of the computer
   * @return the by id
   * @throws DAOException the DAO exception
   */
  @Override
  public Optional<Computer> getById(final long id) throws DAOException {
    Computer comp = (Computer) this.jdbcTemplate.queryForObject("SELECT * FROM computer  LEFT JOIN company on computer.company_id=company.id WHERE computer.id= ? ;", new Object[] { id }, new ComputerMapper());
    if (comp == null) {
      throw new DAOException("Error: Wrong ID");
    } else {
      return Optional.of(comp);
    }
  }

  /**
   * Add a computer to the table.
   * The first param after the name must be the introduced date.
   * The second param after the name should be the company name.
   * @param computer the computer
   * @return the optional
   * @throws DAOException if wrong id
   */
  @Override
  public Optional<Computer> addComputer(Computer computer) throws DAOException {
//TODO wrong id on log
    int status = jdbcTemplate.update("INSERT INTO computer (name,introduced,company_id)  values  (  ?,?,? );", computer.getName(), computer.getIntroduced(), computer.getCompany().getId());
    log.info("computer added" + computer);
    //TODO status
    if (status == 200) {
      log.error("error on add computer");
      throw new DAOException("Error " + status);
    }
    return Optional.of(computer);
  }

  /**
   * Delete a computer by ID.
   * @param id of the computer
   * @throws DAOException the DAO exception
   */
  @Override
  public void deleteComputer(final long id) throws DAOException {
    getById(id); // check if the id is not wrong

    int status = jdbcTemplate.update("DELETE FROM computer WHERE id = ?;", false);
    //TODO status
    if (status == 200) {
      throw new DAOException("Error " + status);
    }

  }

  /**
   * Delete a computer by ID.
   * @param offset the offset
   * @param limit the limit
   * @return the computers page
   * @throws DAOException the DAO exception
   */
  @Override
  public ArrayList<Computer> getComputersPage(final long offset, final int limit) throws DAOException {

    return (ArrayList<Computer>) this.jdbcTemplate.query("SELECT * FROM computer  LEFT JOIN company on computer.company_id=company.id LIMIT ? OFFSET ? ;", new Object[] { limit, offset }, new ComputerMapper());

    /*
    } catch (final SQLException e) {
      throw new DAOException("Error :" + e.getMessage());
    } finally {
      daoUtils.close(rs, stmt);
    }*/

  }

  /**
   * Gets the number instances.
   * @return the number instances
   * @throws DAOException
   * @see com.excilys.formation.java.computerdatabase.dao.IComputerDAO#getNumberInstances()
   */
  @Override
  public int getNumberInstances() throws DAOException {

    return this.jdbcTemplate.queryForObject("Select count(*) from computer;", Integer.class);
    /*
    } catch (final SQLException e) {
     throw new DAOException("Error :" + e.getMessage());
    } finally {
     daoUtils.close(rs, stmt);
    }*/

  }

  /**
   * Filter.
   * @param string the string
   * @param offset the offset
   * @param limit the limit
   * @return the array list
   * @throws DAOException
   */
  public ArrayList<Computer> filter(String string, final long offset, final int limit) throws DAOException {

    return (ArrayList<Computer>) this.jdbcTemplate.query("SELECT * FROM computer  LEFT JOIN company on computer.company_id=company.id  WHERE computer.name LIKE ?  ;", new ComputerMapper());
    /*
    } catch (final SQLException e) {
     throw new DAOException("Error :" + e.getMessage());
    } finally {
     daoUtils.close(rs, stmt);
    }*/
  }

  @Override
  public void updateComputer(Computer computer) throws DAOException {
    if (getById(computer.getId()).isPresent()) {
      this.jdbcTemplate.update("UPDATE computer SET name = ?, introduced= ? , company_id=? WHERE id = ? ", computer.getName(), computer.getIntroduced().toString(),  computer.getCompany().getId(), computer.getId());
      //TODO status
      /*int status =
       * if (status == 200) {
      throw new DAOException("error" + status);
      }*/
    }
  }

  final class ComputerMapper implements RowMapper<Computer> {

    public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
      final Computer computer = new Computer.ComputerBuilder(resultSet.getString("name")).build();
      computer.setId(resultSet.getLong("id"));
      if (resultSet.getString("introduced") != null) {
        if (!resultSet.getString("introduced").contains("0000-00-00")) {
          computer.setIntroduced(LocalDate.parse(((resultSet.getString("introduced").substring(0, 10)))));
        }
      }
      if (resultSet.getString("company.name") != null) {
        computer.setCompany(new Company(resultSet.getLong("company.id"), resultSet.getString("company.name")));
      }
      return computer;

    }
  }
}
