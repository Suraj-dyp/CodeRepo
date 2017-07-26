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
	
	public static void retrieveDataForEodCalculate() {
		final String queryType = "select";
		String query = "SELECT T.trade_id, T.commodity, T.quantity, T.trade_price, T.currency as trade_price_currency, M.price as market_price, M.currency as market_price_currency from trade_information AS T INNER JOIN market_price AS M where T.commodity = M.commodity AND M.date = CURDATE() AND T.maturity_date >= CURDATE() ";
		
		DatabaseHelper.makeConnection();
		DatabaseHelper.queryExecute(query, queryType);
		ResultSet resultSet = DatabaseHelper.getResultSet();
		
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
			e.printStackTrace();
		}*/
		
		ArrayList<AttributesForCalculationOfEOD> listEOD = new ArrayList<>();
		try {
			while(resultSet.next()) {
				int tradeId = resultSet.getInt(1);
				String commodity = resultSet.getString(2);
				double quantity = resultSet.getDouble(3);
				double tradePrice = resultSet.getDouble(4);
				String tradePriceCurrency = resultSet.getString(5);
				double marketPrice = resultSet.getDouble(6);
				String marketPriceCurrency = resultSet.getString(7);
				AttributesForCalculationOfEOD obj = new AttributesForCalculationOfEOD(tradeId, commodity, quantity,
						tradePrice, tradePriceCurrency, marketPrice, marketPriceCurrency);
				listEOD.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(listEOD.size());
		
	}

}
