/**
 * 
 */
package com.tpt.bonzai.code;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author suraj.kumar
 *
 */
public class EODCalculation {
	
	static ArrayList<EODAttributes> listEODAttributes = new ArrayList<>();
	public static void retrieveEODAttributes() {
		String query = "SELECT T.trade_id, T.commodity, T.quantity, T.trade_price, T.currency AS trade_price_currency,"
				+ " M.price AS market_price, M.currency AS market_price_currency from trade_information AS T "
				+ "INNER JOIN market_price AS M WHERE T.commodity = M.commodity AND M.date = CURDATE() AND "
				+ "T.maturity_date >= CURDATE() ";
		
		final String queryType = "select";
		DatabaseHelper.makeConnection();
		DatabaseHelper.queryExecute(query, queryType);
		ResultSet resultSet = DatabaseHelper.getResultSet();
		
		//displayEODAttributes(resultSet);
		
		storeEODAttributes(resultSet);
		
		/*for(EODAttributes eodAttributes : listEODAttributes)
			System.out.println(eodAttributes);*/
		
	}
	
	public static void insertEODAttributesInEODTable() {
		
		
		int tradeId = 0;
		double quantity = 0, tradePrice = 0, marketPrice = 0, profitNLoss = 0;
		DatabaseHelper.makeConnection();
		for(EODAttributes eodAttributes : listEODAttributes) {
			tradeId = eodAttributes.getTradeId();
			quantity = eodAttributes.getQuantity(); 
			tradePrice = eodAttributes.getTradePrice();
			marketPrice = eodAttributes.getMarketPrice();
			profitNLoss = profitNLossCalculate(quantity, tradePrice, marketPrice);
			eodAttributes.setProfitNLoss(profitNLoss);
			
			String query = "INSERT IGNORE INTO end_of_day values(CURDATE(), ?, ?, ?, ?, ?, ?)";
			String queryType = "other";
			DatabaseHelper.queryExecute(query, queryType, String.valueOf(tradeId), "NULL", String.valueOf(quantity), 
					String.valueOf(tradePrice), String.valueOf(marketPrice), String.valueOf(profitNLoss));
		}
		
				
	
	}

	private static double profitNLossCalculate(double quantity, double tradePrice, double marketPrice) {
		double profitNLoss;
		profitNLoss = (marketPrice-tradePrice)*quantity;
		return profitNLoss;
	}
	
	


	private static void storeEODAttributes(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				int tradeId = resultSet.getInt(1);
				String commodity = resultSet.getString(2);
				double quantity = resultSet.getDouble(3);
				double tradePrice = resultSet.getDouble(4);
				String tradePriceCurrency = resultSet.getString(5);
				double marketPrice = resultSet.getDouble(6);
				String marketPriceCurrency = resultSet.getString(7);
				EODAttributes eodAttributes = new EODAttributes(tradeId, commodity, quantity,
						tradePrice, tradePriceCurrency, marketPrice, marketPriceCurrency);
				listEODAttributes.add(eodAttributes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
