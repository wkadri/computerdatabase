package com.excilys.formation.java.computerdatabase.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.java.computerdatabase.dao.IComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.sql.ComputerDAO;
import com.excilys.formation.java.computerdatabase.dao.sql.DAOException;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

/**
 * @author Walid KADRI
 */
public class ComputerService {
	/** Computer DAO */
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
		ComputerDTO computer = null;
		try {
			computer = computerDAO.getByID(id);
			log.info(computer.toString());
		} catch (DAOException e) {
			log.error("Computer not in the database - Reason:");
			log.error(e.getMessage());
		}
		return computer;
	}

	public void createComputer(String name) {
		if (name.isEmpty()) {
			log.warn("Name empty");
			computerDAO.addComputer("computer");
			log.info("Computer ( with default name : computer )added");
		} else {
			computerDAO.addComputer(name.trim());
			log.info("Computer " + name + " added");
		}
	}

	public void updateComputer(int id, String newValue) {
		try {
			computerDAO.updateComputer(id, newValue);
			log.info("Computer id :" + id + " modified");
			
		} catch (DAOException e) {
			log.error("Can't update the computer-Reason:");
			log.error(e.getMessage());
		}
	}

	public void deleteComputer(int i) {
		try {
			computerDAO.deleteComputer(i);
		} catch (DAOException e) {
			log.error("Can't delete the computer-Reason:");
			log.error(e.getMessage());
		}

	}
}
