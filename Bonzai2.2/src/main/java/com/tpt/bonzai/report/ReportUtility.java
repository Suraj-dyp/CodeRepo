package com.tpt.bonzai.report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ReportUtility {
	
	private Scanner scanner = new Scanner(System.in);
	
	public String getFilePath() {
		System.out.println("Enter File Path:");
		String path = scanner.nextLine();
		return path;
	}
	public String getCounterpartName() {
		System.out.println("Enter Counterpart name:");
		String counterpartName = scanner.nextLine();
		return counterpartName;
	}
	
	public String getInternalCompanyName() {
		System.out.println("Enter Internal Company name:");
		String internalCompanyName = scanner.nextLine();
		return internalCompanyName;
	}
	
	public String getWarehouseName() {
		System.out.println("Enter Warehouse name:");
		String warehouseName = scanner.nextLine();
		return warehouseName;
	}
	
	
	public String getDate() {	
    	System.out.println("Enter the COB date in format: (YYYY-MM-DD) ");
    	String userDate = scanner.nextLine();
		return userDate;
	}
	
	public java.sql.Date getSQLDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
		try {
			utilDate = format.parse(date);
		} catch (ParseException e) {
			
		}
        
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
       }
	
	public int getTradeId() {
		System.out.println("Enter Trade ID:");
		int tradeId = scanner.nextInt();
		return tradeId;
	}
	
	public String getCommodity() {
		System.out.println("Enter Commodity:");
		String commodity = scanner.nextLine();
		return commodity;
	}
	
	public void displayReport(ResultSet resultSet, int numberIterations) {
		try {
			while(resultSet.next()) {
				for(int i=1; i<=numberIterations; i++)
					System.out.print(resultSet.getString(i) + "  ");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
