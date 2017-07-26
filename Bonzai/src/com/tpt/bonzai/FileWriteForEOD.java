package com.tpt.bonzai;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileWriteForEOD {
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
		
	public static void writeEOD() {
			String sqlEOD = "select * from end_of_day where date = CURDATE()";
			try {
				preparedStatement = DataBaseHandler.getConnection().prepareStatement(sqlEOD);
				resultSet = preparedStatement.executeQuery();
				
				String counterpartFilePath = "C:\\Users\\suraj.kumar\\Desktop\\EOD.csv";
				CSVHandler csvHandler = new CSVHandler();
				csvHandler.csvWrite(counterpartFilePath, resultSet);
				//printProfitNLossForCounterpartGivenDateRange();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	

}
