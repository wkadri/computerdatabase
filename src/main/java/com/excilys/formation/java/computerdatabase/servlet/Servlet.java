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

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation.
 */
@WebServlet("/Servlet") public class Servlet extends HttpServlet {
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  /** The service. */
  ComputerService service = new ComputerService();

  /** The pages. */
  private Pages pages = new Pages(new ArrayList<>());

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

    id = getParameterInt(req.getParameter("id"));
    nb = getParameterInt(req.getParameter("nb"));
    //nb = getParameterInt("nb", req);
    pagination(id, nb, req);

    String action = req.getParameter("action");

    String selection = req.getParameter("selection");
    if (selection != null) {
      System.out.println("DELETE" + selection);
      //selection.split(C3);
      //service.deleteComputer(computerID + 1);
    }
    if (action != null && action.contains("Filter")) {
      filter(req);
    } else {
      req.setAttribute("nbInstances", service.getNumberInstances());
      req.setAttribute("allComputers", pages.getEns().get(pages.getEns().size() - 1));
    }

    req.getRequestDispatcher("views/index.jsp").forward(req, res);
  }

  /**
   * Pagination.
   * @param id the id
   * @param nb the nb
   * @param req the req
   */
  private void pagination(int id, int nb, HttpServletRequest req) {
    int nbrpage = (int) (service.getNumberInstances() / nb) + 1;
    if (0 < id && id <= nbrpage) {
      pages.getEns().add(service.getComputersPage((long) (id * nb - nb), nb));
    } else if (id <= 0) {
      id = 1;
      pages.getEns().set(0, service.getComputersPage((long) (id * nb - nb), nb));
    } else {
      id = id % nbrpage;
    }
    req.setAttribute("suivant", id + 1);
    req.setAttribute("precedent", id - 1);
    req.setAttribute("nb", nb);
    req.setAttribute("id", id);
    ArrayList<Integer> numPage = new ArrayList<>();
    for (int i = -3; i < 3; i++) {
      numPage.add(id % pages.getEns().size() + i);
    }
    numPage.removeIf(t -> t < 0);
    req.setAttribute("allPages", numPage);
  }

  /**
   * Filter.
   * @param req the req
   */
  private void filter(HttpServletRequest req) {
    String filtre = req.getParameter("search");
    req.setAttribute("allComputers", service.filter(filtre, 0, 200));
    req.setAttribute("nbInstances", service.filter(filtre, 0, 200).size());
  }

  /**
   * Gets the parameter int.
   * @param value the value
   * @param req the req
   * @return the parameter int
   */
  private int getParameterInt(String value) {
    int id = 10;
    if (value != null && !value.equals("")) {
      id = Integer.parseInt(value);
    }
    return id;
  }
}
