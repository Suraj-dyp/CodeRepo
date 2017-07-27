package com.tpt.bonzai.code;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileWriteForEOD {	
		
	public static void writeEOD() {
			String query = "select * from end_of_day where date = CURDATE()";
			final String queryType = "select";
			DatabaseHelper.makeConnection();
			DatabaseHelper.queryExecute(query, queryType);
			ResultSet resultSet = DatabaseHelper.getResultSet();
				
			final String counterpartFilePath = "C:\\Users\\bwbss\\Desktop\\EOD.csv";
			CSVHandler.csvWrite(counterpartFilePath, resultSet);
	
	}
		
}
