package com.excilys.formation.java.computerdatabase.dao.sql;

import java.util.ArrayList;

import com.excilys.formation.java.computerdatabase.dao.ICompanyDAO;
import com.excilys.formation.java.computerdatabase.dto.CompanyDTO;

/**
 * 
 * @author Walid KADRI
 *
 */
public class CompanyDAO implements ICompanyDAO {
	private SQLEvaluator sqlEvaluator;

	public CompanyDAO() {
		sqlEvaluator = SQLEvaluator.getInstance();
	}

	@Override
	public ArrayList<CompanyDTO> getCompanies() {
		ArrayList<ArrayList<String>> stringCompanys = sqlEvaluator.evaluate("SELECT * FROM company", "id", "name");
		ArrayList<CompanyDTO> companies = new ArrayList<>();
		for (ArrayList<String> list : stringCompanys) {
			CompanyDTO company = new CompanyDTO();
			company.setId(list.get(0));
			company.setName(list.get(1));
			companies.add(company);
		}
		return companies;
	}

}
