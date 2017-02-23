package com.excilys.formation.java.computerdatabase.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Walid KADRI
 */
@XmlRootElement(name = "company")
public class CompanyDTO implements Serializable {
	
	private long id;
	private String name;
	
	public CompanyDTO() {
		
	}
	
	public CompanyDTO(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(final long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Company ID: " + id + "  name: " + name;
	}
}
