package com.tpt.bonzai.code;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EODCalculationWithTransfer {
	
	static ArrayList<EODAttributes> listEODAttributes = new ArrayList<>();
	public static void retrieveEODAttributes() {
		String query = "SELECT T.transfer_id, T.trade_id, T.commodity, T.quantity, T.transfer_price, "
				+ "T.currency as transfer_price_currency, M.price as market_price, " 
				+ "M.currency as market_price_currency from trade_transfer_sk AS T " 
				+"INNER JOIN market_price AS M INNER JOIN trade_information as TI "
				+ "WHERE TI.trade_id = T.trade_id and T.commodity = M.commodity AND M.date = CURDATE()"
				+ "AND TI.maturity_date >= CURDATE() ";
		
		final String queryType = "select";
		DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.makeConnection();
		dbHelper.queryExecute(query, queryType);
		ResultSet resultSet = dbHelper.getResultSet();
		
		
		//displayEODAttributes(resultSet);
		
		storeEODAttributes(resultSet);
		
		/*for(EODAttributes eodAttributes : listEODAttributes)
			System.out.println(eodAttributes);*/
		
		//DatabaseHelper.closeConnection();
	}
	
	public static void insertEODAttributesInEODTable() {
		
		
		int transferId = 0, tradeId = 0;
		double quantity = 0, tradePrice = 0, marketPrice = 0, profitNLoss = 0;
		DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.makeConnection();
		for(EODAttributes eodAttributes : listEODAttributes) {
			transferId = eodAttributes.getTransferId();
			tradeId = eodAttributes.getTradeId();
			quantity = eodAttributes.getQuantity(); 
			tradePrice = eodAttributes.getTradePrice();
			marketPrice = eodAttributes.getMarketPrice();
			profitNLoss = profitNLossCalculate(quantity, tradePrice, marketPrice);
			eodAttributes.setProfitNLoss(profitNLoss);
			
			String query = "INSERT IGNORE INTO end_of_day values(CURDATE(), ?, ?, ?, ?, ?, ?)";
			String queryType = "other";
			dbHelper.queryExecute(query, queryType, String.valueOf(tradeId), String.valueOf(transferId), String.valueOf(quantity), 
					String.valueOf(tradePrice), String.valueOf(marketPrice), String.valueOf(profitNLoss));
		}
		
		dbHelper.closeConnection();	
	
	}

	private static double profitNLossCalculate(double quantity, double tradePrice, double marketPrice) {
		double profitNLoss;
		profitNLoss = (marketPrice-tradePrice)*quantity;
		return profitNLoss;
	}
	
	


	private static void storeEODAttributes(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				int transferId = resultSet.getInt(1);
				int tradeId = resultSet.getInt(2);
				String commodity = resultSet.getString(3);
				double quantity = resultSet.getDouble(4);
				double tradePrice = resultSet.getDouble(5);
				String tradePriceCurrency = resultSet.getString(6);
				double marketPrice = resultSet.getDouble(7);
				String marketPriceCurrency = resultSet.getString(8);
				EODAttributes eodAttributes = new EODAttributes(transferId, tradeId, commodity, quantity,
						tradePrice, tradePriceCurrency, marketPrice, marketPriceCurrency);
				listEODAttributes.add(eodAttributes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
