package com.excilys.formation.java.computerdatabase.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.java.computerdatabase.domain.Computer;
import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;
import com.excilys.formation.java.computerdatabase.ui.dto.CompanyDTO;
import com.excilys.formation.java.computerdatabase.ui.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.ui.dto.mapper.MapperDTO;

/**
 * Servlet implementation class EditComputerServlet.
 */
@Controller
@RequestMapping("/edit-computer")
public class EditComputerController {

  /** The service computer. */
  @Autowired
  private ComputerService serviceComputer;

  /** The company service. */
  @Autowired
  private CompanyService companyService;

  /**
   * Do get.
   * @param request the request
   * @param response the response
   */
  @RequestMapping(method = RequestMethod.GET)
  public String edit(final ModelMap model, @RequestParam(value = "id", defaultValue = "0")
  final int id) {
    final Computer computer = serviceComputer.describeComputerByID(id);
    final ComputerDTO computerDTO = MapperDTO.map(computer);
    model.addAttribute("computerName", computer.getName());
    if (computer.getIntroduced() != null) {

      model.addAttribute("introduced", computer.getIntroduced().toString());
    }

    if (computer.getCompany() != null) {
      model.addAttribute("companyID", computer.getCompany().getId());
    }
    model.addAttribute("companies", companyService.getCompanies());
    model.addAttribute("computeur", computerDTO);

    return "editComputer";
  }

  /**
   * Do post.
   * @param request the request
   * @param response the response
   * @return
   */
  @RequestMapping(method = RequestMethod.POST)
  public String postEdit(final ModelMap model, @RequestParam(value = "id", defaultValue = "0")
  final int id, @RequestParam(value = "companyId", defaultValue = "1")
  final int companyID, @RequestParam(value = "computerName", defaultValue = "")
  final String name, @RequestParam(value = "introduced", defaultValue = "")
  final String introduced, @RequestParam(value = "discontinued", defaultValue = "")
  final String discontinued) {
    //TODO
    final ComputerDTO computerDTO = new ComputerDTO();
    computerDTO.setId(id);
    computerDTO.setName(name);
    computerDTO.setIntroduced(introduced);
    computerDTO.setDiscontinued(discontinued);
    final CompanyDTO companyDTO = new CompanyDTO();
    companyDTO.setId(companyID);
    companyDTO.setName(companyService.getCompanyName(companyID));//TODO verif company
    computerDTO.setCompany(companyDTO);
    final Computer computer = MapperDTO.map(computerDTO);
    serviceComputer.updateComputer(computer);
    return "redirect:computers";
  }

}
