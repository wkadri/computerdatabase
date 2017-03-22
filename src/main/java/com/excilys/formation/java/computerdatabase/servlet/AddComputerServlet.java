package com.excilys.formation.java.computerdatabase.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.computerdatabase.dto.CompanyDTO;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.mapper.MapperDTO;
import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

/**
 * The Class ServletComputer.
 */
@WebServlet("/ServletComputer")

public class AddComputerServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The service computer. */
  private ComputerService serviceComputer = new ComputerService();

  /** The company service. */
  private CompanyService companyService = new CompanyService();

  /**
   * doGet.
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    request.setAttribute("companies", companyService.getCompanies());
    request.getRequestDispatcher("views/addComputer.jsp").forward(request, response);
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
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("computerName");
    String introduced = request.getParameter("introduced");
    String discontinued = request.getParameter("discontinued");
    if (introduced == "") {
      introduced = null;
    }
    String companyID = request.getParameter("companyId");
    ComputerDTO computerDto = new ComputerDTO();
    computerDto.setName(name);
    //TODO refactore
    if (introduced != null || introduced == "null") {
      computerDto.setIntroduced(introduced);
    }
    //TODO changer les vérif
    if (discontinued != null || discontinued == "null") {
      computerDto.setDiscontinued(discontinued);
    }
    int id = Integer.valueOf(companyID);
    computerDto.setCompany(new CompanyDTO(id, companyService.getCompanyName(id)));

    serviceComputer.createComputer(MapperDTO.map(computerDto));
    request.getRequestDispatcher("Servlet").forward(request, response);
  }

}
