package com.excilys.formation.java.computerdatabase.dao.sql;

import java.util.ArrayList;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.util.DateUtil;

/**
 * Access to the table Computers
 * 
 * @author Walid KADRI
 */
public class ComputerDAO implements IComputerDAO {
	/** SQL engine */
	private SQLEvaluator sqlEvaluator;
	/** Max number of instances */
	final static int LIMIT_INSTANCES_NUMBER = 500;

	/**
	 * Constructor
	 */
	public ComputerDAO() {
		sqlEvaluator = SQLEvaluator.getInstance();
	}

	/**
	 * Return the complete list of computers
	 */
	@Override
	public ArrayList<ComputerDTO> getComputers() {
		ArrayList<ArrayList<String>> stringComputers = sqlEvaluator
				.evaluate("SELECT * FROM computer LIMIT" + LIMIT_INSTANCES_NUMBER, "id", "name", "introduced");
		ArrayList<ComputerDTO> computers = new ArrayList<>();
		for (ArrayList<String> list : stringComputers) {
			ComputerDTO computer = new ComputerDTO();
			computer.setId(list.get(0));
			computer.setName(list.get(1));
			if (list.get(2) != null) {
				computer.setIntroduced(DateUtil.format(list.get(2)));
			}
			computers.add(computer);
		}
		return computers;
	}

	/**
	 * return the computer with the specified id
	 * 
	 * @param id
	 *            of the computer
	 */
	@Override
	public ComputerDTO getByID(int id) throws DAOException {
		ArrayList<ArrayList<String>> stringComputers = sqlEvaluator
				.evaluate("SELECT * FROM computer WHERE id=" + id + ";", "id", "name", "introduced");
		if (stringComputers.isEmpty()) {
			throw new DAOException("wrong id");
		}
		ComputerDTO computer = new ComputerDTO();
		computer.setId(stringComputers.get(0).get(0));
		computer.setName(stringComputers.get(0).get(1));
		if (stringComputers.get(0).get(2) != null) {
			computer.setIntroduced(DateUtil.format(stringComputers.get(0).get(2)));
		} else {
			computer.setIntroduced(null);
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
	 */
	@Override
	public void addComputer(String name, String introduced) {
		sqlEvaluator.evaluate("INSERT INTO computer (name,introduced) values  ('" + name + "','" + introduced + "');");
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
	@Override
	public void updateComputer(int id, String newName, String newIntroduced) throws DAOException {
		getByID(id);// check if the id is not wrong
		sqlEvaluator.evaluate("UPDATE computer SET name = '" + newName + "' introduced='" + newIntroduced
				+ "' WHERE id = +" + id + ";");
	}

	/**
	 * Delete a computer by ID
	 * 
	 * @param id
	 *            of the computer
	 */
	@Override
	public void deleteComputer(int id) throws DAOException {
		getByID(id);// check if the id is not wrong
		sqlEvaluator.evaluate("DELETE FROM computer " + "WHERE id = +" + id + ";");
	}

}
