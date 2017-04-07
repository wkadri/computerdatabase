package com.excilys.formation.java.computerdatabase.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.formation.java.computerdatabase.core.Computer;
import com.excilys.formation.java.computerdatabase.core.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.core.dto.mapper.MapperDTO;
import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

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
    if (computer.getDiscontinued()!= null) {

      model.addAttribute("discontinued", computer.getDiscontinued().toString());
    }
    if (computer.getCompany() != null) {
      model.addAttribute("companyID", computer.getCompany().getId());
    }
    model.addAttribute("companies", companyService.getCompanies());
    model.addAttribute("computer", computerDTO);
    return "editComputer";
  }

  /**
   * Do post.
   * @param request the request
   * @param response the response
   * @return
   * @return
   */
  @RequestMapping(method = RequestMethod.POST)
  public String postComp(@ModelAttribute("computer")@Validated
   ComputerDTO computerDTO, final BindingResult result, final ModelMap model,
  final RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      return "editComputer";
    }
    final Computer computer = MapperDTO.map(computerDTO);
    serviceComputer.updateComputer(computer);
    return "redirect:computers";

  }

}
