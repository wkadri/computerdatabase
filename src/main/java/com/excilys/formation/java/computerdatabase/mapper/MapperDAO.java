package com.excilys.formation.java.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.formation.java.computerdatabase.domain.Company;
import com.excilys.formation.java.computerdatabase.domain.Computer;

/**
 * The Class MapperDAO.
 */
public class MapperDAO {

  /**
   * Map computer DTO.
   * @param resultSet the result set
   * @return the computer DTO
   * @throws SQLException the SQL exception
   */
  public static Computer mapComputer(final ResultSet resultSet) throws SQLException {
    final Computer computer = new Computer.ComputerBuilder(resultSet.getString("name")).build();
    computer.setId(resultSet.getLong("id"));
    if (resultSet.getString("introduced") != null) {
      if (!resultSet.getString("introduced").contains("0000-00-00")) {
        computer.setIntroduced(LocalDate.parse(((resultSet.getString("introduced").substring(0, 10)))));
      }
    }
    if (resultSet.getString("company.name") != null) {
      computer.setCompany(new Company(resultSet.getLong("company.id"), resultSet.getString("company.name")));
    }
    return computer;
  }

  /**
   * Map company DTO.
   * @param resultSet the result to set
   * @return the company DTO
   * @throws SQLException the SQL exception
   */
  public static Company mapCompany(final ResultSet resultSet) throws SQLException {
    final Company company = new Company(resultSet.getLong("id"), resultSet.getString("name"));
    return company;
  }
}
