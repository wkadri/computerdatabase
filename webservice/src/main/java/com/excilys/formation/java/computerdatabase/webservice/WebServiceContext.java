package com.excilys.formation.java.computerdatabase.webservice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.excilys.formation.java.computerdatabase.webservice" })
public class WebServiceContext {

}
