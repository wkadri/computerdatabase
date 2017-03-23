package com.excilys.formation.java.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

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
@WebServlet("/edit-computer")
public class EditComputerServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The service computer. */
  private IComputerService serviceComputer;

  /** The company service. */
  private ICompanyService companyService;

  /**
   * Instantiates a new edits the computer servlet.
   * @see HttpServlet#HttpServlet()
   */
  public EditComputerServlet() {
    super();
  }

  @Override
  public void init() throws ServletException {
    super.init();
    AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
    serviceComputer= (ComputerService) context.getBean(ComputerService.class);
    companyService = (CompanyService) context.getBean(CompanyService.class);
  }

  /**
   * Do get.
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String idStr = request.getParameter("id");
    int id = 0;
    if (idStr != null && !idStr.equals("")) {
      id = Integer.parseInt(idStr);
    }
    Computer computer = serviceComputer.describeComputerByID(id);
    ComputerDTO computerDTO = MapperDTO.map(computer);
    request.setAttribute("computerName", computer.getName());
    if (computer.getIntroduced() != null) {

      request.setAttribute("introduced", computer.getIntroduced().toString());
    }

    if (computer.getCompany() != null) {
      request.setAttribute("companyID", computer.getCompany().getId());
    }
    request.setAttribute("companies", companyService.getCompanies());
    request.setAttribute("computeur", computerDTO);
    request.getRequestDispatcher("views/editComputer.jsp").forward(request, response);
  }

  /**
   * Do post.
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("computerName");
    String introduced = request.getParameter("introduced");
    String discontinued = request.getParameter("discontinued");
    String idStr = request.getParameter("id");
    int id = 0;
    if (introduced == "") {
      introduced = null;
    }
    if (discontinued == "") {
      discontinued = "1198-11-11";
    }
    if (idStr != null && !idStr.equals("")) {
      id = Integer.parseInt(idStr);
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
    response.sendRedirect("/computerdatabase/computers");
  }

}
