package com.excilys.formation.java.computerdatabase.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.formation.java.computerdatabase.dao.ICompanyDAO;
import com.excilys.formation.java.computerdatabase.domain.Company;
import com.excilys.formation.java.computerdatabase.dto.CompanyDTO;
import com.excilys.formation.java.computerdatabase.mapper.MapperDAO;

/**
 * The Class CompanyDAO.
 */
public class CompanyDAO implements ICompanyDAO {

  /** The sql. */
  private final String sql = "Select * from company";

  /** The dao util. */
  private final DAOUtil daoUtil = DAOUtil.INSTANCE;

  /**
   * Return companies.
   * @return ArrayList<CompanyDTO> the list of companies.
   */
  @Override public ArrayList<Company> getCompanies() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    final ArrayList<Company> companies = new ArrayList<>();
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, sql, false);
      rs = stmt.executeQuery();
      while (rs.next()) {
        companies.add(MapperDAO.mapCompany(rs));
      }
    } catch (final SQLException e) {
      e.printStackTrace();
    }

    return companies;
  }

  @Override public Company getCompanyByID(int id) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Company company= new Company(0);
    try {
      conn = daoUtil.getConnection();
      stmt = daoUtil.initialisationRequetePreparee(conn, "Select * from company where id=?", false);
      stmt.setInt(1, id);
      rs = stmt.executeQuery();
      while (rs.next()) {
       company=MapperDAO.mapCompany(rs);
       
      }
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    return company;
  }

}
