package com.excilys.formation.java.computerdatabase.ui.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.excilys.formation.java.computerdatabase.dao.PersistenceContext;
import com.excilys.formation.java.computerdatabase.service.ServiceContext;
import com.excilys.formation.java.computerdatabase.ui.AppContext;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(final ServletContext container) throws ServletException {
    final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(AppContext.class);
    ctx.register(PersistenceContext.class);
    ctx.register(ServiceContext.class);
    ctx.setServletContext(container);
    final ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/");
  }
}
