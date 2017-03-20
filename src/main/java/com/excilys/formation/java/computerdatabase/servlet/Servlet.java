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
import com.excilys.formation.java.computerdatabase.mapper.MapperDTO;
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

  //private Logger log = LoggerFactory.getLogger(Servlet.class);

  /**
   * Inits the.
   * @param config the config
   * @throws ServletException the servlet exception
   * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
   */
  @Override public void init(final ServletConfig config) throws ServletException {
    super.init(config);
    //log.info("initialisation de la servlet Servlet");
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
    // log.info("appel doGet de la servlet Servlet");
    int id = getParameterInt(req.getParameter("id"), 1);
    int nb = getParameterInt(req.getParameter("nb"), 10);
    pagination(id, nb, req);

    String action = req.getParameter("action");

    if (action != null && action.contains("Filter"))

    {
      filter(req);
    } else {
      req.setAttribute("nbInstances", service.getNumberInstances());
      req.setAttribute("allComputers", pages.getEns().get(pages.getEns().size() - 1));
    }

    req.getRequestDispatcher("views/index.jsp").forward(req, res);
  }

  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //log.info("appel doPost de la servlet Servlet");
    String selection = req.getParameter("selection");
    String deleted = req.getParameter("delete");
    if (deleted != null) {
      service.deleteComputer(Integer.valueOf(deleted));
    }
    if (selection != null) {
      String[] delete = selection.split(",");
      int i = 0;
      while (i < delete.length) {
        if (delete[i] != "") {
          service.deleteComputer(Integer.valueOf(delete[i]));
        }
        i++;
      }
    }
    doGet(req, resp);
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
      pages.getEns().add(MapperDTO.map(service.getComputersPage((long) (id * nb - nb), nb)));
    } else if (id <= 0) {
      id = 1;
      pages.getEns().set(0, MapperDTO.map(service.getComputersPage((long) (id * nb - nb), nb)));
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
  //TODO
  private int getParameterInt(String value, int initialValue) {
    int id = initialValue;
    if (value != null && !value.equals("")) {
      id = Integer.parseInt(value);
    }
    return id;
  }

}
