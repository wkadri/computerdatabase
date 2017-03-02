package com.excilys.formation.java.computerdatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.computerdatabase.dto.Pages;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

/**
 * Servlet implementation.
 */
@WebServlet("/Servlet") public class Servlet extends HttpServlet {
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  /** The service. */
  ComputerService service = new ComputerService();

  /**
   * Inits the.
   * @param config the config
   * @throws ServletException the servlet exception
   * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
   */
  @Override public void init(final ServletConfig config) throws ServletException {
    super.init(config);
    System.out.println("initialisation de la servlet TestServlet");
  }

  /**
   * Do get.
   * @param req the req
   * @param res the res
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  @Override public void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
    System.out.println("appel doGet de la servlet Servlet");
    int id = 0;
    int nb = 10;
    String idStr = req.getParameter("id");
    String nbPageStr = req.getParameter("nb");
    if (nbPageStr != null && !nbPageStr.equals("")) {
      nb = Integer.parseInt(nbPageStr);
    }
    if (idStr != null && !idStr.equals("")) {
      id = Integer.parseInt(idStr);
    }
    Pages.setPageMaxSize(nb);
    Pages pages = new Pages(service.getComputers(), nb);
    if (id < 0) {
      id = pages.getEns().size();
    }
    int sum = service.getComputers().size();
    req.setAttribute("suivant", id + 1);
    req.setAttribute("precedent", id - 1);
    req.setAttribute("nb", nb);
    req.setAttribute("id", id);
    req.setAttribute("nbInstances", sum);
    pages.currentPage(id);
    req.setAttribute("allComputers", pages.currentPage());
    ArrayList<Integer> numPage = new ArrayList<>();
    for (int i = -3; i < 3; i++) {
      numPage.add(id % pages.getEns().size() + i);
    }
    numPage.removeIf(t -> t < 0);
    req.setAttribute("allPages", numPage);
    req.getRequestDispatcher("views/index.jsp").forward(req, res);
  }

}
