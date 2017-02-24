package com.excilys.formation.java.computerdatabase.dao;

import java.util.ArrayList;
import java.util.Optional;

import com.excilys.formation.java.computerdatabase.dao.sql.DAOException;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

/**
 * DAO interface for computers
 * 
 * @author Walid KADRI
 */
public interface IComputerDAO {
	
	public ArrayList<ComputerDTO> getComputers();
	
	public Optional<ComputerDTO> getByID(long id) throws DAOException;
	
	public void updateComputer(int id, String newName, String newIntroduced) throws DAOException;
	
	public void deleteComputer(long l) throws DAOException;
	
	public Optional<ComputerDTO> addComputer(String name, String... introduced) throws DAOException;
	
}
