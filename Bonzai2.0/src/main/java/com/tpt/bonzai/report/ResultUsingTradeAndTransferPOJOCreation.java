package com.tpt.bonzai.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;
import com.tpt.bonzai.pojo.ResultPOJO;

public class ResultUsingTradeAndTransferPOJOCreation {
	
	private HashSet<ResultPOJO> listResultPOJO = new HashSet<>();
	private DatabaseDetails dbDetails = new DatabaseDetails();
	ReportUtility reportUtility = new ReportUtility();
	
	public HashSet<ResultPOJO> getListResultPOJO() {
		return listResultPOJO;
	}
	
	public void setResultPOJO() {
		
		DatabaseUtilities dbUtilities = new DatabaseUtilities();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
		
		String query = "SELECT date, trade_id, transfer_id, quantity, trade_price, market_price, pnl_amount from end_of_day where transfer_id "
				+ "=? AND trade_id = ? AND date >= ? AND date <= ?";
		
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, reportUtility.getTransferId());
			preparedStatement.setInt(2, reportUtility.getTradeId());
			preparedStatement.setDate(3, reportUtility.getSQLDate(reportUtility.getDate("start")));
			preparedStatement.setDate(4, reportUtility.getSQLDate(reportUtility.getDate("end")));
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ResultPOJO resultPOJO = new ResultPOJO(resultSet.getInt(2), resultSet.getDouble(4), resultSet.getDouble(5), 
						resultSet.getDouble(6), resultSet.getDouble(7), resultSet.getString(1), resultSet.getInt(3));
				listResultPOJO.add(resultPOJO);
			}
			
			
			//System.out.println("In try");
			
		} catch (SQLException e) {
			//System.out.println("In catch");
			dbUtilities.printSQLException(e);
		}
		finally {
			//System.out.println("In finally");
			dbUtilities.closeResultSet(resultSet);
			dbUtilities.closeStatement(preparedStatement);
			dbUtilities.closeConnection(connection);
		}
		
	}

}
