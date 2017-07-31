package com.tpt.bonzai.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opencsv.CSVWriter;

public class CSVHandler {
	private String pathOfFile;
	
	public void csvWrite(String path, ResultSet resultSet) {
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
	
}
