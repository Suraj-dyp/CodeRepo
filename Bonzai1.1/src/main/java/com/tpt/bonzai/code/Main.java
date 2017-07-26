package com.tpt.bonzai.code;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * @author suraj.kumar
 *
 */
public class Main {
	
	public static void main(String[] args) throws SQLException {
		
		EODCalculation.retrieveDataForEodCalculate();
	}

}
