package com.excilys.formation.java.computerdatabase.dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.formation.java.computerdatabase.dao.ICompanyDAO;
import com.excilys.formation.java.computerdatabase.domain.Company;

/**
 * The Class CompanyDAO.
 */
@Component
public class CompanyDAO implements ICompanyDAO {

  /** The dao util. */
  @Autowired
  public void setDataSource(final DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  private JdbcTemplate jdbcTemplate;

  public CompanyDAO() {
    jdbcTemplate = new JdbcTemplate();
  }

  /**
   * Return companies.
   * @return ArrayList<CompanyDTO> the list of companies.
   */
  @Override
  public ArrayList<Company> getCompanies() {
    ArrayList<Company> companies = new ArrayList<>();
    companies = (ArrayList<Company>) jdbcTemplate.query("Select * from company", new CompanyMapper());
    return companies;
  }

  /**
   * Returns a comapny by ID.
   * @param id the id
   * @return the company by ID
   */
  @Override
  public Company getCompanyByID(final int id) {
    ArrayList<Company> companies = new ArrayList<>();
    companies = (ArrayList<Company>) jdbcTemplate.query("Select * from company where id=?", new Object[] { id }, new CompanyMapper());
    return companies.get(0);
  }

  final class CompanyMapper implements RowMapper<Company> {

    /**
     * Map company DTO.
     * @param resultSet the result to set
     * @return the company DTO
     * @throws SQLException the SQL exception
     */
    @Override
    public Company mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
      final Company company = new Company(resultSet.getLong("id"), resultSet.getString("name"));
      return company;
    }
  }
}
