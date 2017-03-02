package com.excilys.formation.java.computerdatabase.mapper;

import java.sql.SQLException;

import com.excilys.formation.java.computerdatabase.domain.Company;
import com.excilys.formation.java.computerdatabase.domain.Computer;
import com.excilys.formation.java.computerdatabase.dto.CompanyDTO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

/**
 * The Class MapperDTO.
 */
public class MapperDTO {

  /**
   * Map computer DTO.
   * @param computer the computer
   * @return the computer DTO
   * @throws SQLException the SQL exception
   */
  public static ComputerDTO map(final Computer computer) throws SQLException {
    final ComputerDTO computerDTO = new ComputerDTO();
    computerDTO.setId(computer.getId());
    computerDTO.setName(computer.getName());
    if (computer.getIntoduced() != null) {
      if (!computer.getIntoduced().toString().contains("0000-00-00")) {
        computerDTO.setIntroduced(computer.getIntoduced());
      }
    }
    if (computer.getCompany().getName() != null) {
      computerDTO.setCompany(new CompanyDTO(computer.getCompany().getName()));
    }
    return computerDTO;
  }

  /**
   * Map.
   * @param computerDTO the computer DTO
   * @return the computer
   * @throws SQLException the SQL exception
   */
  public static Computer map(final ComputerDTO computerDTO) throws SQLException {
    final Computer computer = new Computer.ComputerBuilder(computerDTO.getName()).introduced(computerDTO.getIntroduced()).company(new Company(computerDTO.getCompany().getId(), computerDTO.getCompany().getName())).build();
    return computer;
  }
}
