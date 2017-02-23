package com.excilys.formation.java.computerdatabase.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.formation.java.computerdatabase.dao.ICompanyDAO;
import com.excilys.formation.java.computerdatabase.dto.CompanyDTO;
import com.excilys.formation.java.computerdatabase.mapper.Mapper;

/**
 * The Class CompanyDAO.
 */
public class CompanyDAO implements ICompanyDAO {
	
	private final String sql = "Select * from company";
	private final DAOUtil daoUtil = DAOUtil.INSTANCE;
	
	@Override
	public ArrayList<CompanyDTO> getCompanies() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final ArrayList<CompanyDTO> companies = new ArrayList<>();
		try {
			conn = daoUtil.getConnection();
			stmt = daoUtil.initialisationRequetePreparee(conn, sql, false);
			rs = stmt.executeQuery();
			while (rs.next()) {
				companies.add(Mapper.mapCompanyDTO(rs));
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		
		return companies;
	}
	
}
