package com.excilys.formation.java.computerdatabase.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

  
  @Controller
  @RequestMapping("/")
  public class DefaultController {
      
      @GetMapping
      public ModelAndView goToDashboard(){
          return new ModelAndView("redirect:/computers");
      }
      
      
      @GetMapping("/login")
      public ModelAndView login(@RequestParam(value = "error", required = false) String error,
              @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

          ModelAndView model = new ModelAndView();
          if (error != null) {
              model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
          }

          if (logout != null) {
              model.addObject("msg", "You've been logged out successfully.");
          }
          model.setViewName("login");

          return model;
      }

      // customize the error message
      private String getErrorMessage(HttpServletRequest request, String key) {

          Exception exception = (Exception) request.getSession().getAttribute(key);

          String error = "";
          if (exception instanceof BadCredentialsException) {
              error = "Invalid username and password!";
          } else if (exception instanceof LockedException) {
              error = exception.getMessage();
          } else {
              error = "Invalid username and password!";
          }

          return error;
      }
      
      // for 403 access denied page
      @RequestMapping(value = "/403", method = RequestMethod.GET)
      public ModelAndView accesssDenied() {

          ModelAndView model = new ModelAndView();

          // check if user is login
          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          if (!(auth instanceof AnonymousAuthenticationToken)) {
              UserDetails userDetail = (UserDetails) auth.getPrincipal();
              System.out.println(userDetail);

              model.addObject("username", userDetail.getUsername());

          }

          model.setViewName("403");
          return model;

      }
      
  }
}
