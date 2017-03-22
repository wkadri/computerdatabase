package com.excilys.formation.java.computerdatabase.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.excilys.formation.java.computerdatabase.AppContext;
import com.excilys.formation.java.computerdatabase.dao.ICompanyDAO;
import com.excilys.formation.java.computerdatabase.domain.Company;
import com.excilys.formation.java.computerdatabase.mapper.MapperDAO;

/**
 * The Class CompanyDAO.
 */ 
@Component
public class CompanyDAO implements ICompanyDAO {

  /** The sql. */
  private final String sql = "Select * from company";

  /** The dao util. */
  @Autowired
  DAOUtils daoUtils;

  public CompanyDAO() {
    //ApplicationContext context = new AnnotationConfigApplicationContext(DAOUtils.class);
  
  }

  /**
   * Return companies.
   * @return ArrayList<CompanyDTO> the list of companies.
   */
  @Override
  public ArrayList<Company> getCompanies() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    final ArrayList<Company> companies = new ArrayList<>();
    try {
      conn = daoUtils.getConnection();
      stmt = daoUtils.initRequest(conn, sql, false);
      rs = stmt.executeQuery();
      while (rs.next()) {
        companies.add(MapperDAO.mapCompany(rs));
      }
    } catch (final SQLException e) {
      e.printStackTrace();
    }

    return companies;
  }

  /**
   * Returns a comapny by ID.
   * @param id the id
   * @return the company by ID
   */
  @Override
  public Company getCompanyByID(int id) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Company company = new Company(0);
    try {
      conn = daoUtils.getConnection();
      stmt = daoUtils.initRequest(conn, "Select * from company where id=?", false);
      stmt.setInt(1, id);
      rs = stmt.executeQuery();
      while (rs.next()) {
        company = MapperDAO.mapCompany(rs);

      }
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    return company;
  }

}
