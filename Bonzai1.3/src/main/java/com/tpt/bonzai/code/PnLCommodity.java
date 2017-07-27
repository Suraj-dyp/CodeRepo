package com.tpt.bonzai.code;

import java.sql.ResultSet;

public class PnLCommodity {
	
	public static void profitNLossForCommodities() {
		String query = "SELECT T.commodity, AVG(EOD.pnl_amount) as sum_pnl from end_of_day as EOD inner join trade_information as T"
				+ " where EOD.trade_id = T.trade_id GROUP BY T.commodity";
		String queryType = "select";
		
		DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.makeConnection();
		dbHelper.queryExecute(query, queryType);
		ResultSet resultSet = dbHelper.getResultSet();
		
		
		//displayProfitNLossForCommodities(resultSet);
		
		final String commodityFilePath = "C:\\Users\\suraj.kumar\\Desktop\\commodity.csv";
		CSVHandler.csvWrite(commodityFilePath, resultSet);
		
		dbHelper.closeConnection();
	}


}
