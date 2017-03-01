package com.excilys.formation.java.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

@WebServlet("/ServletComputer") public class ServletComputer extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private ComputerService serviceComputer = new ComputerService();
  private CompanyService companyService = new CompanyService();

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String name = request.getParameter("computerName");
    String introduced = request.getParameter("introduced");
    if (introduced=="") {
      introduced = null;
    }
    String companyID=request.getParameter("companyID");
    String action = request.getParameter("action");
    System.out.println(action + name + introduced);
    if (action!=null && action.contains("Add")) {
      System.out.println("YES");
      serviceComputer.createComputer(name, introduced, companyID);
    }

    request.setAttribute("companies", companyService.getCompanies());
    request.getRequestDispatcher("views/addComputer.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    doGet(request, response);
  }

}
