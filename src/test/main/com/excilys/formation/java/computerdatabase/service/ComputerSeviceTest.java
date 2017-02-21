package com.excilys.formation.java.computerdatabase.service;

import org.junit.Assert;
import org.junit.Test;

import com.excilys.formation.java.computerdatabase.dao.sql.DAOException;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

public class ComputerSeviceTest {

	ComputerService service = new ComputerService();

	@Test
	public void createTest() {
		int initSize = service.getComputers().size();
		service.createComputer("MacDo");
		Assert.assertEquals(initSize + 1, service.getComputers().size());
	}

	@Test
	public void deleteTest() throws NumberFormatException, DAOException {
		int initSize = service.getComputers().size();
		service.createComputer("MacDo");
		service.deleteComputer(Integer.valueOf(service.getComputers().get(service.getComputers().size()-1).getId()));
		Assert.assertEquals(initSize, service.getComputers().size());
	}

	@Test
	public void updateTest() throws DAOException {
		ComputerDTO before = service.describeComputerByID(96);
		service.updateComputer(96, "MacInTouch");
		service.describeComputerByID(96);
		Assert.assertNotEquals(before, service.describeComputerByID(96));
	}
}
