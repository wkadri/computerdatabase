package com.excilys.formation.java.computerdatabase.dao.sql;

import java.util.ArrayList;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

/**
 * @author Walid KADRI
 */
public class ComputerDAO implements IComputerDAO {

	private SQLEvaluator sqlEvaluator;

	public ComputerDAO() {
		sqlEvaluator = SQLEvaluator.getInstance();
	}

	@Override
	public ArrayList<ComputerDTO> getComputers() {
		ArrayList<ArrayList<String>> stringComputers = sqlEvaluator.evaluate("SELECT * FROM computer", "id", "name");
		ArrayList<ComputerDTO> computers = new ArrayList<>();
		for (ArrayList<String> list : stringComputers) {
			ComputerDTO computer = new ComputerDTO();
			computer.setId(list.get(0));
			computer.setName(list.get(1));
			computers.add(computer);
		}
		return computers;
	}

	@Override
	public ComputerDTO getByID(int id) {
		ArrayList<ArrayList<String>> stringComputers = sqlEvaluator.evaluate(
				"SELECT * FROM computer WHERE id=" + id + ";", "id", "name", "introduced", "discontinued",
				"company_id");
		ComputerDTO computer = new ComputerDTO();
		computer.setId(stringComputers.get(0).get(0));
		computer.setName(stringComputers.get(0).get(1));

		return computer;
	}

	@Override
	public ComputerDTO getByName(int String) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addComputer(String name) {

		sqlEvaluator.evaluate("INSERT INTO computer (name) values  ('" + name + "');");
	}

	@Override
	public void updateComputer(int id, String newName) {
		sqlEvaluator.evaluate("UPDATE computer SET name = '" + newName + "'WHERE id = +" + id + ";");
	}

	@Override
	public void deleteComputer(int id) {

		sqlEvaluator.evaluate("DELETE FROM computer " + "WHERE id = +" + id + ";");
	}

}
