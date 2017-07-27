package com.tpt.bonzai.code;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.impl.validation.ValidationState;

public class PnLCounterpart {
	
	private static Scanner scanner = new Scanner(System.in);	
	
	public static boolean profitNLossForCounterpartGivenDateRange() {
		String query = "SELECT EOD.date, T.counterpart, EOD.trade_id, EOD.transfer_id, EOD.quantity,EOD.trade_price, EOD.market_price, EOD.pnl_amount from end_of_day as EOD inner join "
				+ "trade_information as T where EOD.trade_id = T.trade_id and T.counterpart = ? and EOD.date >= ? and EOD.date <= ?";
		String queryType = "select";
		
		DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.makeConnection();
		String counterpartName = getCounterpartName();
		boolean validCounterpart = CompanyList.validateCompany(counterpartName);
		
		if(validCounterpart) {
			String startDate = validStartDate();
			String endDate = validEndDate();
			dbHelper.queryExecute(query, queryType, counterpartName, startDate, endDate);
			ResultSet resultSet = dbHelper.getResultSet();
			
			//displayProfitNLossForCounterpartGivenDateRange(resultSet);
			
			final String counterpartFilePath = "C:\\Users\\suraj.kumar\\Desktop\\counterpart_"+counterpartName+"_.csv";
			CSVHandler.csvWrite(counterpartFilePath, resultSet);
			dbHelper.closeConnection();
		}
		return validCounterpart;
		
	}

	private static String validEndDate() {
		boolean validEndDate;
		String endDate;
		do {
			endDate = getDate("end");
			validEndDate = validateDate(endDate);
		}while(!validEndDate);
		return endDate;
	}

	private static String validStartDate() {
		boolean validStartDate;
		String startDate;
		do {
			startDate = getDate("start");
			validStartDate = validateDate(startDate);
		}while(!validStartDate);
		return startDate;
	}
	
	private static boolean validateDate(String date) {
		boolean flag = false;
		if(date.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")) {
			flag = true;
		}
		return flag;
	}

	public static void profitNLossForCounterpart() {
		String query = "SELECT T.counterpart,  AVG(EOD.pnl_amount) AS average_of_pnl from end_of_day as EOD inner join trade_information as T"
				+ " where EOD.trade_id = T.trade_id and EOD.date > ? and EOD.date <= ? group by T.counterpart";
		String queryType = "select";
		String startDate = validStartDate();
		String endDate = validEndDate();
		DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.makeConnection();
		dbHelper.queryExecute(query, queryType, startDate, endDate);
		ResultSet resultSet = dbHelper.getResultSet();
		
		//displayProfitNLossForCounterpart(resultSet);
		
		final String counterpartFilePath = "C:\\Users\\suraj.kumar\\Desktop\\all_counterparts.csv";
		CSVHandler.csvWrite(counterpartFilePath, resultSet);
		
		dbHelper.closeConnection();

	}
	
	private static String getCounterpartName() {
		System.out.println("Enter Counterpart name:");
		String counterpartName = scanner.nextLine();
		return counterpartName;
	}
	
	
	private static String getDate(String infoDate) {	
    	System.out.println("Enter the " + infoDate + " date in format: (YYYY-MM-DD) ");
    	String userDate = scanner.next();
		return userDate;
	}

}
