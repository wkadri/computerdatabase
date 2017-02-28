package com.excilys.formation.java.computerdatabase.domain;

// TODO: Auto-generated Javadoc
/**
 * Business Class representing a company.
 * Class where the intelligence'll be implemented.
 */
public class Company {

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
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Sets the name.
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Instantiates a new company.
   * @param l the id
   * @param name the name
   */
  public Company(long l, String name) {
    super();
    this.id = l;
    this.name = name;
  }

  /** The id. */
  private long id;
  /** The name. */
  private String name;

  /**
   * Gets the name.
   * @return the name
   */
  public String getName() {

    return name;
  }

}
