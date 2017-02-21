package com.excilys.formation.java.computerdatabase.dao.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOException extends Exception {

	Logger log = LoggerFactory.getLogger(DAOException.class);

	/**
	 * Specific Message
	 */
	private String messEx;

	public DAOException(final String string) {
		super();
		messEx = string;
	}

	/**
	 * Get the message of the ExecutorException.
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