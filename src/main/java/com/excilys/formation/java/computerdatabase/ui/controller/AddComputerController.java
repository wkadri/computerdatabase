package com.excilys.formation.java.computerdatabase.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;
import com.excilys.formation.java.computerdatabase.ui.dto.CompanyDTO;
import com.excilys.formation.java.computerdatabase.ui.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.ui.dto.mapper.MapperDTO;

/**
 * The Class ServletComputer.
 */
@Controller
@RequestMapping("/add-computer")
public class AddComputerController {

  /** The service computer. */
  @Autowired
  private ComputerService serviceComputer;

  /** The company service. */
  @Autowired
  private CompanyService companyService;

  /**
   * getAdd.
   * @param request the request
   * @param response the response
   */
  @RequestMapping(method = RequestMethod.GET)
  public String getAdd(final ModelMap model) {
    model.addAttribute("companies", companyService.getCompanies());
    return "addComputer";
  }

  /**
   * post Add.
   * @param request the request
   * @param response the response
   */
  @RequestMapping(method = RequestMethod.POST)
  public String postAdd(final ModelMap model, @RequestParam(value = "companyId", defaultValue = "0")
  final int companyID, @RequestParam(value = "computerName", defaultValue = "")
  final String name, @RequestParam(value = "introduced", defaultValue = "") String introduced, @RequestParam(value = "discontinued", defaultValue = "")
  final String discontinued) {
    if (introduced == "") {
      introduced = null;
    }
    final ComputerDTO computerDto = new ComputerDTO();
    computerDto.setName(name);
    //TODO refactore
    if (!introduced.isEmpty()) {
      computerDto.setIntroduced(introduced);
    }
    //TODO changer les vérifs
    if (!discontinued.isEmpty()) {
      computerDto.setDiscontinued(discontinued);
    }
    computerDto.setCompany(new CompanyDTO(companyID, companyService.getCompanyName(companyID)));
    serviceComputer.createComputer(MapperDTO.map(computerDto));
    return "redirect:computers";
  }
}
