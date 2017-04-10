package com.excilys.formation.java.computerdatabase.service;

import com.excilys.formation.java.computerdatabase.core.User;
import com.excilys.formation.java.computerdatabase.core.dto.UserDTO;
import com.excilys.formation.java.computerdatabase.core.exception.EmailExistsException;

public interface IUserService {

  User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException;

}
