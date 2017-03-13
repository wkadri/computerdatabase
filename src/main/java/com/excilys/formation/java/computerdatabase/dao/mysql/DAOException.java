package com.excilys.formation.java.computerdatabase.dao.mysql;

/**
 * Class that manage exceptions in DAOs.
 * @author Walid KADRI
 */
public class DAOException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /**
   * Specific Message.
   */
  private final String messEx;

  /**
   * Instantiates a new DAO exception.
   * @param string the string
   */
  public DAOException(final String string) {
    super();
    messEx = string;
  }

  /**
   * Get the message of the DAOException.
   * @return String, the specific message plus exception message
   */
  @Override public String getMessage() {
    if (super.getMessage() != null) {
      return messEx + "  because " + super.getMessage();
    } else {
      return messEx;
    }
  }
}
