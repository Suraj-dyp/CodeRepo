//package com.tpt.bonzai.report;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashSet;
//
//import com.tpt.bonzai.database.details.DatabaseDetails;
//import com.tpt.bonzai.database.utilities.DatabaseUtilities;
//import com.tpt.bonzai.pojo.CobPOJO;
//
//public class ResultUsingTradeIdPOJOCreation {
//	
//	private HashSet<CobPOJO> listResultPOJO = new HashSet<>();
//	private DatabaseDetails dbDetails = new DatabaseDetails();
//	ReportUtility reportUtility = new ReportUtility();
//	
//	public HashSet<CobPOJO> getListResultPOJO() {
//		return listResultPOJO;
//	}
//	
//	public void setResultPOJO() {
//		
//		DatabaseUtilities dbUtilities = new DatabaseUtilities();
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet =null;
//		
//		String query = "SELECT date, trade_id, quantity, trade_price, market_price, pnl_amount from end_of_day where transfer_id "
//				+ "IS NULL AND trade_id = ? AND date >= ? AND date <= ?";
//		
//		try {
//			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, reportUtility.getTradeId());
//			preparedStatement.setDate(2, reportUtility.getSQLDate(reportUtility.getDate("start")));
//			preparedStatement.setDate(3, reportUtility.getSQLDate(reportUtility.getDate("end")));
//			resultSet = preparedStatement.executeQuery();
//			while(resultSet.next()) {
//				CobPOJO resultPOJO = new CobPOJO(resultSet.getInt(2), resultSet.getDouble(3), resultSet.getDouble(4), 
//						resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getString(1));
//				listResultPOJO.add(resultPOJO);
//			}
//			
//			
//			//System.out.println("In try");
//			
//		} catch (SQLException e) {
//			//System.out.println("In catch");
//			dbUtilities.printSQLException(e);
//		}
//		finally {
//			//System.out.println("In finally");
//			dbUtilities.closeResultSet(resultSet);
//			dbUtilities.closeStatement(preparedStatement);
//			dbUtilities.closeConnection(connection);
//		}
//		
//	}
//
//}

















/*package com.tpt.bonzai.report;

import java.util.HashSet;

import com.tpt.bonzai.pojo.CobPOJO;

public class Report extends ReportUtility {
	private final String DATE = "EOD_DATE";
	private final String TRADE_ID = "TRADE_ID";
	private final String TRANSFER_ID = "TRANSFER_ID";
	private final String QUANTITY = "QUANTITY";
	private final String TRADE_PRICE = "TRADE_PRICE";
	private final String MARKET_PRICE = "MARKET_PRICE";
	private final String PROFIT_LOSS = "PROFIT/LOSS";
	private final String COMMODITY = "COMMODITY";
	private final String WAREHOUSE = "WAREHOUSE";
	private final String COUNTERPART = "COUNTERPART";
	private final String AVG_PROFIT_LOSS  = "AVERAGE_PROFIT/LOSS";
	
	
	public void generateReportUsingTrade(HashSet<CobPOJO> listResultPOJO ) {
		//CSVUtility csvUtility = new CSVUtility();
		String header = DATE + " " + TRADE_ID + " " + QUANTITY + " " + TRADE_PRICE + " " + MARKET_PRICE + " " + PROFIT_LOSS;
		//csvUtility.csvWrite(header);
		System.out.println();
		System.out.println(header);
		for(CobPOJO resultPOJO : listResultPOJO) { 
			String result = resultPOJO.getEodDate() + "\t " + resultPOJO.getTradeId() + "\t\t " +
					 "\t " + resultPOJO.getQuantity() + "\t \t" + resultPOJO.getTradePrice() + 
					 " \t" + resultPOJO.getMarketPrice() + "\t\t " + resultPOJO.getProfitAndLoss();
			//csvUtility.csvWrite(result);
			System.out.println(result);
		}
		System.out.println();
			
	}

	public void generateReportUsingTradeAndTransfer(HashSet<CobPOJO> listResultPOJO ) {
		
		System.out.println();
		String header = DATE + " " + TRADE_ID + TRANSFER_ID + " " + QUANTITY + " " + TRADE_PRICE + " " + MARKET_PRICE + " " + PROFIT_LOSS;
		System.out.println(header);
		for(CobPOJO resultPOJO : listResultPOJO) {
			String result = resultPOJO.getEodDate() + " \t" + resultPOJO.getTradeId() + "\t\t " +
					resultPOJO.getTransferId() + "\t " + resultPOJO.getQuantity() + "\t \t" + resultPOJO.getTradePrice() + 
					"\t " + resultPOJO.getMarketPrice() + "\t " + resultPOJO.getProfitAndLoss();
			System.out.println(result);
		}
		System.out.println();	
	}
	
	public void generateReportUsingCounterpart(HashSet<CobPOJO> listResultPOJO ) {
		
		System.out.println();
		String header = COUNTERPART + " " + DATE + " " + TRADE_ID + TRANSFER_ID + " " + QUANTITY + " " + TRADE_PRICE + " " + MARKET_PRICE + " " + PROFIT_LOSS;
		System.out.println(header);
		for(CobPOJO resultPOJO : listResultPOJO) {
			String result = resultPOJO.getCounterpart() + "\t " + resultPOJO.getEodDate() + " \t" + resultPOJO.getTradeId() + "\t\t " +
					resultPOJO.getTransferId() + " \t" + resultPOJO.getQuantity() + "\t \t" + resultPOJO.getTradePrice() + 
					" " + resultPOJO.getMarketPrice() + " \t" + resultPOJO.getProfitAndLoss();
			System.out.println(result);
		}
		System.out.println();	
	}
	
	public void generateReportUsingWarehouse(HashSet<CobPOJO> listResultPOJO ) {
		
		System.out.println();
		String header = COMMODITY + " " + AVG_PROFIT_LOSS;
		System.out.println(header);
		for(CobPOJO resultPOJO : listResultPOJO) {
			String result = resultPOJO.getCommodity() + " \t\t" + resultPOJO.getAvgProfitAndLossForWarehouse();
			System.out.println(result);
		}
		System.out.println();	
	}
}
*/