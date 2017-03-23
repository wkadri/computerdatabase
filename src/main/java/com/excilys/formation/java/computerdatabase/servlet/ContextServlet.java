package com.excilys.formation.java.computerdatabase.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public abstract class ContextServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
/*
  @Override
  public void init(ServletConfig config) throws ServletException {
      super.init(config);
      SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
        config.getServletContext());
  }*/
} 