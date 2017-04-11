package com.excilys.formation.java.computerdatabase.webservice;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.excilys.formation.java.computerdatabase.persistence.HibernateConfiguration;
import com.excilys.formation.java.computerdatabase.service.ServiceContext;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class[] getRootConfigClasses() {
    return new Class[] { WebServiceContext.class,HibernateConfiguration.class,ServiceContext.class };
  }

  @Override
  protected Class[] getServletConfigClasses() {
    return null;
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}
