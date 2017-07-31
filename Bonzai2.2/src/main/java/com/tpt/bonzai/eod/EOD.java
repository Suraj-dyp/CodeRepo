package com.tpt.bonzai.eod;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;
import com.tpt.bonzai.pojo.ForexPOJO;
import com.tpt.bonzai.pojo.TradePOJO;
import com.tpt.bonzai.pojo.TransferPOJO;

public class EOD {
	private DatabaseDetails dbDetails = new DatabaseDetails();
	private DatabaseUtilities dbUtilities = new DatabaseUtilities();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private ArrayList<ForexPOJO> listForex;
	private String queryTrade = "INSERT INTO end_of_day (date, trade_id, commodity, quantity, uom, trade_price, trade_currency, "
			+ "market_price, market_currency, pnl_amount, pnl_currency, counterpart, internal_company) "
			+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String queryTransfer = "INSERT INTO end_of_day values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String queryTradeUpdate = "UPDATE end_of_day set quantity=?, pnl_amount=? where trade_id=? and transfer_id IS NULL AND date=?";
	private String queryTradeSelect = "SELECT quantity, trade_price, market_price, trade_currency, market_currency from end_of_day where trade_id = ? and transfer_id IS NULL AND date=?";
	private String queryTradeDelete = "DELETE FROM end_of_day where date = ?";
	
	
	public void setForex(Date date) {
		ForexRetrieval forexRetrieval = new ForexRetrieval();
		forexRetrieval.readForex(date);
		forexRetrieval.storeTrade();
		listForex = forexRetrieval.getListForex();
		//System.out.println(listForex);
	}
	
	public void  storeEOD(ArrayList<TradePOJO> listTrade, Date date) {
		
		deleteTrade(date);
		
		double quantity, tradePrice, marketPrice, profitAndLoss;
		String profitAndLossCurrency = "INR", tradePriceCurrency, marketPriceCurrency;		
		
		for(TradePOJO trade : listTrade) {
			quantity = trade.getQuantity();
			tradePrice = trade.getTradePrice();
			tradePriceCurrency = trade.getTradePriceCurrency();
			marketPrice = trade.getMarketPrice();
			marketPriceCurrency = trade.getMarketPriceCurrency();
			profitAndLoss = profitAndLossCalculate(quantity, tradePrice, tradePriceCurrency, marketPrice, marketPriceCurrency);
			
			try {
				connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
				preparedStatement = connection.prepareStatement(queryTrade);
				preparedStatement.setDate(1, date);
				preparedStatement.setInt(2, trade.getTradeId());
				preparedStatement.setString(3, trade.getCommodity());
				preparedStatement.setDouble(4, quantity);
				preparedStatement.setString(5, trade.getUom());
				preparedStatement.setDouble(6, tradePrice);
				preparedStatement.setString(7, tradePriceCurrency);
				preparedStatement.setDouble(8, marketPrice);
				preparedStatement.setString(9, marketPriceCurrency);
				preparedStatement.setDouble(10, profitAndLoss);
				preparedStatement.setString(11, profitAndLossCurrency);
				preparedStatement.setString(12, trade.getCounterpart());
				preparedStatement.setString(13, trade.getInternalCompany());
				preparedStatement.execute();
			} catch (SQLException e) {
				dbUtilities.printSQLException(e);
			}
			
		}
		dbUtilities.closeConnection(connection);
		
		
	}
	
	private double profitAndLossCalculate(double quantity, double tradePrice, String tradePriceCurrency, double marketPrice, String marketPriceCurrency) {
		for(ForexPOJO forex : listForex) {
			if(forex.getCurrency().equalsIgnoreCase(tradePriceCurrency))
				tradePrice = tradePrice / forex.getInrFactor();
			if(forex.getCurrency().equalsIgnoreCase(marketPriceCurrency))
				marketPrice = marketPrice / forex.getInrFactor();
		}
		return (marketPrice-tradePrice)*quantity;
	}
	
	public void  storeEOD(ArrayList<TransferPOJO> listTransfer, Date date, boolean isTransfer) {
		
		double transferQuantity, tradePrice, marketPrice, profitAndLoss;
		int tradeId;
		String profitAndLossCurrency = "INR", transferPriceCurrency, marketPriceCurrency;		
		
		for(TransferPOJO transfer : listTransfer) {
			tradeId = transfer.getTradeId();
			transferQuantity = transfer.getQuantity();
			tradePrice = transfer.getTradePrice();
			transferPriceCurrency = transfer.getTradePriceCurrency();
			marketPrice = transfer.getMarketPrice();
			marketPriceCurrency = transfer.getMarketPriceCurrency();
			profitAndLoss = profitAndLossCalculate(transferQuantity, tradePrice, transferPriceCurrency, marketPrice, marketPriceCurrency);
			
			try {
				connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
				preparedStatement = connection.prepareStatement(queryTransfer);
				preparedStatement.setDate(1, date);
				preparedStatement.setInt(2, tradeId);
				preparedStatement.setInt(3, transfer.getTransferId());
				preparedStatement.setString(4, transfer.getCommodity());
				preparedStatement.setDouble(5, transferQuantity);
				preparedStatement.setString(6, transfer.getUom());
				preparedStatement.setDouble(7, tradePrice);
				preparedStatement.setString(8, transfer.getTradePriceCurrency());
				preparedStatement.setDouble(9, marketPrice);
				preparedStatement.setString(10, transfer.getMarketPriceCurrency());
				preparedStatement.setDouble(11, profitAndLoss);
				preparedStatement.setString(12, profitAndLossCurrency);
				preparedStatement.setString(13, transfer.getStorage());
				preparedStatement.setString(14, transfer.getCounterpart());
				preparedStatement.setString(15, transfer.getInternalCompany());
				preparedStatement.execute();
				
				updateTrade(tradeId, transferQuantity, date);
			} catch (SQLException e) {
				dbUtilities.printSQLException(e);
			}
			
		}
		
		dbUtilities.closeConnection(connection);
	}

	private void updateTrade(int tradeId, double transferQuantity, Date date) {
		double tradePrice = 0, tradeQuantity = 0, marketPrice = 0, profitAndLoss;
		String tradePriceCurrency = null, marketPriceCurrency = null;
		Connection connection = null;
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			PreparedStatement preparedStatement = connection.prepareStatement(queryTradeSelect);
			preparedStatement.setInt(1, tradeId);
			preparedStatement.setDate(2, date);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				tradeQuantity = resultSet.getDouble(1);
				tradePrice = resultSet.getDouble(2);
				marketPrice = resultSet.getDouble(3);
				tradePriceCurrency = resultSet.getString(4);
				marketPriceCurrency = resultSet.getString(5);
			}
			tradeQuantity = tradeQuantity - transferQuantity;
			profitAndLoss = profitAndLossCalculate(tradeQuantity, tradePrice, tradePriceCurrency, marketPrice, marketPriceCurrency);
			
			preparedStatement = connection.prepareStatement(queryTradeUpdate);
			preparedStatement.setDouble(1, tradeQuantity);
			preparedStatement.setDouble(2, profitAndLoss);
			preparedStatement.setInt(3, tradeId);
			preparedStatement.setDate(4, date);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			dbUtilities.printSQLException(e);
		}
		finally {
			dbUtilities.closeConnection(connection);
		}
		
	}
	

	private void deleteTrade(Date date) {
		Connection connection = null;
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			PreparedStatement preparedStatement = connection.prepareStatement(queryTradeDelete);
			preparedStatement.setDate(1, date);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			dbUtilities.printSQLException(e);
		}
		
		dbUtilities.closeConnection(connection);
	}



}
