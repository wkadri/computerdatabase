package com.excilys.formation.java.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.formation.java.computerdatabase.dto.CompanyDTO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

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
  public static ComputerDTO mapComputerDTO(final ResultSet resultSet) throws SQLException {
    final ComputerDTO computer = new ComputerDTO();
    computer.setId(resultSet.getLong("id"));
    computer.setName(resultSet.getString("name"));
    if (resultSet.getString("introduced") != null) {
      if (!resultSet.getString("introduced").contains("0000-00-00")) {
        computer.setIntroduced(LocalDate.parse(((resultSet.getString("introduced").substring(0, 10)))));
      }
    }
    if (resultSet.getString("company.name") != null) {
      computer.setCompany(new CompanyDTO(resultSet.getString("company.name")));
    }
    return computer;
  }

  /**
   * Map company DTO.
   * @param resultSet the result to set
   * @return the company DTO
   * @throws SQLException the SQL exception
   */
  public static CompanyDTO mapCompanyDTO(final ResultSet resultSet) throws SQLException {
    final CompanyDTO company = new CompanyDTO();
    company.setId(resultSet.getLong("id"));
    company.setName(resultSet.getString("name"));
    return company;
  }
}
