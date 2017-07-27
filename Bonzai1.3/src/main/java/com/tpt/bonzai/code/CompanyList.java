/**
 * 
 */
package com.tpt.bonzai.code;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author suraj.kumar
 *
 */
public class CompanyList {
	
	private static ArrayList<Company> companyList = new ArrayList<>();
	
	public static ArrayList<Company> getCompanyList() {
		return companyList;
	}

	public static void getListOfCompanies() {
		final String query = "SELECT company_name from company";
		final String queryType = "select";
		DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.makeConnection();
		dbHelper.queryExecute(query, queryType);
		ResultSet resultSet = dbHelper.getResultSet();
		
		storeListOfCompanies(resultSet);
	}
	
	public static void storeListOfCompanies(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				Company company = new Company(resultSet.getString(1));
				companyList.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Company company : companyList) {
			System.out.println(company.getName());
		}
	}

	public static boolean validateCompany(String counterpartName) {
		boolean flag = false;
		for(Company company : companyList) {
			if(company.getName().equalsIgnoreCase(counterpartName)) {
				flag = true;
			}
		}
		return flag;
		
	}

}
