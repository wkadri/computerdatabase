package com.excilys.formation.java.computerdatabase.core.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * DTO Class for the object company.
 * @author Walid KADRI
 */
@XmlRootElement(name = "company")
public class CompanyDTO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -4553645858293342650L;

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
   * Instantiates a new company DTO.
   * @param id the id
   * @param name the name
   */
  public CompanyDTO(final long id, final String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Instantiates a new company DTO.
   * @param companyID the company ID
   */
  public CompanyDTO(final long companyID) {
    id = companyID;
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
   * To String.
   * @return string describing the instance
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Company ID: " + id + "  name: " + name;
  }
}
