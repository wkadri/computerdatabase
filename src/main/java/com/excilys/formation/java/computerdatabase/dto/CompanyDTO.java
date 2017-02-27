package com.excilys.formation.java.computerdatabase.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO Class for the object company.
 * @author Walid KADRI
 */
@XmlRootElement(name = "company") public class CompanyDTO implements Serializable {

  /** The id. */
  private long id;

  /** The name. */
  private String name;

  /**
   * Instantiates a new company DTO.
   */
  public CompanyDTO() {

  }

  /**
   * Instantiates a new company DTO.
   * @param name the name
   */
  public CompanyDTO(final String name) {
    this.name = name;
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
   * @return string describing the instance
   */
  @Override public String toString() {
    return "Company ID: " + id + "  name: " + name;
  }
}
