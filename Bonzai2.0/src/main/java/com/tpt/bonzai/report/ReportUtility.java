package com.tpt.bonzai.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ReportUtility {
	
	private Scanner scanner = new Scanner(System.in);
	
	public String getCounterpartName() {
		System.out.println("Enter Counterpart name:");
		String counterpartName = scanner.nextLine();
		return counterpartName;
	}
	
	public String getWarehouseName() {
		System.out.println("Enter Warehouse name:");
		String warehouseName = scanner.nextLine();
		return warehouseName;
	}
	
	
	public String getDate(String infoDate) {	
    	System.out.println("Enter the " + infoDate + " date in format: (YYYY-MM-DD) ");
    	String userDate = scanner.next();
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
	
	public int getTransferId() {
		System.out.println("Enter Transfer ID:");
		int transferId = scanner.nextInt();
		return transferId;
	}

}
