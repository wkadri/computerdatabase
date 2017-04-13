package com.excilys.formation.java.computerdatabase.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.java.computerdatabase.core.Computer;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

@RestController
public class ComputerRESTController {

  @Autowired
  private ComputerService computerService;

  @GetMapping("/computers")
  public List<?> getCustomers() {
    return computerService.getComputers();
  }

  @GetMapping("/computers/{id}")
  public ResponseEntity<Computer> getCustomer(@PathVariable("id") Long id) {

    Computer computer = computerService.describeComputerByID(id);
    if (computer == null) {
      return new ResponseEntity<Computer>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Computer>(computer, HttpStatus.OK);
  }

  @GetMapping("/computers/filtre/{filtre}")
  public List<?> getComputerFilter(@PathVariable("filtre") String filtre) {
    return computerService.filter(filtre, 0, 100);
  }

  @GetMapping("/computers/count")
  public ResponseEntity<Integer> getComputerCount() {
    return new ResponseEntity<Integer>(computerService.getNumberInstances(), HttpStatus.OK);
  }

  @PostMapping(value = "/computers")
  public ResponseEntity<Computer> createCustomer(@RequestBody Computer computer) {
    computerService.createComputer(computer);
    return new ResponseEntity<Computer>(computer, HttpStatus.OK);
  }

  @DeleteMapping("/computers/{id}")
  public ResponseEntity<Computer> deleteCustomer(@PathVariable Long id) {
    if (null == computerService.describeComputerByID(id)) {
      return new ResponseEntity<Computer>(HttpStatus.NOT_FOUND);
    }
    computerService.deleteComputer(id);
    return new ResponseEntity<Computer>(HttpStatus.OK);
  }
}
