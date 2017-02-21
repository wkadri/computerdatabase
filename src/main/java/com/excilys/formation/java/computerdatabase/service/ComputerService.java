package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.sql.ComputerDAO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

/**
 * @author Walid KADRI
 */
public class ComputerService {

	private IComputerDAO computerDAO;
	private Logger log;

	public ComputerService() {
		computerDAO = new ComputerDAO();
		log = LoggerFactory.getLogger(ComputerService.class);
	}

	public ArrayList<ComputerDTO> getComputers() {
		ArrayList<ComputerDTO> computers = computerDAO.getComputers();
		computers.forEach(t -> log.info(t.toString()));
		return computers;
	}

	public ComputerDTO describeComputerByID(int id) {
		ComputerDTO computer = computerDAO.getByID(id);
		log.info(computer.toString());
		return computer;
	}

	public void createComputer(String name) {
		 computerDAO.addComputer(name);
	}

	public void updateComputer(int id, String newValue) {
		computerDAO.updateComputer(id, newValue);
	}

	public void deleteComputer(int i) {

		computerDAO.deleteComputer(i);
	}
}
