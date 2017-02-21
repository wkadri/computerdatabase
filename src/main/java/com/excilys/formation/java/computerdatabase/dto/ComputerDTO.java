package com.excilys.formation.java.computerdatabase.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Walid KADRI
 */
@XmlRootElement(name = "computer")
public class ComputerDTO implements Serializable {

	private String name;
	/** ID string */
	private String id;
	private Date introduced;

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
		if (introduced != null) {
			return "Computer ID: " + id + "  name: " + name + "  introduced the " + introduced;
		} else
			return "Computer ID: " + id + "  name: " + name;
	}

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
}
