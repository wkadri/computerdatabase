package com.excilys.formation.java.computerdatabase.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.domain.Computer;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.mapper.MapperDAO;
import com.sun.jna.platform.win32.Sspi.TimeStamp;

// TODO: Auto-generated Javadoc
/**
 * Access to the table Computers.
 * @author Walid KADRI
 */
public class ComputerDAO implements IComputerDAO {

  /** Max number of instances. */
  private final DAOUtil daoUtil = DAOUtil.INSTANCE;

  /** The log. */
  private Logger log = LoggerFactory.getLogger(CompanyDAO.class);

  /**
   * Return the complete list of computers.
   * @return the computers
   */
  @Override public ArrayList<Computer> getComputers() {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    final ArrayList<Computer> computers = new ArrayList<>();
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "select * from computer  LEFT JOIN company on computer.company_id=company.id", false);
      rs = stmt.executeQuery();
      rs.next();
      while (rs.next()) {
        computers.add(MapperDAO.mapComputer(rs));
      }
    } catch (final SQLException e) {
      e.printStackTrace();
    }

    daoUtil.close(rs, stmt, conn);
    return computers;
  }

  /**
   * Rollback the connection.
   * @param conn the conn
   */
  private void rollbackConnection(Connection conn) {
    try {
      conn.rollback();
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
  }

  /**
   * return the computer with the specified id.
   * @param id of the computer
   * @return the by id
   * @throws DAOException
   */
  @Override public Optional<Computer> getById(final long id) throws DAOException {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Computer computer = null;
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "SELECT * FROM computer  LEFT JOIN company on computer.company_id=company.id WHERE computer.id= ? ;", false);
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

      daoUtil.close(rs, stmt, conn);
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    return Optional.of(computer);
  }

  /**
   * Add a computer to the table.
   * The first param after the name must be the introduced date.
   * The second param after the name should be the company name.
   * @param name the name
   * @param entries the entries
   * @return the optional
   * @throws DAOException if wrong id
   */
  @Override public Optional<Computer> addComputer(Computer computer) throws DAOException {

    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      conn = daoUtil.getConnection();
      conn.setAutoCommit(true);
      stmt = daoUtil.initialisationRequetePreparee(conn, "INSERT INTO computer (name,introduced,company_id)  values  (  ?,?,? );", false);
      stmt.setString(1, computer.getName());
      if (computer.getIntroduced() != null)
        stmt.setString(2, computer.getIntroduced().toString());
      else {
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
        final long lastid = (getComputers().get(getComputers().size() - 1)).getId();
        computer.setId(lastid);
        computer.setName(computer.getName());
      }
      // conn.commit();
      daoUtil.close(stmt, conn);
    } catch (final SQLException e) {
      //rollbackConnection(conn);
      e.printStackTrace();
      log.error(e.getMessage());
    }
    // daoUtil.close(stmt, conn);
    return Optional.of(computer);
  }

  /**
   * Update a computer.
   * @param id of the computer
   * @param newName new name
   * @param newIntroduced new date introduced
   * @param companyId the company id
   * @throws DAOException the DAO exception
   */
  //TODO add company to update
  @Override public void updateComputer(final long id, final String newName, final String newIntroduced, final String companyId) throws DAOException {
    getById(id); // check if the id is not wrong
    Connection conn = null;
    PreparedStatement stmt = null;
    final ResultSet rs = null;
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "UPDATE computer SET name = ?, introduced= ? , company_id=? WHERE id = ? ", false);
      stmt.setString(1, newName);
      stmt.setString(2, newIntroduced);
      stmt.setString(3, companyId);
      stmt.setLong(4, id);
      stmt.executeUpdate();
      conn.commit();

    } catch (final SQLException e) {
      rollbackConnection(conn);
      e.printStackTrace();
    }
    daoUtil.close(rs, stmt, conn);
  }

  /**
   * Delete a computer by ID.
   * @param id of the computer
   * @throws DAOException the DAO exception
   */
  @Override public void deleteComputer(final long id) throws DAOException {
    getById(id); // check if the id is not wrong
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "DELETE FROM computer WHERE id = ?;", false);
      stmt.setLong(1, id);
      stmt.executeUpdate();
      conn.commit();
      daoUtil.close(stmt, conn);
    } catch (final SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Delete a computer by ID.
   * @param offset the offset
   * @param limit the limit
   * @return the computers page
   * @throws DAOException the DAO exception
   */
  @Override public ArrayList<Computer> getComputersPage(final long offset, final int limit) throws DAOException {
    // check if the id is not wrong
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    final ArrayList<Computer> computers = new ArrayList<>();
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "SELECT * FROM computer  LEFT JOIN company on computer.company_id=company.id LIMIT ? OFFSET ? ;", false);
      stmt.setInt(1, limit);
      stmt.setLong(2, offset);
      rs = stmt.executeQuery();
      rs.next();
      while (rs.next()) {
        computers.add(MapperDAO.mapComputer(rs));
      }
      daoUtil.close(rs, stmt, conn);
      return computers;
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Gets the number instances.
   * @return the number instances
   * @see com.excilys.formation.java.computerdatabase.dao.IComputerDAO#getNumberInstances()
   */
  @Override public int getNumberInstances() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "Select count(*) from computer;", false);
      rs = stmt.executeQuery();
      int i = 0;
      rs.next();
      i = rs.getInt(1);
      daoUtil.close(rs, stmt, conn);
      return i;
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * Filter.
   * @param string the string
   * @param offset the offset
   * @param limit the limit
   * @return the array list
   */
  public ArrayList<Computer> filter(String string, final long offset, final int limit) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    final ArrayList<Computer> computers = new ArrayList<>();
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "SELECT * FROM computer  LEFT JOIN company on computer.company_id=company.id WHERE computer.name LIKE ?  LIMIT ? OFFSET ? ;", false);
      stmt.setString(1, "%" + string + "%");
      stmt.setInt(2, limit);
      stmt.setLong(3, offset);
      rs = stmt.executeQuery();
      rs.next();
      while (rs.next()) {
        computers.add(MapperDAO.mapComputer(rs));
      }
      daoUtil.close(rs, stmt, conn);
      return computers;
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
