package com.excilys.formation.java.computerdatabase.mapper;

import java.util.ArrayList;

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
   */
  public static ComputerDTO map(final Computer computer) {
    final ComputerDTO computerDTO = new ComputerDTO();
    if (computer != null) {
      computerDTO.setId(computer.getId());
      computerDTO.setName(computer.getName());
    }
    if (computer.getIntroduced() != null) {
      if (!computer.getIntroduced().toString().contains("0000-00-00")) {
        computerDTO.setIntroduced(computer.getIntroduced());
      }
    }
    if (computer.getCompany() != null) {
      computerDTO.setCompany(new CompanyDTO(computer.getCompany().getId(), computer.getCompany().getName()));
    }
    return computerDTO;
  }

  /**
   * Map.
   * @param computerDTO the computer DTO
   * @return the computer
   */
  public static Computer map(final ComputerDTO computerDTO) {
    final Computer computer = new Computer.ComputerBuilder(computerDTO.getName()).introduced(computerDTO.getIntroduced()).company(new Company(computerDTO.getCompany().getId(), computerDTO.getCompany().getName())).build();
    return computer;
  }

  public static ArrayList<ComputerDTO> map(final ArrayList<Computer> computers) {
    ArrayList<ComputerDTO> listComputer = new ArrayList<>();
    for (Computer comp : computers) {
      listComputer.add(MapperDTO.map(comp));
    }
    return listComputer;
  }
}
