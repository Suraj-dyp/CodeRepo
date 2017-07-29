package com.tpt.bonzai.eod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;
import com.tpt.bonzai.pojo.CobPOJO;

public class COB {
	
	public void runEOD(HashSet<CobPOJO> listCobPOJO) {
		
		DatabaseDetails dbDetails = new DatabaseDetails();
		for(CobPOJO cobPOJO : listCobPOJO) {
			
			int tradeId = cobPOJO.getTradeId();
			String commodity = cobPOJO.getCommodity();
			double quantity = cobPOJO.getQuantity();
			double tradePrice = cobPOJO.getTradePrice();
			double marketPrice = cobPOJO.getMarketPrice();
			double profitAndLoss = profitAndLossCalculate(quantity, tradePrice, marketPrice);
			cobPOJO.setProfitNLoss(profitAndLoss);
			insertIntoDatabase(dbDetails,tradeId, java.sql.Types.NULL, commodity, quantity, tradePrice,marketPrice,profitAndLoss, false);
		}
			
	}
	
	public void  runEOD(HashSet<CobPOJO> listCobPOJO, boolean transfer) {
		
		DatabaseDetails dbDetails = new DatabaseDetails();
		for(CobPOJO cobPOJO : listCobPOJO) {
			int transferId = cobPOJO.getTransferId();
			int tradeId = cobPOJO.getTradeId();
			String commodity = cobPOJO.getCommodity();
			double quantity = cobPOJO.getQuantity();
			double tradePrice = cobPOJO.getTradePrice();
			double marketPrice = cobPOJO.getMarketPrice();
			double profitAndLoss = profitAndLossCalculate(quantity, tradePrice, marketPrice);
			cobPOJO.setProfitNLoss(profitAndLoss);
			insertIntoDatabase(dbDetails,tradeId, transferId, commodity, quantity, tradePrice,marketPrice,profitAndLoss, transfer);
		}
		
	}
	
	private void insertIntoDatabase(DatabaseDetails dbDetails, int tradeId, int transferId, String commodity, double quantity, double tradePrice, double marketPrice, double profitAndLoss, boolean transfer) {
		
		DatabaseUtilities dbUtilities = new DatabaseUtilities();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT IGNORE INTO end_of_day values(CURDATE(), ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, tradeId);
			if(transfer)
				preparedStatement.setInt(2, transferId);
			else
				preparedStatement.setNull(2, transferId);
			//preparedStatement.setString(3, commodity);
			preparedStatement.setDouble(3, quantity);
			preparedStatement.setDouble(4, tradePrice);
			preparedStatement.setDouble(5, marketPrice);
			preparedStatement.setDouble(6, profitAndLoss);
			preparedStatement.execute();
			//System.out.println("In try");
			
		} catch (SQLException e) {
			//System.out.println("In catch");
			dbUtilities.printSQLException(e);
		}
		finally {
			//System.out.println("In finally");
			dbUtilities.closeStatement(preparedStatement);
			dbUtilities.closeConnection(connection);
		}
		
	}

	
	private double profitAndLossCalculate(double quantity, double tradePrice, double marketPrice) {
		return (marketPrice-tradePrice)*quantity;
	}

	
}
