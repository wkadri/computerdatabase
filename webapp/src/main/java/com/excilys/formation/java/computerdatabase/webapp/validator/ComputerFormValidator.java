package com.excilys.formation.java.computerdatabase.webapp.validator;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.formation.java.computerdatabase.core.dto.ComputerDTO;

//TODO A refaire
@Component
public class ComputerFormValidator implements Validator {
  static final Logger LOGGER = LoggerFactory.getLogger(ComputerFormValidator.class.getName());

  @Override
  public boolean supports(final Class<?> clazz) {
    return ComputerDTO.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(final Object target, final Errors errors) {

    final ComputerDTO computer = (ComputerDTO) target;
    LOGGER.info("NotBeforeIntroduced.computerForm.discontinued");
    System.out.println("ERROR");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "computerName", "NotEmpty.computerForm.name");

    final LocalDate introduced = LocalDate.parse(computer.getIntroduced());
    final LocalDate discontinued = LocalDate.parse(computer.getDiscontinued());

    if (discontinued.isBefore(introduced)) {
      errors.rejectValue("dateWichIsDiscontinued", "NotBeforeIntroduced.computerForm.discontinued");
      LOGGER.info("NotBeforeIntroduced.computerForm.discontinued");
      System.out.println("ERROR");
    }

  }

}
