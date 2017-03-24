package com.excilys.formation.java.computerdatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.dto.Pages;
import com.excilys.formation.java.computerdatabase.mapper.MapperDTO;
import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

/**
 * Servlet implementation.
 */
@Controller
@RequestMapping("/computers")
public class ComputersServlet {
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  /** The service. */
//@Autowired
  private ComputerService service;
  /** The pages. */
  private Pages pages = new Pages(new ArrayList<>());
  private static Logger log = LoggerFactory.getLogger(ComputersServlet.class);

  /**
   * Do get.
   * @param req the req
   * @param res the res
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  @RequestMapping(method = RequestMethod.GET, params = { "id", "nb", "action" })
  public String get(@RequestParam int id, @RequestParam int nb, @RequestParam String action, final HttpServletRequest req, final HttpServletResponse res, ModelMap model) throws ServletException, IOException {
    // log.info("appel doGet de la servlet Servlet");
    // int id = getParameterInt(req.getParameter("id"), 1);
    //int nb = getParameterInt(req.getParameter("nb"), 10);

    pagination(id, nb, model);
    if (action != null && action.contains("Filter")) {
      filter(req);
    } else {
      model.addAttribute("nbInstances", service.getNumberInstances());
      model.addAttribute("allComputers", pages.getEns().get(pages.getEns().size() - 1));
    }
    //return "get";

    return "dashboard";
  }

  /**
   * Do Post method.
   * @param req the req
   * @param resp the resp
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  /*
  @RequestMapping(method = RequestMethod.GET)
  public String post(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    //log.info("appel doPost de la servlet Servlet");
    //TODO delete à refaire à cause des changement effectué pour gatling
    String selection = req.getParameter("selection");
    String deleted = req.getParameter("delete");
    if (deleted != null && selection == "dqz") {
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
    return "post";
  
  }*/

  /**
   * Pagination.
   * @param id the id
   * @param nb the nb
   * @param req the req
   */
  private void pagination(int id, int nb, ModelMap model) {
    int nbrpage = (int) (service.getNumberInstances() / nb) + 1;
    if (0 < id && id <= nbrpage) {
      pages.getEns().add(MapperDTO.map(service.getComputersPage((long) (id * nb - nb), nb)));
    } else if (id <= 0) {
      id = 1;
      pages.getEns().set(0, (ArrayList<ComputerDTO>) MapperDTO.map(service.getComputersPage((long) (id * nb - nb), nb)));
    } else {
      id = id % nbrpage;
    }
    model.addAttribute("suivant", id + 1);
    model.addAttribute("precedent", id - 1);
    model.addAttribute("nb", nb);
    model.addAttribute("id", id);
    ArrayList<Integer> numPage = new ArrayList<>();
    for (int i = -3; i < 3; i++) {
      numPage.add(id % pages.getEns().size() + i);
    }
    numPage.removeIf(t -> t < 0);
    model.addAttribute("allPages", numPage);
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
   * @param initialValue the initial value
   * @return the parameter int
   */
  private int getParameterInt(String value, int initialValue) {
    int id = initialValue;
    if (value != null && !value.equals("")) {
      id = Integer.parseInt(value);
    }
    return id;
  }

}
