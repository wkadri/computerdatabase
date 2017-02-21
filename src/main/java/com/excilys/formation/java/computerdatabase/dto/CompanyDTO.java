package com.excilys.formation.java.computerdatabase.dto;

import java.io.Serializable;

/**
 * @author Walid KADRI
 */
public class CompanyDTO implements Serializable {

	private static final long serialVersionUID = 7651173031312306304L;
	private String id;
	private String name;

	public CompanyDTO() {

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
		return "Company ID: " + id + "  name: " + name;
	}
}
