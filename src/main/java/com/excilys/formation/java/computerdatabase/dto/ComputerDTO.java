package com.excilys.formation.java.computerdatabase.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Walid KADRI
 */
@XmlRootElement(name = "computer")
public class ComputerDTO implements Serializable {
	
	private String name;
	/** ID string */
	private long id;
	private LocalDate introduced;
	private CompanyDTO company;
	
	public ComputerDTO() {
		
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
		if (introduced != null) {
			return "Computer ID: " + id + "  name: " + name + "  introduced the " + introduced;
		} else if (company != null) {
			return "Computer ID: " + id + "  name: " + name + " made by  " + company.getName();
		} else
			return "Computer ID: " + id + "  name: " + name;
	}
	
	public LocalDate getIntroduced() {
		return introduced;
	}
	
	public void setIntroduced(final LocalDate introduced) {
		this.introduced = introduced;
	}
	
	public CompanyDTO getCompany() {
		return company;
	}
	
	public void setCompany(final CompanyDTO companyDTO) {
		company = companyDTO;
	}
}
