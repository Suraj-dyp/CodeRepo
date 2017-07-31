package com.tpt.bonzai.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tpt.bonzai.csv.CSVHandler;
import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;

public class ReportByTradeId extends ReportUtility{
	private DatabaseDetails dbDetails = new DatabaseDetails();
	private DatabaseUtilities dbUtilities = new DatabaseUtilities();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	String query = "SELECT E.date AS COB_DATE, E.trade_id AS TRADE_ID, E.transfer_id AS TRANSFER_ID, E.quantity AS QUANTITY, E.uom AS UNIT_OF_MEASUREMENT, E.trade_price AS TRADE_PRICE, E.trade_currency AS TRADE_CURRENCY, E.market_price AS MARKET_PRICE,"
			+ " E.market_currency AS MARKET_CURRENCY, E.pnl_amount AS PROFIT_LOSS, E.pnl_currency AS PROFIT_LOSS_CURRENCY FROM end_of_day AS E where E.trade_id=? AND E.date=?";
	
	
	public void generateReport(int tradeId) {
		
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, tradeId);
			preparedStatement.setDate(2, getSQLDate(getDate()));
			resultSet = preparedStatement.executeQuery();
			CSVHandler csvHandler = new CSVHandler();
			csvHandler.csvWrite(getFilePath(), resultSet);

			//displayReport(resultSet, 10);
		} catch (SQLException e) {
			dbUtilities.printSQLException(e);
		}
		finally {
			dbUtilities.closeResultSet(resultSet);
			dbUtilities.closeStatement(preparedStatement);
			dbUtilities.closeConnection(connection);
		}
	}
	
	
}