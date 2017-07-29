package com.tpt.bonzai.report;

import java.util.HashSet;

import com.tpt.bonzai.pojo.ResultPOJO;

public class Report extends ReportUtility {
	private final String DATE = "EOD_DATE";
	private final String TRADE_ID = "TRADE_ID";
	private final String TRANSFER_ID = "TRANSFER_ID";
	private final String QUANTITY = "QUANTITY";
	private final String TRADE_PRICE = "TRADE_PRICE";
	private final String MARKET_PRICE = "MARKET_PRICE";
	private final String PROFIT_LOSS = "PROFIT/LOSS";
	private final String COMMODITY = "COMMODITY";
	private final String WAREHOUSE = "WAREHOUSE";
	private final String COUNTERPART = "COUNTERPART";
	private final String AVG_PROFIT_LOSS  = "AVERAGE_PROFIT/LOSS";
	
	
	public void generateReportUsingTrade(HashSet<ResultPOJO> listResultPOJO ) {
		//CSVUtility csvUtility = new CSVUtility();
		String header = DATE + " " + TRADE_ID + " " + QUANTITY + " " + TRADE_PRICE + " " + MARKET_PRICE + " " + PROFIT_LOSS;
		//csvUtility.csvWrite(header);
		System.out.println();
		System.out.println(header);
		for(ResultPOJO resultPOJO : listResultPOJO) { 
			String result = resultPOJO.getEodDate() + "\t " + resultPOJO.getTradeId() + "\t\t " +
					 "\t " + resultPOJO.getQuantity() + "\t \t" + resultPOJO.getTradePrice() + 
					 " \t" + resultPOJO.getMarketPrice() + "\t\t " + resultPOJO.getProfitAndLoss();
			//csvUtility.csvWrite(result);
			System.out.println(result);
		}
		System.out.println();
			
	}

	public void generateReportUsingTradeAndTransfer(HashSet<ResultPOJO> listResultPOJO ) {
		
		System.out.println();
		String header = DATE + " " + TRADE_ID + TRANSFER_ID + " " + QUANTITY + " " + TRADE_PRICE + " " + MARKET_PRICE + " " + PROFIT_LOSS;
		System.out.println(header);
		for(ResultPOJO resultPOJO : listResultPOJO) {
			String result = resultPOJO.getEodDate() + " \t" + resultPOJO.getTradeId() + "\t\t " +
					resultPOJO.getTransferId() + "\t " + resultPOJO.getQuantity() + "\t \t" + resultPOJO.getTradePrice() + 
					"\t " + resultPOJO.getMarketPrice() + "\t " + resultPOJO.getProfitAndLoss();
			System.out.println(result);
		}
		System.out.println();	
	}
	
	public void generateReportUsingCounterpart(HashSet<ResultPOJO> listResultPOJO ) {
		
		System.out.println();
		String header = COUNTERPART + " " + DATE + " " + TRADE_ID + TRANSFER_ID + " " + QUANTITY + " " + TRADE_PRICE + " " + MARKET_PRICE + " " + PROFIT_LOSS;
		System.out.println(header);
		for(ResultPOJO resultPOJO : listResultPOJO) {
			String result = resultPOJO.getCounterpart() + "\t " + resultPOJO.getEodDate() + " \t" + resultPOJO.getTradeId() + "\t\t " +
					resultPOJO.getTransferId() + " \t" + resultPOJO.getQuantity() + "\t \t" + resultPOJO.getTradePrice() + 
					" " + resultPOJO.getMarketPrice() + " \t" + resultPOJO.getProfitAndLoss();
			System.out.println(result);
		}
		System.out.println();	
	}
	
	public void generateReportUsingWarehouse(HashSet<ResultPOJO> listResultPOJO ) {
		
		System.out.println();
		String header = COMMODITY + " " + AVG_PROFIT_LOSS;
		System.out.println(header);
		for(ResultPOJO resultPOJO : listResultPOJO) {
			String result = resultPOJO.getCommodity() + " \t\t" + resultPOJO.getAvgProfitAndLossForWarehouse();
			System.out.println(result);
		}
		System.out.println();	
	}
}
