package com.excilys.formation.java.computerdatabase.dao.mysql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class DBUnitExportTable.
 */
public class DBUnitExportTable {

  /**
   * Instantiates a new DB unit export table.
   * @throws SQLException the SQL exception
   * @throws DatabaseUnitException the database unit exception
   * @throws FileNotFoundException the file not found exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void exportDBUnitTable() throws SQLException, DatabaseUnitException, FileNotFoundException, IOException {
    // database connection
    final Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull", "admincdb", "qwerty1234");
    final IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

    final String fileNameExport = "data";

    // Requêtes de sélection des jeux de données
    final QueryDataSet queryDataSet = new QueryDataSet(connection);

    // TODO Définir les requêtes de sélection des données
    queryDataSet.addTable("computer", "select * from computer limit 10");
    queryDataSet.addTable("company", "select * from company limit 10)");

    // Création du répertoire d'export
    final String dirExport = "target/generated";
    (new File(dirExport)).mkdirs();
    // Ordonner les tables par ordre de dépendances dans le fichier d'export XML
    final ITableFilter filter = new DatabaseSequenceFilter(connection);
    final IDataSet orderedDataSet = new FilteredDataSet(filter, queryDataSet);
    // DTD
    FlatDtdDataSet.write(queryDataSet, new FileOutputStream(dirExport + "/" + fileNameExport + ".dtd"));
    // XML
    final FlatXmlWriter datasetWriter = new FlatXmlWriter(new FileOutputStream(dirExport + "/" + fileNameExport + ".xml"));
    datasetWriter.setDocType(fileNameExport + ".dtd");
    datasetWriter.write(orderedDataSet);
  }

}
