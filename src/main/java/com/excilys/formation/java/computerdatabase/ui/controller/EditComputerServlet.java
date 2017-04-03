package com.excilys.formation.java.computerdatabase.ui.controller;

import java.io.IOException;

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

import com.excilys.formation.java.computerdatabase.AppContext;
import com.excilys.formation.java.computerdatabase.domain.Computer;
import com.excilys.formation.java.computerdatabase.dto.CompanyDTO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.mapper.MapperDTO;
import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;
import com.excilys.formation.java.computerdatabase.service.ICompanyService;
import com.excilys.formation.java.computerdatabase.service.IComputerService;

/**
 * Servlet implementation class EditComputerServlet.
 */
@Controller
@RequestMapping("/edit-computer")
public class EditComputerServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The service computer. */
  @Autowired
  private ComputerService serviceComputer;

  /** The company service. */
  @Autowired
  private CompanyService companyService;

  /**
   * Instantiates a new edits the computer servlet.
   * @see HttpServlet#HttpServlet()
   */
  public EditComputerServlet() {
    super();
  }



  /**
   * Do get.
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @RequestMapping(method = RequestMethod.GET)
  public String edit(ModelMap model,@RequestParam(value="id",defaultValue="0") int id ,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Computer computer = serviceComputer.describeComputerByID(id);
    ComputerDTO computerDTO = MapperDTO.map(computer);
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
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @RequestMapping(method = RequestMethod.POST)
  public String postEdit(ModelMap model,@RequestParam(value="id",defaultValue="0") int id ,@RequestParam(value="computerName",defaultValue="") String name,@RequestParam(value="introduced",defaultValue="") String introduced,@RequestParam(value="discontinued",defaultValue="") String discontinued,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //TODO
    if (introduced == "") {
      introduced = null;
    }
    if (discontinued == "") {
      discontinued = null;
    }
    String companyID = request.getParameter("companyId");
    ComputerDTO computerDTO = new ComputerDTO();
    computerDTO.setId(id);
    computerDTO.setName(name);
    computerDTO.setIntroduced(introduced);
    computerDTO.setDiscontinued(discontinued);
    CompanyDTO companyDTO = new CompanyDTO();
    companyDTO.setId(Integer.valueOf(companyID));
    companyDTO.setName(companyService.getCompanyName(Integer.valueOf(companyID)));//TODO verif company
    computerDTO.setCompany(companyDTO);
    Computer computer = MapperDTO.map(computerDTO);
    serviceComputer.updateComputer(computer);
    return "redirect:computers";
  }

}
