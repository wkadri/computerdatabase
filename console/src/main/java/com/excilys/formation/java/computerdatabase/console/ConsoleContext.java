package com.excilys.formation.java.computerdatabase.console;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.excilys.formation.java.computerdatabase.service", "com.excilys.formation.java.computerdatabase.dao" })
public class ConsoleContext {

}
