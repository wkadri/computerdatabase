package com.excilys.formation.java.computerdatabase.dao;

import java.util.ArrayList;
import java.util.Date;

import com.excilys.formation.java.computerdatabase.dao.sql.DAOException;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

/**
 * DAO interface for computers
 * 
 * @author Walid KADRI
 *
 */
public interface IComputerDAO {

	public ArrayList<ComputerDTO> getComputers();

	public ComputerDTO getByID(int id) throws DAOException;

	public void addComputer(String name, String date);

	public void updateComputer(int id, String newName,String newIntroduced) throws DAOException;

	public void deleteComputer(int id) throws DAOException;
}
