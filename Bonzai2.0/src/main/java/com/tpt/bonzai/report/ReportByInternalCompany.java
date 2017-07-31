package com.tpt.bonzai.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tpt.bonzai.csv.CSVHandler;
import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;

public class ReportByInternalCompany extends ReportUtility{
	
	private DatabaseDetails dbDetails = new DatabaseDetails();
	private DatabaseUtilities dbUtilities = new DatabaseUtilities();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	String query = "SELECT E.date AS COB_DATE, E.internal_company AS INTERNAL_COMPANY, sum(E.quantity) AS QUANTITY, E.uom AS UNIT_OF_MEASUREMENT, sum(E.pnl_amount) AS PROFIT_lOSS, E.pnl_currency AS PROFIT_LOSS_CURRENCY "
			+ "FROM end_of_day AS E where E.date = ? GROUP BY E.internal_company";
	
	
	public void generateReport() {
		
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDate(1, getSQLDate(getDate()));
			resultSet = preparedStatement.executeQuery();
			CSVHandler csvHandler = new CSVHandler();
			csvHandler.csvWrite(getFilePath(), resultSet);

			//displayReport(resultSet, 6);
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
