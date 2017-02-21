package com.excilys.formation.java.computerdatabase.dao.sql;

/**
 * Class that manage exceptions in DAOs
 */
public class DAOException extends Exception {
	/**
	 * Specific Message
	 */
	private String messEx;

	public DAOException(final String string) {
		super();
		messEx = string;
	}

	/**
	 * Get the message of the DAOException.
	 * 
	 * @return String, the specific message plus exception message
	 */
	@Override
	public String getMessage() {
		if (super.getMessage() != null) {
			return messEx + "  because " + super.getMessage();
		} else {
			return messEx;
		}

	}
}