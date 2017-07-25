package com.tpt.bonzai;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EOD {
	

	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	public EOD() {
		// TODO Auto-generated constructor stub
	}
	
	public static void insertEodDataInDatabase() {
		double profitNLoss;
		int tradeId;
		try {
			while(resultSet.next()) {
				tradeId = resultSet.getInt(1);
				profitNLoss = profitNLossCalculate();
				//System.out.println(profitNLoss);
				String sqlEODStorage = "INSERT INTO end_of_day values(CURDATE(), ?, ?, ?)";

				preparedStatement = DataBaseHandler.getConnection().prepareStatement(sqlEODStorage);
				preparedStatement.setInt(1, tradeId);
				preparedStatement.setNull(2, java.sql.Types.NULL);
				preparedStatement.setDouble(3, profitNLoss);
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
		quantity = resultSet.getDouble(3);
		tradePrice = resultSet.getDouble(4);
		marketPrice = resultSet.getDouble(6);
		profitNLoss = (marketPrice-tradePrice)*quantity;
		return profitNLoss;
	}
	
	public static void retrieveDataForEodCalculate() {
		String sqlEOD = "SELECT T.trade_id, T.commodity, T.quantity, T.trade_price, T.currency as trade_price_currency, M.price as market_price, M.currency as market_price_currency from trade_information AS T INNER JOIN market_price AS M where T.commodity = M.commodity AND M.date = CURDATE() ";
		try {
			preparedStatement = DataBaseHandler.getConnection().prepareStatement(sqlEOD);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*private static void printRetrievedDataForEodCalculate() throws SQLException {
			while(resultSet.next()) {
				System.out.print(resultSet.getString(1) + "  ");
				System.out.print(resultSet.getString(2) + "  ");
				System.out.print(resultSet.getString(3) + "  ");
				System.out.print(resultSet.getString(4) + "  ");
				System.out.print(resultSet.getString(5) + "  ");
				System.out.print(resultSet.getString(6) + "  ");
				System.out.println(resultSet.getString(7) + "  ");
			}
		
	}*/


}
