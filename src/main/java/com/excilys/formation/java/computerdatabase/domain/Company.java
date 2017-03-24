package com.excilys.formation.java.computerdatabase.domain;

/**
 * Business Class representing a company.
 * Class where the intelligence'll be implemented.
 */
public class Company {

  /**
   * Sets the id.
   * @param id the new id
   */
  public void setId(final int id) {
    this.id = id;
  }

  /**
   * Sets the name.
   * @param name the new name
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Instantiates a new company.
   * @param l the id
   * @param name the name
   */
  public Company(final long l, final String name) {
    super();
    id = l;
    this.name = name;
  }

  /**
   * Instantiates a new company.
   * @param i the i
   */
  public Company(int i) {
    super();
    id = i;
  }

  public Company() {

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

  /**
   * Gets the id.
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * To string.
   * @return string describing the instance
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Company ID: " + id + "  name: " + name;
  }
}
