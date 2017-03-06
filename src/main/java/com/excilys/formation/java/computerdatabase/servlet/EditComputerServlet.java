package com.excilys.formation.java.computerdatabase.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class EditComputerServlet.
 */
@WebServlet("/EditComputerServlet") public class EditComputerServlet extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The service computer. */
  private ComputerService serviceComputer = new ComputerService();

  /** The company service. */
  private CompanyService companyService = new CompanyService();

  /**
   * Instantiates a new edits the computer servlet.
   * @see HttpServlet#HttpServlet()
   */
  public EditComputerServlet() {
    super();
    // TODO Auto-generated constructor stub
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
    String name = request.getParameter("computerName");
    String introduced = request.getParameter("introduced");
    String idStr = request.getParameter("id");
    int id = 0;
    if (introduced == "") {
      introduced = null;
    }
    if (idStr != null && !idStr.equals("")) {
      id = Integer.parseInt(idStr);
    }
    System.out.println("ID+ " + id);
    String companyID = request.getParameter("companyID");
    String action = request.getParameter("action");
    if (action != null && action.contains("Edit")) {
      System.out.println("UPDATE" + name + introduced);
      serviceComputer.updateComputer(id, name, introduced, companyID);
    }
    request.setAttribute("companies", companyService.getCompanies());
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

    doGet(request, response);
  }

}
