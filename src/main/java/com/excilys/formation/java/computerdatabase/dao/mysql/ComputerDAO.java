package com.excilys.formation.java.computerdatabase.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.computerdatabase.dao.DAOException;
import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.domain.Computer;

/**
 * Access to the table Computers.
 * @author Walid KADRI
 */
@Repository
public class ComputerDAO implements IComputerDAO {

  /** Max number of instances. */
  @Autowired
  DAOUtils daoUtils;

  /** The log. */
  private Logger log = LoggerFactory.getLogger(CompanyDAO.class);

  public ComputerDAO() {

  }

  /**
   * Return the complete list of computers.
   * @return the computers
   * @throws DAOException
   */
  @Override
  public ArrayList<Computer> getComputers() throws DAOException {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    final ArrayList<Computer> computers = new ArrayList<>();
    try {
      conn = daoUtils.getConnection();
      stmt = daoUtils.initRequest(conn, "select * from computer  LEFT JOIN company on computer.company_id=company.id", false);
      rs = stmt.executeQuery();
      rs.next();
      while (rs.next()) {
        computers.add(MapperDAO.mapComputer(rs));
      }
    } catch (final SQLException e) {
      throw new DAOException("Error :" + e.getMessage());
    } finally {
      daoUtils.close(rs, stmt);
    }

    return computers;
  }

  /**
   * Rollback the connection.
   * @param conn the conn
   * @throws DAOException
   */
  private void rollbackConnection(Connection conn) throws DAOException {
    try {
      conn.rollback();
    } catch (SQLException e1) {
      throw new DAOException("Error :" + e1.getMessage());
    }
  }

  /**
   * return the computer with the specified id.
   * @param id of the computer
   * @return the by id
   * @throws DAOException the DAO exception
   */
  @Override
  public Optional<Computer> getById(final long id) throws DAOException {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Computer computer = null;
    try {
      conn = daoUtils.getConnection();
      stmt = daoUtils.initRequest(conn, "SELECT * FROM computer  LEFT JOIN company on computer.company_id=company.id WHERE computer.id= ? ;", false);
      stmt.setLong(1, id);
      rs = stmt.executeQuery();
      rs.beforeFirst();
      if (rs.next()) {
        computer = MapperDAO.mapComputer(rs);
        computer.toString();
      } else {
        log.error("Error: Wrong ID");
        throw new DAOException("Error: Wrong ID");
      }

    } catch (final SQLException e) {
      throw new DAOException("Error :" + e.getMessage());
    } finally {
      daoUtils.close(rs, stmt);
    }
    return Optional.of(computer);
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

    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      conn = daoUtils.getConnection();
      stmt = daoUtils.initRequest(conn, "INSERT INTO computer (name,introduced,company_id)  values  (  ?,?,? );", false);
      stmt.setString(1, computer.getName());
      if (computer.getIntroduced() != null) {
        stmt.setString(2, computer.getIntroduced().toString());
      } else {
        stmt.setString(2, null);
      }
      if (computer.getCompany() != null) {

        stmt.setLong(3, computer.getCompany().getId());
      } else {
        stmt.setString(3, null);
      }
      log.info("computer added" + computer);
      final int status = stmt.executeUpdate();
      if (status != 0) {
        //final long lastid = (getComputers().get(getComputers().size() - 1)).getId();
        // computer.setId(lastid);
        computer.setName(computer.getName());
      }
      conn.commit();
      daoUtils.close(stmt, conn);
    } catch (final SQLException e) {
      rollbackConnection(conn);
      log.error(e.getMessage());
      throw new DAOException("Error :" + e.getMessage());
    } finally {
      daoUtils.close(stmt);
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
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      conn = daoUtils.getConnection();
      stmt = daoUtils.initRequest(conn, "DELETE FROM computer WHERE id = ?;", false);
      stmt.setLong(1, id);
      stmt.executeUpdate();
      conn.commit();
    } catch (final SQLException e) {
      throw new DAOException("Error :" + e.getMessage());
    } finally {
      daoUtils.close(stmt);
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
    // check if the id is not wrong
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    final ArrayList<Computer> computers = new ArrayList<>();
    try {
      conn = daoUtils.getConnection();
      stmt = daoUtils.initRequest(conn, "SELECT * FROM computer  LEFT JOIN company on computer.company_id=company.id LIMIT ? OFFSET ? ;", false);
      stmt.setInt(1, limit);
      stmt.setLong(2, offset);
      rs = stmt.executeQuery();
      rs.next();
      while (rs.next()) {
        computers.add(MapperDAO.mapComputer(rs));
      }
      return computers;
    } catch (final SQLException e) {
      throw new DAOException("Error :" + e.getMessage());
    } finally {
      daoUtils.close(rs, stmt);
    }

  }

  /**
   * Gets the number instances.
   * @return the number instances
   * @throws DAOException
   * @see com.excilys.formation.java.computerdatabase.dao.IComputerDAO#getNumberInstances()
   */
  @Override
  public int getNumberInstances() throws DAOException {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      conn = daoUtils.getConnection();
      stmt = daoUtils.initRequest(conn, "Select count(*) from computer;", false);
      rs = stmt.executeQuery();
      int i = 0;
      rs.next();
      i = rs.getInt(1);
      return i;
    } catch (final SQLException e) {
      throw new DAOException("Error :" + e.getMessage());
    } finally {
      daoUtils.close(rs, stmt);
    }

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
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    final ArrayList<Computer> computers = new ArrayList<>();
    try {
      conn = daoUtils.getConnection();
      stmt = daoUtils.initRequest(conn, "SELECT * FROM computer  LEFT JOIN company on computer.company_id=company.id  WHERE computer.name LIKE ?  ;", false);
      stmt.setString(1, "%" + string.trim() + "%");
      rs = stmt.executeQuery();
      while (rs.next()) {
        computers.add(MapperDAO.mapComputer(rs));
      }
      return computers;
    } catch (final SQLException e) {
      throw new DAOException("Error :" + e.getMessage());
    } finally {
      daoUtils.close(rs, stmt);
    }
  }

  @Override
  public void updateComputer(Computer computer) throws DAOException {
    getById(computer.getId()); // check if the id is not wrong
    Connection conn = null;
    PreparedStatement stmt = null;
    final ResultSet rs = null;
    try {
      //update the value of the computer
      conn = daoUtils.getConnection();
      stmt = daoUtils.initRequest(conn, "UPDATE computer SET name = ?, introduced= ? ,discontinued= ? , company_id=? WHERE id = ? ", false);
      stmt.setString(1, computer.getName());
      stmt.setString(2, computer.getIntroduced().toString());
      stmt.setString(3, computer.getDiscontinued().toString());
      stmt.setLong(4, computer.getCompany().getId());
      stmt.setLong(5, computer.getId());
      stmt.executeUpdate();
      conn.commit();
    } catch (final SQLException e) {
      rollbackConnection(conn);
      throw new DAOException("error on update :" + e.getMessage());
    } finally {
      daoUtils.close(rs, stmt);
    }
  }
}
