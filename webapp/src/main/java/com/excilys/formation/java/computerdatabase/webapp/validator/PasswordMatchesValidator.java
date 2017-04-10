package com.excilys.formation.java.computerdatabase.webapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.excilys.formation.java.computerdatabase.core.dto.UserDTO;



public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> { 
   
  @Override
  public void initialize(PasswordMatches constraintAnnotation) {       
  }
  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context){   
      UserDTO user = (UserDTO) obj;
      return user.getPassword().equals("admin");   //TODO là il retroune tout le temps true 
  }     
}
