package com.excilys.formation.java.computerdatabase.dao.sql;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.util.DateUtil;

/**
 * 
 * @author Walid KADRI
 */
public class ComputerDAO implements IComputerDAO {
	/** SQL engine */
	private SQLEvaluator sqlEvaluator;

	public ComputerDAO() {
		sqlEvaluator = SQLEvaluator.getInstance();
	}

	@Override
	public ArrayList<ComputerDTO> getComputers() {
		ArrayList<ArrayList<String>> stringComputers = sqlEvaluator.evaluate("SELECT * FROM computer", "id", "name",
				"introduced");
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

	@Override
	public void addComputer(String name, String introduced) {
		sqlEvaluator.evaluate(
				"INSERT INTO computer (name,introduced) values  ('" + name + "','" + introduced + "');");
	}

	@Override
	public void updateComputer(int id, String newName,String newIntroduced) throws DAOException {
		getByID(id);// check if the id is not wrong
		sqlEvaluator.evaluate("UPDATE computer SET name = '" + newName + "' introduced='"+newIntroduced+"' WHERE id = +" + id + ";");
	}

	@Override
	public void deleteComputer(int id) throws DAOException {
		getByID(id);// check if the id is not wrong
		sqlEvaluator.evaluate("DELETE FROM computer " + "WHERE id = +" + id + ";");
	}

}
