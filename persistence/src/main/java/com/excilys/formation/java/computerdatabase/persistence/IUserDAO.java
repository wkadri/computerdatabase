package com.excilys.formation.java.computerdatabase.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.excilys.formation.java.computerdatabase.core.User;

public interface IUserDAO extends JpaRepository<User, Long> {

  @Query(value="Select * from user where email= ?1", nativeQuery=true)
  User findByEmail(String email);
  
}
