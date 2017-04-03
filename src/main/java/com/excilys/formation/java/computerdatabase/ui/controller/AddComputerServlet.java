package com.excilys.formation.java.computerdatabase.ui.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.java.computerdatabase.AppContext;
import com.excilys.formation.java.computerdatabase.dto.CompanyDTO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.mapper.MapperDTO;
import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

import net.bytebuddy.asm.Advice.Return;

/**
 * The Class ServletComputer.
 */
@Controller
@RequestMapping("/add-computer")
public class AddComputerServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The service computer. */
  @Autowired
  private ComputerService serviceComputer;

  /** The company service. */
  @Autowired
  private CompanyService companyService;

  /**
   * doGet.
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  @RequestMapping(method = RequestMethod.GET)
  public String getAdd(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    model.addAttribute("companies", companyService.getCompanies());
    return "addComputer";
  }

  /**
   * Do post.
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  @RequestMapping(method = RequestMethod.POST)
  public String postAdd(ModelMap model, @RequestParam(value = "companyId", defaultValue = "0") int companyID, @RequestParam(value = "computerName", defaultValue = "") String name, @RequestParam(value = "introduced", defaultValue = "") String introduced, @RequestParam(value = "discontinued", defaultValue = "") String discontinued, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (introduced == "") {
      introduced = null;
    }
    ComputerDTO computerDto = new ComputerDTO();
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
