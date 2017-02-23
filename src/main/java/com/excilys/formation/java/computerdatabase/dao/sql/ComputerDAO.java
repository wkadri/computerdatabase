package com.excilys.formation.java.computerdatabase.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.mapper.Mapper;

/**
 * Access to the table Computers
 * 
 * @author Walid KADRI
 */
public class ComputerDAO implements IComputerDAO {
	
	/** Max number of instances */
	private final DAOUtil daoUtil = DAOUtil.INSTANCE;
	
	/**
	 * Return the complete list of computers
	 */
	@Override
	public ArrayList<ComputerDTO> getComputers() {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final ArrayList<ComputerDTO> computers = new ArrayList<>();
		try {
			conn = daoUtil.getConnection();
			stmt = daoUtil.initialisationRequetePreparee(conn, "select * from computer  LEFT JOIN company on computer.company_id=company.id", false);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				computers.add(Mapper.mapComputerDTO(rs));
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		daoUtil.close(rs, stmt, conn);
		return computers;
	}
	
	/**
	 * return the computer with the specified id
	 * 
	 * @param id
	 *            of the computer
	 */
	@Override
	public ComputerDTO getByID(final long id) throws DAOException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ComputerDTO computer = new ComputerDTO();
		try {
			conn = daoUtil.getConnection();
			stmt = daoUtil.initialisationRequetePreparee(conn, "SELECT * FROM computer  LEFT JOIN company on computer.company_id=company.id WHERE computer.id= ? ;", false);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			if (rs == null) {
				throw new DAOException("wrong id");
			}
			while (rs.next()) {
				computer = Mapper.mapComputerDTO(rs);
			}
			
			daoUtil.close(rs, stmt, conn);
			
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}
	
	/**
	 * Add a computer to the table
	 * 
	 * @param String
	 *            name of the computer
	 * @param introduced
	 *            string corresponding of the date introduced
	 * @throws DAOException
	 */
	@Override
	public ComputerDTO addComputer(final String name, final String... entries) throws DAOException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		final ComputerDTO computer = new ComputerDTO();
		try {
			conn = daoUtil.getConnection();
			stmt = daoUtil.initialisationRequetePreparee(conn, "INSERT INTO computer (name,introduced,company_id) values  (  ?,?,? );", false);
			stmt.setString(1, name);
			stmt.setString(2, entries[0]);
			stmt.setString(3, entries[1]);
			final int status = stmt.executeUpdate();
			if (status != 0) {
				final long lastid = (getComputers().get(getComputers().size() - 1)).getId();
				computer.setId(lastid);
				computer.setName(name);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		
		return computer;
	}
	
	/**
	 * Update a computer
	 * 
	 * @param id
	 *            of the computer
	 * @param newName
	 *            new name
	 * @param newIntroduced
	 *            new date introduced
	 */
	//TODO add company to update
	@Override
	public void updateComputer(final int id, final String newName, final String newIntroduced) throws DAOException {
		getByID(id);// check if the id is not wrong
		Connection conn = null;
		PreparedStatement stmt = null;
		final ResultSet rs = null;
		try {
			conn = daoUtil.getConnection();
			stmt = daoUtil.initialisationRequetePreparee(conn, "UPDATE computer SET name = ? WHERE id = ?;", false);
			stmt.setLong(2, id);
			stmt.setString(1, newName);
			stmt.executeUpdate();
			
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		daoUtil.close(rs, stmt, conn);
	}
	
	/**
	 * Delete a computer by ID
	 * 
	 * @param id
	 *            of the computer
	 */
	@Override
	public void deleteComputer(final long id) throws DAOException {
		getByID(id);// check if the id is not wrong
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = daoUtil.getConnection();
			stmt = daoUtil.initialisationRequetePreparee(conn, "DELETE FROM computer " + "WHERE id = ?;", false);
			stmt.setLong(1, id);
			final int status = stmt.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
	
}
