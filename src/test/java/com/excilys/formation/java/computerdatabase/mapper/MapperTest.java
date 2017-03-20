package com.excilys.formation.java.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.formation.java.computerdatabase.domain.Computer;

/**
 * The Class MapperTest.
 * @author Kadri Walid
 */
public class MapperTest {

  /** The Constant COLUMN_NAME. */
  private static final String COLUMN_NAME = "name";

  /** The Constant COLUMN_ID. */
  private static final String COLUMN_ID = "id";

  /** The Constant COLUMN_INTRODUCED. */
  private static final String COLUMN_INTRODUCED = "introduced";

  /** The Constant COLUMN_COMPANY_ID. */
  private static final String COLUMN_COMPANY_ID = "company_id";

  /** The Constant COLUMN_COMPANY_NAME. */
  private static final String COLUMN_COMPANY_NAME = "company_name";

  /** The result set. */
  private ResultSet resultSet;

  /**
   * Inits the before each test.
   * @throws SQLException the SQL exception
   */
  @Before public void initBeforeEachTest() throws SQLException {
    resultSet = Mockito.mock(ResultSet.class);
    Mockito.when(resultSet.getLong(COLUMN_ID)).thenReturn((long) 2);
    Mockito.when(resultSet.getString(COLUMN_NAME)).thenReturn("Name");
    Mockito.when(resultSet.getString(COLUMN_INTRODUCED)).thenReturn("2010-05-02");

    Mockito.when(resultSet.getLong(COLUMN_COMPANY_ID)).thenReturn((long) 15);
    Mockito.when(resultSet.getString(COLUMN_COMPANY_NAME)).thenReturn("Name Company");
  }

  /**
   * Result set to computer.
   * @throws SQLException the SQL exception
   */
  @Test public void resultSetToComputer() throws SQLException {

    final Computer computer = MapperDAO.mapComputer(resultSet);
    if (computer != null) {
      Assert.assertEquals(2, computer.getId());
      Assert.assertEquals("Name", computer.getName());
      Assert.assertEquals(LocalDate.parse("2010-05-02"), computer.getIntroduced());

    }
  }

  /**
   * Introduced null map.
   * @throws SQLException the SQL exception
   */
  @Test public void introducedNullMap() throws SQLException {
    Mockito.when(resultSet.getString(COLUMN_INTRODUCED)).thenReturn("0000-00-00");
    final Computer computer = MapperDAO.mapComputer(resultSet);
    if (computer != null) {
      Assert.assertEquals(computer.getId(), 2);
      Assert.assertEquals(computer.getName(), "Name");
      Assert.assertEquals(computer.getIntroduced(), null);

    }
  }
}
