package com.excilys.formation.java.computerdatabase.console;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.excilys.formation.java.computerdatabase.persistence.HibernateConfiguration;
import com.excilys.formation.java.computerdatabase.service.ServiceContext;

@Configuration
@Import(value = { ServiceContext.class, HibernateConfiguration.class })
@ComponentScan(basePackages = { "com.excilys.formation.java.computerdatabase.console" })
public class ConsoleContext {

}
