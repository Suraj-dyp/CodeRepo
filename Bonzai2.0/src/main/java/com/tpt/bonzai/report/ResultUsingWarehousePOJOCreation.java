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
//public class ResultUsingWarehousePOJOCreation {
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
//		String query = "SELECT T.commodity, SUM(EOD.pnl_amount) as sum_pnl from end_of_day as EOD INNER JOIN warehouse as W inner join trade_transfer T where "
//				+ "EOD.trade_id = T.trade_id and T.storage_id = W.storgage_id and W.storage= ? group by T.commodity";
//		
//		try {
//			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, reportUtility.getWarehouseName());
//			resultSet = preparedStatement.executeQuery();
//			while(resultSet.next()) {
//				CobPOJO resultPOJO = new CobPOJO(resultSet.getString(1), resultSet.getDouble(2));
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
