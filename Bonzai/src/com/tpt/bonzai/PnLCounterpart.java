package com.tpt.bonzai;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PnLCounterpart {
	
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	private static Scanner scanner = new Scanner(System.in);	
	
	public static void profitNLossForCounterpartGivenDateRange() {
		String sqlEOD = "SELECT T.counterpart,  EOD.pnl_amount, EOD.date from end_of_day as EOD inner join trade_information as T where EOD.trade_id = T.trade_id and T.counterpart = ? and EOD.date > ? and EOD.date <= ?";
		try {
			preparedStatement = DataBaseHandler.getConnection().prepareStatement(sqlEOD);
			preparedStatement.setString(1, getCounterpartName());
			preparedStatement.setDate(2, getDate("start"));
			preparedStatement.setDate(3, getDate("end"));
			resultSet = preparedStatement.executeQuery();
			
			String counterpartFilePath = "C:\\Users\\suraj.kumar\\Desktop\\counterpart.csv";
			CSVHandler csvHandler = new CSVHandler();
			csvHandler.csvWrite(counterpartFilePath, resultSet);
			//printProfitNLossForCounterpartGivenDateRange();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void profitNLossForCounterpart() {
		String sqlEOD = "SELECT T.counterpart,  sum(EOD.pnl_amount) from end_of_day as EOD inner join trade_information as T where EOD.trade_id = T.trade_id and EOD.date > ? and EOD.date <= ? group by T.counterpart";
		try {
			preparedStatement = DataBaseHandler.getConnection().prepareStatement(sqlEOD);
			preparedStatement.setDate(1, getDate("start"));
			preparedStatement.setDate(2, getDate("end"));
			resultSet = preparedStatement.executeQuery();
			
			String counterpartFilePath = "C:\\Users\\suraj.kumar\\Desktop\\counterparts.csv";
			CSVHandler csvHandler = new CSVHandler();
			csvHandler.csvWrite(counterpartFilePath, resultSet);
			//printProfitNLossForCounterpart();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static String getCounterpartName() {
		System.out.println("Enter Counterpart name:");
		String counterpartName = scanner.next();
		return counterpartName;
	}
	
	private static void printProfitNLossForCounterpart() throws SQLException {
		System.out.println("##############################################");
		System.out.println("Counterpart\tProfit/Loss");
		System.out.println("##############################################");
		while(resultSet.next()) {
			System.out.print(resultSet.getString(1) + "  ");
			System.out.println(resultSet.getString(2) + "  ");
		}
	
	}

	private static void printProfitNLossForCounterpartGivenDateRange() throws SQLException {
		System.out.println("##############################################");
		System.out.println("Counterpart\tDate\tProfit/Loss");
		System.out.println("##############################################");
		while(resultSet.next()) {
			System.out.print(resultSet.getString(1) + "  ");
			System.out.print(resultSet.getString(3) + "  ");
			System.out.println(resultSet.getString(2) + "  ");
		}
	
	}
	
	private static Date getDate(String infoDate) {	
    	System.out.println("Enter the " + infoDate + " Date in format: (YYYY-MM-DD) ");
    	String userDate = scanner.next();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date myDate = null;
		try {
			myDate = format.parse(userDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        //System.out.println(sqlDate);
		return sqlDate;
	}

}
