package com.excilys.formation.java.computerdatabase.domain;

import java.time.LocalDate;

/**
 * Business Class representing a computer.
 * Class where the intelligence'll be implemented.
 * Follow a Builder Pattern
 */
public class Computer {

  /** The id. */
  private long id;

  public long getId() {
    return id;
  }

  public void setId(final long l) {
    this.id = l;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public LocalDate getIntroduced() {
    return introduced;
  }

  public void setIntroduced(final LocalDate intoduced) {
    this.introduced = intoduced;
  }

  public LocalDate getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(final LocalDate discontinued) {
    this.discontinued = discontinued;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(final Company company) {
    this.company = company;
  }

  /** The name. */
  private String name;

  /** The intoduced. */
  private LocalDate introduced;

  /** The discontinued. */
  private LocalDate discontinued;

  /** The company. */
  private Company company;

  /**
   * Instantiates a new computer.
   * @param builder the builder
   */
  private Computer(final ComputerBuilder builder) {

    id = builder.id;
    name = builder.name;
    introduced = builder.introduced;
    discontinued = builder.discontinued;
    company = builder.company;

  }

  /**
   * The Class ComputerBuilder.
   */
  public static class ComputerBuilder {

    /** The id. */
    private int id;

    /** The name. */
    private final String name;

    /** The introduced. */
    private LocalDate introduced;

    /** The discontinued. */
    private LocalDate discontinued;
    /** The company. */
    private Company company;

    /**
     * Instantiates a new computer builder.
     * @param name the name
     */
    public ComputerBuilder(final String name) {
      this.name = name;
    }

    /**
     * Introduced.
     * @param introduced the introduced
     * @return the computer builder
     */
    public ComputerBuilder introduced(final LocalDate introduced) {
      this.introduced = introduced;
      return this;
    }

    /**
     * Discontinued.
     * @param discontinued the discontinued
     * @return the computer builder
     */
    public ComputerBuilder discontinued(final LocalDate discontinued) {
      this.discontinued = discontinued;
      return this;
    }

    /**
     * Company.
     * @param company the company
     * @return the computer builder
     */
    public ComputerBuilder company(final Company company) {
      this.company = company;
      return this;
    }

    /**
     * Builds the computer.
     * @return the computer
     */
    public Computer build() {
      return new Computer(this);
    }
  }

 

}
