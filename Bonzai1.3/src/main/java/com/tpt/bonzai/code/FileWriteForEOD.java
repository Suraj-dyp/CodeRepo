package com.tpt.bonzai.code;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileWriteForEOD {	
		
	public static void writeEOD() {
			String query = "select * from end_of_day where date = CURDATE()";
			final String queryType = "select";
			
			DatabaseHelper dbHelper = new DatabaseHelper();
			dbHelper.makeConnection();
			dbHelper.queryExecute(query, queryType);
			ResultSet resultSet = dbHelper.getResultSet();
			
				
			final String counterpartFilePath = "C:\\Users\\suraj.kumar\\Desktop\\EOD.csv";
			CSVHandler.csvWrite(counterpartFilePath, resultSet);
	
			dbHelper.closeConnection();
	}
		
}
