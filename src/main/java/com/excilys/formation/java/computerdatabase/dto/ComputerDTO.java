package com.excilys.formation.java.computerdatabase.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO for computer Class.
 * @author Walid KADRI
 */
@XmlRootElement(name = "computer") public class ComputerDTO implements Serializable {

  /** The name. */
  private String name;
  /** ID string. */
  private long id;

  /** The introduced. */
  private LocalDate introduced;

  /** The company. */
  private CompanyDTO company;

  /**
   * Instantiates a new computer DTO.
   */
  public ComputerDTO() {

  }

  /**
   * Gets the name.
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   * @param name the new name
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Gets the id.
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id.
   * @param id the new id
   */
  public void setId(final long id) {
    this.id = id;
  }

  /**
   * @see java.lang.Object#toString()
   * @return string
   */
  //TODO plus clean
  @Override public String toString() {
    if (company != null && introduced != null) {
      return "Computer ID: " + id + "  name: " + name + " made by  " + company.getName() + " introduced the " + introduced;
    } else if (introduced != null & company == null) {
      return "Computer ID: " + id + "  name: " + name + "  introduced the " + introduced;
    } else if (company != null & introduced == null) {
      return "Computer ID: " + id + "  name: " + name + " made by  " + company.getName();
    } else {
      return "Computer ID: " + id + "  name: " + name;
    }
  }

  /**
   * Gets the introduced.
   * @return the introduced
   */
  public LocalDate getIntroduced() {
    return introduced;
  }

  /**
   * Sets the introduced.
   * @param introduced the new introduced
   */
  public void setIntroduced(final LocalDate introduced) {
    this.introduced = introduced;
  }

  /**
   * Gets the company.
   * @return the company
   */
  public CompanyDTO getCompany() {
    return company;
  }

  /**
   * Sets the company.
   * @param companyDTO the new company
   */
  public void setCompany(final CompanyDTO companyDTO) {
    company = companyDTO;
  }
}
