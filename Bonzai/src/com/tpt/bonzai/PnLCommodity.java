package com.tpt.bonzai;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.opencsv.CSVWriter;

public class PnLCommodity {
	
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	private static Scanner scanner = new Scanner(System.in);	
	
	public static void profitNLossForCommodities() {
		String sqlEOD = "SELECT T.commodity, sum(EOD.pnl_amount) as sum_pnl from end_of_day as EOD inner join trade_information as T where EOD.trade_id = T.trade_id GROUP BY T.commodity";
		try {
			preparedStatement = DataBaseHandler.getConnection().prepareStatement(sqlEOD);
			resultSet = preparedStatement.executeQuery();
			
			String commodityFilePath = "C:\\Users\\suraj.kumar\\Desktop\\commodity.csv";
			CSVHandler csvHandler = new CSVHandler();
			csvHandler.csvWrite(commodityFilePath, resultSet);
			
			//printProfitNLossForCommodities();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void printProfitNLossForCommodities() throws SQLException {
		System.out.println("##############################################");
		System.out.println("Commodity\tProfit/Loss");
		System.out.println("##############################################");
		while(resultSet.next()) {
			System.out.print(resultSet.getString(1) + "  ");
			System.out.println(resultSet.getString(2) + "  ");
		}
	
	}

}
