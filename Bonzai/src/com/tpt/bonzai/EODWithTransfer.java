package com.tpt.bonzai;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EODWithTransfer {
	
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	public EODWithTransfer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void insertEodDataWithTransferInDatabase() {
		double profitNLoss;
		int tradeId, transferId;
		double quantity, trade_price, marketPrice;
		try {
			while(resultSet.next()) {
				transferId = resultSet.getInt(1);
				tradeId = resultSet.getInt(2);
				quantity = resultSet.getDouble(4);
				trade_price = resultSet.getDouble(5);
				marketPrice = resultSet.getDouble(7);
				profitNLoss = profitNLossCalculate();
				/*System.out.println(profitNLoss);
				System.out.println(transferId);*/
				String sqlEODStorage = "INSERT INTO end_of_day values(CURDATE(), ?, ?, ?, ?, ?, ?)";

				preparedStatement = DataBaseHandler.getConnection().prepareStatement(sqlEODStorage);
				preparedStatement.setInt(1, tradeId);
				preparedStatement.setInt(2, transferId);
				preparedStatement.setDouble(3, quantity);
				preparedStatement.setDouble(4, trade_price);
				preparedStatement.setDouble(5, marketPrice);
				preparedStatement.setDouble(6, profitNLoss);
				preparedStatement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
	
	}


	private static double profitNLossCalculate() throws SQLException {
		double profitNLoss;
		double quantity;
		double tradePrice;
		double marketPrice;
		quantity = resultSet.getDouble(4);
		tradePrice = resultSet.getDouble(5);
		marketPrice = resultSet.getDouble(7);
		profitNLoss = (marketPrice-tradePrice)*quantity;
		return profitNLoss;
	}
	
	public static void retrieveDataForEodCalculate() {
		String sqlEOD = "SELECT T.transfer_id, T.trade_id, T.commodity, T.quantity, T.transfer_price, T.currency as transfer_price_currency, M.price as market_price, \r\n" + 
				"M.currency as market_price_currency from trade_transfer_sk AS T \r\n" + 
				"INNER JOIN market_price AS M INNER JOIN trade_information as TI\r\n" + 
				"where TI.trade_id = T.trade_id and T.commodity = M.commodity AND M.date = CURDATE();";
		try {
			preparedStatement = DataBaseHandler.getConnection().prepareStatement(sqlEOD);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
