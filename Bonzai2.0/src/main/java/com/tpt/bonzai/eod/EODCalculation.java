package com.tpt.bonzai.eod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;

public class EODCalculation {
	
	public void  storeEOD() throws SQLException {
		DatabaseDetails dbDetails = new DatabaseDetails();
		DatabaseUtilities dbUtilities = new DatabaseUtilities();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = getAttributesForEODCalculation(dbDetails, dbUtilities, connection, preparedStatement);
		
		while(resultSet.next()) {
			int tradeId = resultSet.getInt(1);
			String commodity = resultSet.getString(2);
			double quantity = resultSet.getDouble(3);
			double tradePrice = resultSet.getDouble(4);
			String tradePriceCurrency = resultSet.getString(5);
			double marketPrice = resultSet.getDouble(6);
			String marketPriceCurrency = resultSet.getString(7);
			double profitAndLoss = profitAndLossCalculate(quantity, tradePrice, marketPrice);
			insertIntoDatabase(dbDetails,tradeId, );
		}
		/*try {
			while(resultSet.next()) {
				System.out.print(resultSet.getString(1) + "  ");
				System.out.print(resultSet.getString(2) + "  ");
				System.out.print(resultSet.getString(3) + "  ");
				System.out.print(resultSet.getString(4) + "  ");
				System.out.print(resultSet.getString(5) + "  ");
				System.out.print(resultSet.getString(6) + "  ");
				System.out.println(resultSet.getString(7) + "  ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		dbUtilities.closeResultSet(resultSet);
		dbUtilities.closeStatement(preparedStatement);
		dbUtilities.closeConnection(connection);
	}
	
	private void insertIntoDatabase(DatabaseDetails dbDetails) {
		
		String query = "INSERT IGNORE INTO end_of_day values(CURDATE(), ?, ?, ?, ?, ?, ?)";
		DatabaseUtilities dbUtilities = new DatabaseUtilities();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.execute();
			System.out.println("In try");
			
		} catch (SQLException e) {
			System.out.println("In catch");
			DatabaseUtilities.printSQLException(e);
		}
		finally {
			System.out.println("In finally");
			dbUtilities.closeStatement(preparedStatement);
			dbUtilities.closeConnection(connection);
		}
		
	}

	/*public void EODCalculation(ResultSet resultSet) {
		
		int tradeId = 0;
		double quantity = 0, tradePrice = 0, marketPrice = 0, profitNLoss = 0;
		DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.makeConnection();
				
		for(EODAttributes eodAttributes : listEODAttributes) {
			tradeId = eodAttributes.getTradeId();
			quantity = eodAttributes.getQuantity(); 
			tradePrice = eodAttributes.getTradePrice();
			marketPrice = eodAttributes.getMarketPrice();
			profitNLoss = profitNLossCalculate(quantity, tradePrice, marketPrice);
			eodAttributes.setProfitNLoss(profitNLoss);
			
			String query = "INSERT IGNORE INTO end_of_day values(CURDATE(), ?, ?, ?, ?, ?, ?)";
			String queryType = "other";
			dbHelper.queryExecute(query, queryType, String.valueOf(tradeId), "NULL", String.valueOf(quantity), 
					String.valueOf(tradePrice), String.valueOf(marketPrice), String.valueOf(profitNLoss));
		}
		
		dbHelper.closeConnection();		
	
	}*/
	
	private double profitAndLossCalculate(double quantity, double tradePrice, double marketPrice) {
		return (marketPrice-tradePrice)*quantity;
	}

	private ResultSet getAttributesForEODCalculation(DatabaseDetails dbDetails, DatabaseUtilities dbUtilities,
			Connection connection, PreparedStatement preparedStatement) {
		
		String query = "SELECT T.trade_id, T.commodity, T.quantity, T.trade_price, T.currency AS trade_price_currency,"
				+ " M.price AS market_price, M.currency AS market_price_currency from trade_information AS T "
				+ "INNER JOIN market_price AS M WHERE T.commodity = M.commodity AND M.date = CURDATE() AND "
				+ "T.maturity_date >= CURDATE() ";
		
		ResultSet resultSet = null;
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			System.out.println("In try");
			
		} catch (SQLException e) {
			System.out.println("In catch");
			DatabaseUtilities.printSQLException(e);
		}
		finally {
			System.out.println("In finally");
			return resultSet;
		}
	}
	

}
