package com.excilys.formation.java.computerdatabase.dao.sql;

import java.io.IOException;
import java.io.InputStream;
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
	
	private static final String FILE_PROPERTIES = "/resources/dao";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USERNAME = "username";
	private static final String PROPERTY_PASSWORD = "password";
	
	private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private String username = "admincdb";
	private String driver = "com.mysql.jdbc.Driver";
	private String password = "qwerty1234";
	
	//TODO property file
	private void setProperties() throws DAOException {
		try {
			final Properties properties = new Properties();
			final ClassLoader classLoader = DAOUtil.class.getClassLoader();
			System.out.println(classLoader.getResource(FILE_PROPERTIES));
			InputStream fichierProperties = classLoader.getResource(FILE_PROPERTIES).openStream();
			
			if (fichierProperties == null) {
				throw new DAOException("the properties file " + FILE_PROPERTIES + " not found.");
			}
			
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			username = properties.getProperty(PROPERTY_USERNAME);
			password = properties.getProperty(PROPERTY_PASSWORD);
		} catch (final IOException e) {
			throw new DAOException("Impossible de charger le fichier properties " + FILE_PROPERTIES);
		}
	}
	
	public void initConection() throws DAOException {
		try {
			Class.forName(driver);
		} catch (final ClassNotFoundException e) {
			throw new DAOException("The driver not find in the classpath.");
		}
	}
	
	public Connection getConnection() throws SQLException {
		try {
			initConection();
		} catch (final DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(url, username, password);
	}
	
	/**
	 * Fermeture silencieuse.
	 *
	 * @param resultSet the result set
	 */
	/* Fermeture silencieuse du resultset */
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
	 * Fermeture silencieuse.
	 *
	 * @param statement the statement
	 */
	/* Fermeture silencieuse du statement */
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
	 * Fermeture silencieuse.
	 *
	 * @param connexion the connexion
	 */
	/* Fermeture silencieuse de la connexion */
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
	 * Fermetures silencieuses.
	 *
	 * @param statement the statement
	 * @param connexion the connexion
	 */
	/* Fermetures silencieuses du statement et de la connexion */
	public void close(final Statement statement, final Connection connexion) {
		close(statement);
		close(connexion);
	}
	
	/**
	 * Fermetures silencieuses.
	 *
	 * @param resultSet the result set
	 * @param statement the statement
	 * @param connexion the connexion
	 */
	/* Fermetures silencieuses du resultset, du statement et de la connexion */
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
	/*
	 * Initialise la requête préparée basée sur la connexion passée en argument,
	 * avec la requête SQL et les objets donnés.
	 */
	public PreparedStatement initialisationRequetePreparee(final Connection connexion, final String sql, final boolean returnGeneratedKeys, final Object... objets) throws SQLException {
		final PreparedStatement preparedStatement = connexion.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		for (int i = 0; i < objets.length; i++) {
			preparedStatement.setObject(i + 1, objets[i]);
		}
		return preparedStatement;
	}
	
}
