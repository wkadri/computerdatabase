package com.excilys.formation.java.computerdatabase.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 * The Class DAOUtil.
 */
@Component("daoUtils")
public class DAOUtils {

  /**
   * Instantiates a new DAO util.
   */
  private Logger log = LoggerFactory.getLogger(DAOUtils.class);

  /** The Constant FILE_PROPERTIES. */
  private static final String FILE_PROPERTIES = "/home/excilys/Documents/Cdb/computerdatabase/src/main/resources/computerdatabase.properties";

 @Autowired
  private  DataManager ds;

  private Connection connexion;

  public DAOUtils() {
  /*  ds = new DriverManagerDataSource();
    ds.setDriverClassName("com.mysql.jdbc.Driver");
    ds.setUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull");
    ds.setUsername("admincdb");
    ds.setPassword("qwerty1234");*/
  }

  /**
   * Gets the connection.
   * @return the connection
   * @throws SQLException the SQL exception
   */
  public Connection getConnection() throws SQLException {
    connexion = ds.getConnection();
    connexion.setAutoCommit(false);
    return connexion;
  }

  /**
   * Close the result set.
   * @param resultSet the result set to close
   */
  public void close(final ResultSet resultSet) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (final SQLException e) {
        log.error("Échec de la fermeture du ResultSet : " + e.getMessage());
      }
    }
  }

  /**
   * Close the statement.
   * @param statement the statement
   */
  public void close(final Statement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (final SQLException e) {
        log.error("Échec de la fermeture du Statement : " + e.getMessage());
      }
    }
  }

  /**
   * Close the connection.
   */
  public void close() {

    if (connexion != null) {
      try {
        connexion.close();

      } catch (final SQLException e) {
        log.error("Stop the connection : " + e.getMessage());
      }
    }
  }

  /**
   * Close the statement and the connection .
   * @param statement statement
   * @param connexion connexion
   */
  public void close(final Statement statement, final Connection connexion) {
    close(statement);
    close();
  }

  /**
   * Close.
   * @param resultSet the result set
   * @param statement the statement
   * @param connexion the connection
   */

  public void close(final ResultSet resultSet, final Statement statement) {
    close(resultSet);
    close(statement);
    close();
  }

  /**
   * Initialize the request.
   * @param connexion the connexion
   * @param sql the sql
   * @param returnGeneratedKeys the return generated keys
   * @param objets the objets
   * @return the prepared statement
   * @throws SQLException the SQL exception
   */

  public PreparedStatement initRequest(final Connection connexion, final String sql, final boolean returnGeneratedKeys, final Object... objets) throws SQLException {
    final PreparedStatement preparedStatement = connexion.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
    for (int i = 0; i < objets.length; i++) {
      preparedStatement.setObject(i + 1, objets[i]);
    }
    return preparedStatement;
  }

}
