package com.excilys.formation.java.computerdatabase.dto;

import java.io.Serializable;

/**
 * @author Walid KADRI
 */

public class ComputerDTO implements Serializable {

	private static final long serialVersionUID = 4238347049602492059L;
	private String name;
	/** ID string */
	private String id;

	public ComputerDTO() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}

	@Override
	public String toString() {
		return "Computer ID: " + id + "  name: " + name;
	}
}
