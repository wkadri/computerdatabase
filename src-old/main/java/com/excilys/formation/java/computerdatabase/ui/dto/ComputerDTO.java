package com.excilys.formation.java.computerdatabase.ui.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.excilys.formation.java.computerdatabase.domain.Computer;
import com.excilys.formation.java.computerdatabase.ui.dto.mapper.MapperDTO;

/**
 * DTO for computer Class.
 * @author Walid KADRI
 */
@XmlRootElement(name = "computer")
public class ComputerDTO implements Serializable {

  /**
   */
  private static final long serialVersionUID = 8353717455299508169L;
  /** The name. */
  private String name;
  /** ID string. */
  private long id;

  /** The introduced. */
  private String introduced;

  /** The introduced. */
  private String discontinued;

  public String getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(final String discontinued) {
    this.discontinued = discontinued;
  }

  /** The company. */
  private CompanyDTO company;

  /**
   * Instantiates a new computer DTO.
   */
  public ComputerDTO() {
    company = new CompanyDTO();
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
  @Override
  public String toString() {
    final Computer comp = MapperDTO.map(this);
    return comp.toString();
  }

  /**
   * Gets the introduced.
   * @return the introduced
   */
  public String getIntroduced() {
    return introduced;
  }

  /**
   * Sets the introduced.
   * @param introduced the new introduced
   */
  public void setIntroduced(final String introduced) {
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
