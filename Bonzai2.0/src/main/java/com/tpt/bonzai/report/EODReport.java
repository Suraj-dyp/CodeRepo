package com.tpt.bonzai.report;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tpt.bonzai.csv.CSVHandler;
import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;

public class EODReport extends ReportUtility{
	
	private DatabaseDetails dbDetails = new DatabaseDetails();
	private DatabaseUtilities dbUtilities = new DatabaseUtilities();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	String query = "SELECT E.date AS COB_DATE, E.trade_id AS TRADE_ID, E.transfer_id AS TRANSFER_ID, E.storage AS WAREHOUSE, E.counterpart AS COUNTERPART,"
			+ " E.internal_company AS INTERNAL_COMPANY, E.commodity AS COMMODITY, E.quantity AS QUANTITY, E.uom AS UNIT_OF_MEASUREMENT, "
			+ " E.trade_price AS TRADE_PRICE, E.trade_currency AS TRADE_CURRENCY, E.market_price AS MARKET_PRICE, "  
			+ " E.market_currency AS MARKET_CURRENCY, E.pnl_amount AS PROFIT_AND_LOSS, E.pnl_currency AS PROFIT_AND_LOSS_CURRENCY FROM end_of_day AS E WHERE E.date=? ";
			
	
	public void generateReport(Date date) {
		
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDate(1, date);
			resultSet = preparedStatement.executeQuery();
			
			CSVHandler csvHandler = new CSVHandler();
			csvHandler.csvWrite(getFilePath(), resultSet);	//"C:\\Users\\bwbss\\Desktop\\EOD.csv"
			//displayReport(resultSet, 15);
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
