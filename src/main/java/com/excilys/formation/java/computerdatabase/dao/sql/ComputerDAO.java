package com.excilys.formation.java.computerdatabase.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.mapper.MapperDAO;

/**
 * Access to the table Computers.
 * @author Walid KADRI
 */
public class ComputerDAO implements IComputerDAO {

  /** Max number of instances. */
  private final DAOUtil daoUtil = DAOUtil.INSTANCE;

  /**
   * Return the complete list of computers.
   * @return the computers
   */
  @Override public ArrayList<ComputerDTO> getComputers() {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    final ArrayList<ComputerDTO> computers = new ArrayList<>();
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "select * from computer  LEFT JOIN company on computer.company_id=company.id", false);
      rs = stmt.executeQuery();
      rs.next();
      while (rs.next()) {
        computers.add(MapperDAO.mapComputerDTO(rs));
      }
    } catch (final SQLException e) {
      rollbackConnection(conn);
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
   */
  @Override public Optional<ComputerDTO> getById(final long id) {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    ComputerDTO computer = new ComputerDTO();
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "SELECT * FROM computer  LEFT JOIN company on computer.company_id=company.id WHERE computer.id= ? ;", false);
      stmt.setLong(1, id);
      rs = stmt.executeQuery();
      rs.beforeFirst();
      if (rs.next()) {
        computer = MapperDAO.mapComputerDTO(rs);
        computer.toString();
      } else {
        System.out.println("Error: Wrong ID");
      }
    } catch (final SQLException e) {
      rollbackConnection(conn);
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
  @Override public Optional<ComputerDTO> addComputer(final String name, final String... entries) throws DAOException {

    Connection conn = null;
    PreparedStatement stmt = null;
    final ComputerDTO computer = new ComputerDTO();
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "INSERT INTO computer (name,introduced,company_id) values  (  ?,?,? );", false);
      stmt.setString(1, name);
      if (entries.length == 2) {
        stmt.setString(2, entries[0]);

        stmt.setString(3, entries[1]);
      } else if (entries.length == 1) {
        stmt.setString(2, entries[0]);
        stmt.setString(3, "null");
      }

      final int status = stmt.executeUpdate();
      if (status != 0) {
        final long lastid = (getComputers().get(getComputers().size() - 1)).getId();
        computer.setId(lastid);
        computer.setName(name);
      }
    } catch (final SQLException e) {
      rollbackConnection(conn);
      e.printStackTrace();
    }

    return Optional.of(computer);
  }

  /**
   * Update a computer.
   * @param id of the computer
   * @param newName new name
   * @param newIntroduced new date introduced
   * @throws DAOException the DAO exception
   */
  //TODO add company to update
  @Override public void updateComputer(final long id, final String newName, final String newIntroduced) throws DAOException {
    getById(id); // check if the id is not wrong
    Connection conn = null;
    PreparedStatement stmt = null;
    final ResultSet rs = null;
    try {

      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "UPDATE computer SET name = ?, introduced= ? WHERE id = ? ", false);
      stmt.setString(1, newName);
      stmt.setString(2, newIntroduced);
      stmt.setLong(3, id);
      stmt.executeUpdate();

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
    } catch (final SQLException e) {
      e.printStackTrace();
    }
  }

}
