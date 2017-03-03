package com.excilys.formation.java.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

/**
 * The Class ServletComputer.
 */
@WebServlet("/ServletComputer")

public class ServletComputer extends HttpServlet {

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
    String name = request.getParameter("computerName");
    String introduced = request.getParameter("introduced");
    if (introduced == "") {
      introduced = null;
    }
    String companyID = request.getParameter("companyID");
    String action = request.getParameter("action");
    if (action != null && action.contains("Add")) {
      serviceComputer.createComputer(name, introduced, companyID);
    }
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

    doGet(request, response);
  }

}
