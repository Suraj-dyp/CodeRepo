package com.tpt.bonzai.code;

import java.sql.ResultSet;

public class PnLCommodity {
	
	public static void profitNLossForCommodities() {
		String query = "SELECT T.commodity, sum(EOD.pnl_amount) as sum_pnl from end_of_day as EOD inner join trade_information as T"
				+ " where EOD.trade_id = T.trade_id GROUP BY T.commodity";
		String queryType = "select";
		
		DatabaseHelper.makeConnection();
		DatabaseHelper.queryExecute(query, queryType);
		ResultSet resultSet = DatabaseHelper.getResultSet();
		
		//displayProfitNLossForCommodities(resultSet);
		
		final String commodityFilePath = "C:\\Users\\bwbss\\Desktop\\commodity.csv";
		CSVHandler.csvWrite(commodityFilePath, resultSet);
		
	}


}
