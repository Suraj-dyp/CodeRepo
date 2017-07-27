package com.tpt.bonzai.code;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PnLCounterpart {
	
	private static Scanner scanner = new Scanner(System.in);	
	
	public static void profitNLossForCounterpartGivenDateRange() {
		String query = "SELECT T.counterpart, EOD.pnl_amount, EOD.date from end_of_day as EOD inner join "
				+ "trade_information as T where EOD.trade_id = T.trade_id and T.counterpart = ? and EOD.date >= ? and EOD.date <= ?";
		String queryType = "select";
		
		DatabaseHelper.makeConnection();
		String counterpartName = getCounterpartName();
		DatabaseHelper.queryExecute(query, queryType, counterpartName, getDate("start"), getDate("end"));
		ResultSet resultSet = DatabaseHelper.getResultSet();
		//displayProfitNLossForCounterpartGivenDateRange(resultSet);
		
		final String counterpartFilePath = "C:\\Users\\bwbss\\Desktop\\counterpart_"+counterpartName+"_.csv";
		CSVHandler.csvWrite(counterpartFilePath, resultSet);
	
		
	}
	
	public static void profitNLossForCounterpart() {
		String query = "SELECT T.counterpart,  sum(EOD.pnl_amount) AS sum_of_pnl from end_of_day as EOD inner join trade_information as T"
				+ " where EOD.trade_id = T.trade_id and EOD.date > ? and EOD.date <= ? group by T.counterpart";
		String queryType = "select";
		DatabaseHelper.makeConnection();
		DatabaseHelper.queryExecute(query, queryType, getDate("start"), getDate("end"));
		ResultSet resultSet = DatabaseHelper.getResultSet();
		//displayProfitNLossForCounterpart(resultSet);
		
		final String counterpartFilePath = "C:\\Users\\bwbss\\Desktop\\all_counterparts.csv";
		CSVHandler.csvWrite(counterpartFilePath, resultSet);

	}
	
	private static String getCounterpartName() {
		System.out.println("Enter Counterpart name:");
		String counterpartName = scanner.next();
		return counterpartName;
	}
	
	
	private static String getDate(String infoDate) {	
    	System.out.println("Enter the " + infoDate + " date in format: (YYYY-MM-DD) ");
    	String userDate = scanner.next();
		return userDate;
	}

}
