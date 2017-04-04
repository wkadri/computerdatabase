package com.excilys.formation.java.computerdatabase.ui.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.java.computerdatabase.service.ComputerService;
import com.excilys.formation.java.computerdatabase.ui.dto.Pages;
import com.excilys.formation.java.computerdatabase.ui.dto.mapper.MapperDTO;

/**
 * Servlet implementation.
 */
@Controller
@RequestMapping("/computers")
public class ComputersController {
  /** The service. */
  @Autowired
  private ComputerService service;
  /** The pages. */
  private final Pages pages = new Pages(new ArrayList<>());
  private static Logger log = LoggerFactory.getLogger(ComputersController.class);

  /**
   * Do get.
   * @param req the req
   * @param res the res
   */
  @RequestMapping(method = RequestMethod.GET)
  public String get(final ModelMap model, @RequestParam(value = "id", defaultValue = "1")
  final int id, @RequestParam(value = "nb", defaultValue = "10")
  final int nb, @RequestParam(value = "action", defaultValue = "")
  final String action, @RequestParam(value = "search", defaultValue = "")
  final String search) {
    pagination(id, nb, model);
    if (action != null && action.contains("Filter")) {
      filter(search, model);
    } else {
      model.addAttribute("nbInstances", service.getNumberInstances());
      model.addAttribute("allComputers", pages.getEns().get(pages.getEns().size() - 1));
    }
    return "index";
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
  private void pagination(int id, final int nb, final ModelMap model) {
    final int nbrpage = service.getNumberInstances() / nb + 1;
    if (0 < id && id <= nbrpage) {
      pages.getEns().add(MapperDTO.map(service.getComputersPage(id * nb - nb, nb)));
    } else if (id <= 0) {
      id = 1;
      pages.getEns().set(0, MapperDTO.map(service.getComputersPage(id * nb - nb, nb)));
    } else {
      id = id % nbrpage;
    }
    model.addAttribute("suivant", id + 1);
    model.addAttribute("precedent", id - 1);
    model.addAttribute("nb", nb);
    model.addAttribute("id", id);
    final ArrayList<Integer> numPage = new ArrayList<>();
    for (int i = -3; i < 3; i++) {
      numPage.add(id % pages.getEns().size() + i);
    }
    numPage.removeIf(t -> t < 0);
    model.addAttribute("allPages", numPage);
  }

  /**
   * Filter.
   * @param search
   * @param model the req
   */
  private void filter(final String search, final ModelMap model) {
    model.addAttribute("allComputers", service.filter(search, 0, 200));
    model.addAttribute("nbInstances", service.filter(search, 0, 200).size());
  }

}
