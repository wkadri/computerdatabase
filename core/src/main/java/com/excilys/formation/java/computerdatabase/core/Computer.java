package com.excilys.formation.java.computerdatabase.core;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Business Class representing a computer.
 * Class where the intelligence'll be implemented.
 * Follow a Builder Pattern
 */
@Entity
@Table(name = "computer")
public class Computer {

  /** The id. */
  @Id
  private long id;
  /** The name. */
  @Column(name = "name")
  private String name;

  /** The intoduced. */
  @Column(name = "introduced")
  private LocalDate introduced;

  /** The discontinued. */
  @Column(name = "discontinued")
  private LocalDate discontinued;

  /** The company. */
  @ManyToOne(targetEntity = Company.class)//Many company sur one computer
  private Company company;

  public Computer() {
    // TODO Auto-generated constructor stub
  }

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
    private LocalDate introduced = null;

    /** The discontinued. */
    private LocalDate discontinued = null;
    /** The company. */
    private Company company;

    /**
     * Instantiates a new computer builder.
     * @param name the name
     */
    public ComputerBuilder(final String name) {

      company = new Company(1, "Apple");//TODO default value to counter Nullpointerexception
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

  public long getId() {
    return id;
  }

  public void setId(final long l) {
    id = l;
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
    introduced = intoduced;
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

  /**
   * To String.
   * @see java.lang.Object#toString()
   * @return string
   */
  //TODO refcto:trop compliqué
  @Override
  public String toString() {

    if (company != null && introduced != null && discontinued == null) {
      return "Computer ID: " + id + "  name: " + name + " made by  " + company.getName() + " introduced the " + introduced;
    } else if (introduced != null & company == null) {
      return "Computer ID: " + id + "  name: " + name + "  introduced the " + introduced;
    } else if (company != null & introduced == null) {
      return "Computer ID: " + id + "  name: " + name + " made by  " + company.getName();
    } else if (discontinued != null && introduced != null) {
      if (company != null) {
        return "Computer ID: " + id + "  name: " + name + " made by  " + company.getName() + " introduced the " + introduced + " discontinued the " + discontinued;
      } else {
        return "Computer ID: " + id + "  name: " + name + " introduced the " + introduced + " discontinued the " + discontinued;
      }
    } else {
      return "Computer ID: " + id + "  name: " + name;
    }
  }

}
