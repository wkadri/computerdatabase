package com.excilys.formation.java.computerdatabase.dao.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOUtil.
 */
public enum DAOUtil {
	
	INSTANCE;
	/**
	 * Instantiates a new DAO util.
	 */
	
	private static final String FILE_PROPERTIES = "/home/excilys/Documents/Cdb/computerdatabase/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USERNAME = "username";
	private static final String PROPERTY_PASSWORD = "password";
	
	private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private String username = "admincdb";
	private String driver = "com.mysql.jdbc.Driver";
	private String password = "qwerty1234";
	
	private void setProperties() throws DAOException {
		try {
			final Properties properties = new Properties();
			File file = new File(FILE_PROPERTIES);
			FileInputStream fileStream = new FileInputStream(file);
			properties.load(fileStream);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			username = properties.getProperty(PROPERTY_USERNAME);
			password = properties.getProperty(PROPERTY_PASSWORD);
		} catch (final IOException e) {
			throw new DAOException("Impossible de charger le fichier properties " + FILE_PROPERTIES);
		}
	}
	
	public void initConnection() throws DAOException {
		
		try {
			setProperties();
			Class.forName(driver);
		} catch (final ClassNotFoundException e) {
			throw new DAOException("The driver not find in the classpath.");
		}
	}
	
	public Connection getConnection() throws SQLException {
		try {
			initConnection();
		} catch (final DAOException e) {
			e.getMessage();
		}
		return DriverManager.getConnection(url, username, password);
	}
	
	/**
	 * Close
	 * 
	 * @param resultSet the result set
	 */
	public void close(final ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (final SQLException e) {
				System.out.println("Échec de la fermeture du ResultSet : " + e.getMessage());
			}
		}
	}
	
	/**
	 * Close
	 * 
	 * @param statement the statement
	 */
	public void close(final Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (final SQLException e) {
				System.out.println("Échec de la fermeture du Statement : " + e.getMessage());
			}
		}
	}
	
	/**
	 * Close the connection
	 * 
	 * @param connexion the connexion
	 */
	public void close(final Connection connexion) {
		if (connexion != null) {
			try {
				connexion.close();
			} catch (final SQLException e) {
				System.out.println("Échec de la fermeture de la connexion : " + e.getMessage());
			}
		}
	}
	
	/**
	 * Close the statement and the connexion
	 * 
	 * @param statement
	 * @param connexion
	 */
	
	public void close(final Statement statement, final Connection connexion) {
		close(statement);
		close(connexion);
	}
	
	/**
	 * Close
	 *
	 * @param resultSet the result set
	 * @param statement the statement
	 * @param connexion the connexion
	 */
	
	public void close(final ResultSet resultSet, final Statement statement, final Connection connexion) {
		close(resultSet);
		close(statement);
		close(connexion);
	}
	
	/**
	 * Initialisation requete preparee.
	 *
	 * @param connexion the connexion
	 * @param sql the sql
	 * @param returnGeneratedKeys the return generated keys
	 * @param objets the objets
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	
	public PreparedStatement initialisationRequetePreparee(final Connection connexion, final String sql, final boolean returnGeneratedKeys, final Object... objets) throws SQLException {
		final PreparedStatement preparedStatement = connexion.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		for (int i = 0; i < objets.length; i++) {
			preparedStatement.setObject(i + 1, objets[i]);
		}
		return preparedStatement;
	}
	
}
