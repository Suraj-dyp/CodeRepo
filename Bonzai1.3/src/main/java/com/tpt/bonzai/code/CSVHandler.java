package com.tpt.bonzai.code;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opencsv.CSVWriter;

public class CSVHandler {
	
	private static String pathOfFile;
	
	public CSVHandler() {
	}
	
	
	public static void csvWrite(String path, ResultSet resultSet) {
		pathOfFile = path;
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(pathOfFile));
			writer.writeAll(resultSet, true);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	/*String csv = "C:\\Users\\suraj.kumar\\Desktop\\sample22.csv";
	String [] country = "India#China#United States".split("#");
	public CSVHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public void csvWrite() throws IOException {
		
		CSVWriter writer = new CSVWriter(new FileWriter(csv));

		

		writer.writeNext(country);

		writer.close();
	}*/

	
}
