package com.excilys.formation.java.computerdatabase.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.java.computerdatabase.core.Company;
import com.excilys.formation.java.computerdatabase.service.CompanyService;

@RestController
public class CompanyRESTController {

  @Autowired
  private CompanyService service;

  @GetMapping("/companies")
  public List<?> getCustomers() {
    return service.getCompanies();
  }

  @GetMapping("/companies/{id}")
  public ResponseEntity<Company> getCustomer(@PathVariable("id") Long id) {

    Company company = service.getCompany(id);
    if (company == null) {
      return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Company>(company, HttpStatus.OK);
  }

  @DeleteMapping("/companies/{id}")
  public ResponseEntity<Company> deleteCustomer(@PathVariable Long id) {

    if (null == service.getCompany(id)) {
      return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
    }
    service.deleteCompany(id);
    return new ResponseEntity<Company>(HttpStatus.OK);

  }
}
