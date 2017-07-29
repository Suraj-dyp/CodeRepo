package com.tpt.bonzai.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;
import com.tpt.bonzai.pojo.ResultPOJO;

public class ResultUsingCounterpartPOJOCreation {
	
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
		
		String query = "SELECT EOD.date, T.counterpart, EOD.trade_id, EOD.transfer_id, EOD.quantity,EOD.trade_price, EOD.market_price, EOD.pnl_amount from end_of_day as EOD inner join "
				+ "trade_information as T where EOD.trade_id = T.trade_id and T.counterpart = ? and EOD.date >= ? and EOD.date <= ?";
		
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, reportUtility.getCounterpartName());
			preparedStatement.setDate(2, reportUtility.getSQLDate(reportUtility.getDate("start")));
			preparedStatement.setDate(3, reportUtility.getSQLDate(reportUtility.getDate("end")));
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ResultPOJO resultPOJO = new ResultPOJO(resultSet.getInt(3), resultSet.getDouble(5), resultSet.getDouble(6), 
						resultSet.getDouble(7), resultSet.getDouble(8), resultSet.getString(1), resultSet.getInt(4), resultSet.getString(2));
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
