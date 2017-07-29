package com.tpt.bonzai.eod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;
import com.tpt.bonzai.pojo.CobPOJO;

public class CobPOJOCreation {
	
	
	private HashSet<CobPOJO> listCObPOJO = new HashSet<>();
	private DatabaseDetails dbDetails = new DatabaseDetails();
	private String queryWithoutTransfer = "SELECT T.trade_id, T.commodity, T.quantity, T.trade_price, T.currency AS trade_price_currency,"
			+ " M.price AS market_price, M.currency AS market_price_currency from trade_information AS T "
			+ "INNER JOIN market_price AS M WHERE T.commodity = M.commodity AND M.date = CURDATE() AND "
			+ "T.maturity_date >= CURDATE() ";
	private String queryWithTransfer = "SELECT T.transfer_id, T.trade_id, T.commodity, T.quantity, T.transfer_price, " + 
			"	T.currency as transfer_price_currency, M.price as market_price, " + 
			"	M.currency as market_price_currency from trade_transfer AS T " + 
			"	INNER JOIN market_price AS M " + 
			"	WHERE T.commodity = M.commodity AND M.date = CURDATE() " + 
			"	AND T.transfer_date >= CURDATE()";
	
	public HashSet<CobPOJO> getListCObPOJO() {
		return listCObPOJO;
	}

	public void setPOJOForEODCalculation() {
		DatabaseUtilities dbUtilities = new DatabaseUtilities();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(queryWithoutTransfer);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				int tradeId = resultSet.getInt(1);
				String commodity = resultSet.getString(2);
				double quantity = resultSet.getDouble(3);
				double tradePrice = resultSet.getDouble(4);
				String tradePriceCurrency = resultSet.getString(5);
				double marketPrice = resultSet.getDouble(6);
				String marketPriceCurrency = resultSet.getString(7);
				CobPOJO cobPOJO = new CobPOJO(tradeId, commodity, quantity, tradePrice, tradePriceCurrency, marketPrice, marketPriceCurrency);
				listCObPOJO.add(cobPOJO);
			}			
			//System.out.println("In try");
			//System.out.println(listCObPOJO.size());
			
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
	
	public void setPOJOForEODCalculation(boolean transfer) {
		DatabaseUtilities dbUtilities = new DatabaseUtilities();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(queryWithTransfer);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int transferId = resultSet.getInt(1);
				int tradeId = resultSet.getInt(2);
				String commodity = resultSet.getString(3);
				double quantity = resultSet.getDouble(4);
				double tradePrice = resultSet.getDouble(5);
				String tradePriceCurrency = resultSet.getString(6);
				double marketPrice = resultSet.getDouble(7);
				String marketPriceCurrency = resultSet.getString(8);
				CobPOJO cobPOJO = new CobPOJO(transferId, tradeId, commodity, quantity, tradePrice, tradePriceCurrency, marketPrice, marketPriceCurrency);
				listCObPOJO.add(cobPOJO);
			}
			//System.out.println("In try");
			//System.out.println(listCObPOJO.size());
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
