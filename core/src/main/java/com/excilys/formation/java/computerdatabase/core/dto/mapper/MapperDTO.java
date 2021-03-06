package com.excilys.formation.java.computerdatabase.core.dto.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.excilys.formation.java.computerdatabase.core.Company;
import com.excilys.formation.java.computerdatabase.core.Computer;
import com.excilys.formation.java.computerdatabase.core.dto.CompanyDTO;
import com.excilys.formation.java.computerdatabase.core.dto.ComputerDTO;

/**
 * The Class MapperDTO.
 */
public class MapperDTO {

  public MapperDTO() {}

  /**
   * Map computer DTO.
   * @param computer the computer
   * @return the computer DTO
   */
  //TODO vérif pas au bon endroit
  //TODO faire le mapping du discontinued
  public static ComputerDTO map(final Computer computer) {
    final ComputerDTO computerDTO = new ComputerDTO();
    if (computer != null) {
      computerDTO.setId(computer.getId());
      computerDTO.setName(computer.getName());
    }
    if (computer.getIntroduced() != null) {
      computerDTO.setIntroduced(computer.getIntroduced().toString());
    }
    if (computer.getDiscontinued() != null) {

      computerDTO.setDiscontinued(computer.getDiscontinued().toString());
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

    final Computer computer = new Computer.ComputerBuilder(computerDTO.getName()).build();
    if (computerDTO.getId() != 0) {
      computer.setId(computerDTO.getId());
    }
    if (computerDTO.getIntroduced() != null && !computerDTO.getIntroduced().isEmpty()) {
      computer.setIntroduced(LocalDate.parse(computerDTO.getIntroduced()));
    }
    //TODO a refaire
    if (computerDTO.getIntroduced() != null && computerDTO.getDiscontinued() != null && !computerDTO.getDiscontinued().isEmpty() && !computerDTO.getIntroduced().isEmpty()) {
      computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinued()));
    }
    if (computerDTO.getIntroduced() != null && computerDTO.getDiscontinued() != null && !computerDTO.getIntroduced().isEmpty() && !computerDTO.getDiscontinued().isEmpty()) {
      //TODO
      //Si introduced null et discontinued pas null, computer a introduced=discontinued
     // computer.setIntroduced(LocalDate.parse(computerDTO.getDiscontinued()));
      //computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinued()));
    }
    
    if (computerDTO.getCompany() != null) {
      computer.setCompany(new Company(computerDTO.getCompany().getId(), computerDTO.getCompany().getName()));
    }
    return computer;
  }

  /**
   * Map.
   * @param computers the computers
   * @return the array list
   */
  public static ArrayList<ComputerDTO> map(final Page<Computer> computers) {
    final ArrayList<ComputerDTO> listComputer = new ArrayList<>();
    for (final Computer comp : computers) {
      listComputer.add(MapperDTO.map(comp));
    }
    return listComputer;
  }

  public static ArrayList<ComputerDTO> map(final List<Computer> computers) {
    final ArrayList<ComputerDTO> listComputer = new ArrayList<>();
    for (final Computer comp : computers) {
      listComputer.add(MapperDTO.map(comp));
    }
    return listComputer;
  }

}
