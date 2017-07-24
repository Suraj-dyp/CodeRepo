package com.tpt.bonzai;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class CSVHandler {
	
	String csv = "C:\\Users\\suraj.kumar\\Desktop\\sample22.csv";
	String [] country = "India#China#United States".split("#");
	public CSVHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public void csvWrite() throws IOException {
		
		CSVWriter writer = new CSVWriter(new FileWriter(csv));

		

		writer.writeNext(country);

		writer.close();
	}

}
