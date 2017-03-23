package com.excilys.formation.java.computerdatabase.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.computerdatabase.domain.Company;
import com.excilys.formation.java.computerdatabase.domain.Computer;
import com.excilys.formation.java.computerdatabase.dto.CompanyDTO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

/**
 * The Class MapperDTO.
 */
public class MapperDTO {

  private MapperDTO() {}

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
    if (computerDTO.getIntroduced() != null) {
      computer.setIntroduced(LocalDate.parse(computerDTO.getIntroduced()));
    }
    if (computerDTO.getDiscontinued() != null && computerDTO.getIntroduced() != null) {
      computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinued()));
      System.out.println("1");
    }
    if (computerDTO.getIntroduced() == null && computerDTO.getDiscontinued() != null) {
      //Si introduced null et discontinued pas null, computer a introduced=discontinued
      computer.setIntroduced(LocalDate.parse(computerDTO.getDiscontinued()));
      computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinued()));
    }
    if (computerDTO.getCompany() != null) {
      computer.setCompany(new Company(computerDTO.getCompany().getId(), computerDTO.getCompany().getName()));
    }
    System.out.println(computer);
    return computer;
  }

  /**
   * Map.
   * @param computers the computers
   * @return the array list
   */
  public static ArrayList<ComputerDTO> map(final List<Computer> computers) {
    ArrayList<ComputerDTO> listComputer = new ArrayList<>();
    for (Computer comp : computers) {
      listComputer.add(MapperDTO.map(comp));
    }
    return listComputer;
  }

}
