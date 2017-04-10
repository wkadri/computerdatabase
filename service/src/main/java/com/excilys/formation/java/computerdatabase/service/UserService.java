package com.excilys.formation.java.computerdatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.java.computerdatabase.core.User;
import com.excilys.formation.java.computerdatabase.core.dto.UserDTO;
import com.excilys.formation.java.computerdatabase.core.exception.EmailExistsException;
import com.excilys.formation.java.computerdatabase.persistence.IUserDAO;

@Service
public class UserService implements IUserService  {
    @Autowired
    private IUserDAO repository; 
     
    @Transactional
    @Override
    public User registerNewUserAccount(UserDTO accountDto)  throws EmailExistsException {
     
        User user = new User();
        user.setLogin(accountDto.getLogin());
       
        user.setPassword(accountDto.getPassword());
        repository.save(user);
        
        return user;
    }
 
}