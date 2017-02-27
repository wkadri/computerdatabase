package com.excilys.formation.java.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.formation.java.computerdatabase.dao.sql.DAOUtil;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

// TODO: Auto-generated Javadoc
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

  /** The Constant COLUMN_DISCONTINUED. */
  private static final String COLUMN_DISCONTINUED = "discontinued";

  /** The Constant COLUMN_COMPANY_ID. */
  private static final String COLUMN_COMPANY_ID = "company_id";

  /** The Constant COLUMN_COMPANY_NAME. */
  private static final String COLUMN_COMPANY_NAME = "company_name";

  /** The result set. */
  private ResultSet resultSet;

  private DAOUtil dao;

  /**
   * Inits the before each test.
   */
  @Before public void initBeforeEachTest() {
    resultSet = Mockito.mock(ResultSet.class);
    dao = Mockito.mock(DAOUtil.class);
  }

  /**
   * Result set to computer.
   * @throws SQLException the SQL exception
   */
  @Test public void resultSetToComputer() throws SQLException {
    Mockito.when(resultSet.getLong(COLUMN_ID)).thenReturn((long) 2);
    Mockito.when(resultSet.getString(COLUMN_NAME)).thenReturn("Name");
    Mockito.when(dao.getConnection());
    Mockito.when(resultSet.getObject(COLUMN_INTRODUCED)).thenReturn(LocalDate.parse("2010-05-02"));
    Mockito.when(resultSet.getObject(COLUMN_DISCONTINUED)).thenReturn(LocalDate.parse("2013-05-02"));

    Mockito.when(resultSet.getLong(COLUMN_COMPANY_ID)).thenReturn((long) 15);
    Mockito.when(resultSet.getString(COLUMN_COMPANY_NAME)).thenReturn("Name Company");
    ComputerDTO computer = Mapper.mapComputerDTO(resultSet);
    if (computer != null) {
      Assert.assertEquals(computer.getId(), 2);
      Assert.assertEquals(computer.getName(), "Name");
      Assert.assertEquals(computer.getIntroduced(), null);
      // Assert.assertEquals(computer.getCompany().getId(), 15);
      //    Assert.assertEquals(computer.getCompany().getName(), "Name Company");
    }
  }
}
