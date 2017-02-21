package com.excilys.formation.java.computerdatabase.dao;

import java.util.ArrayList;

import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

public interface IComputerDAO {

	public ArrayList<ComputerDTO> getComputers();

	public ComputerDTO getByID(int id);

	public ComputerDTO getByName(int String);

	public void addComputer(String name);

	public void updateComputer(int id, String newName);

	public void deleteComputer(int id);
}
