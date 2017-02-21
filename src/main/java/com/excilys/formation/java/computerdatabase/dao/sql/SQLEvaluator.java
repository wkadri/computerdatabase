package com.excilys.formation.java.computerdatabase.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Walid Kadri
 */
public class SQLEvaluator {

	private Connection conn;
	private static SQLEvaluator instance;

	public static SQLEvaluator getInstance() {
		if (instance == null) {
			instance = new SQLEvaluator();
		}
		return instance;
	}

	private SQLEvaluator() {
	}

	private void initConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/computer-database-db";
			conn = DriverManager.getConnection(url, "admincdb", "qwerty1234");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ArrayList<String>> evaluate(String query, String... colsName) {
		ArrayList<ArrayList<String>> results = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		initConnection();
		try {
			stmt = conn.createStatement();
			if (stmt.execute(query)) {
				rs = stmt.getResultSet();

			}
		} catch (

		SQLException ex) {
			// handle any errors
			ex.printStackTrace();
		}

		// Now do something with the ResultSet ....

		try {
			if (rs != null) {
				while (rs.next()) {
					try {
						ArrayList<String> list = new ArrayList<>();
						for (String colName : colsName) {
							String foundResult = rs.getString(colName);
							list.add(foundResult);
							// System.out.print(" " + foundResult);
						}
						results.add(list);
						// System.out.print("\n\r");
					} catch (SQLException e) {// ignore errors
												// TODO
					}
				}

			} // else the statement return no results(like for update,delete and
				// create statements)
		} catch (SQLException ex) {ex.printStackTrace();
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					// ignore
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					// ignore
				}
			}

		}
		return results;
	}
}