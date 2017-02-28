package com.excilys.formation.java.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.computerdatabase.service.ComputerService;

/**
 * Servlet implementation.
 */
@WebServlet("/computer") public class Servlet extends HttpServlet {
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  ComputerService service = new ComputerService();

  /**
   * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
   */
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    System.out.println("initialisation de la servlet TestServlet");
  }

  /**
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  public void doGet(HttpServletRequest req, HttpServletResponse res) {
    StringBuffer sb = new StringBuffer();

    System.out.println("appel doGet de la servlet Servlet");

    sb.append("<HTML>\n");
    sb.append("<HEAD>\n");
    sb.append("<TITLE>Bonjour</TITLE>\n");
    sb.append("</HEAD>\n");
    sb.append("<BODY>\n");
    sb.append("<H1>Bonjour</H1>\n");
    
    service.getComputers().forEach(t -> sb.append("<p>"+t.toString() + "</p>"));
   
    sb.append("</BODY>\n");

    sb.append("</HTML>");

    res.setContentType("text/html");
    res.setContentLength(sb.length());

    try {
      res.getOutputStream().print(sb.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
