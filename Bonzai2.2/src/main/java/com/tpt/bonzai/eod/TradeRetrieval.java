package com.tpt.bonzai.eod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;
import com.tpt.bonzai.pojo.TradePOJO;

public class TradeRetrieval {

	private ArrayList<TradePOJO> listTrades = new ArrayList<>();
	private DatabaseDetails dbDetails = new DatabaseDetails();
	private DatabaseUtilities dbUtilities = new DatabaseUtilities();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String query = "SELECT T.trade_id, T.counterpart, T.internal_company, T.commodity, T.quantity, T.uom, T.trade_price, T.currency " + 
			"AS trade_curency,M.price AS market_price, M.currency AS market_currency " + 
			"from trade_information AS T INNER JOIN market_price AS M " + 
			"where T.trade_date<='2017-08-10' AND M.date='2017-08-10' AND T.commodity=M.commodity AND T.completion_status=\"Complete\"";
	
	public void readTrade() {
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			//System.out.println("In try");
		} catch (SQLException e) {
			//System.out.println("In catch");
			dbUtilities.printSQLException(e);
		}
	}
	
	public void storeTrade() {
		try {
			while(resultSet.next()) {
				int tradeId = resultSet.getInt(1);
				String counterpart = resultSet.getString(2);
				String internalCompany = resultSet.getString(3);
				String commodity = resultSet.getString(4);
				double quantity = resultSet.getDouble(5);
				String uom = resultSet.getString(6);
				double tradePrice = resultSet.getDouble(7);
				String tradePriceCurrency = resultSet.getString(8);
				double marketPrice = resultSet.getDouble(9);
				String marketPriceCurrency = resultSet.getString(10);
				TradePOJO trade = new TradePOJO(tradeId, counterpart, internalCompany, commodity, quantity, uom,
						tradePrice, tradePriceCurrency, marketPrice, marketPriceCurrency);
				listTrades.add(trade);
			}
		} catch (SQLException e) {
			dbUtilities.printSQLException(e);
		}
		
	}

	public ArrayList<TradePOJO> getListTrades() {
		return listTrades;
	}

	public void displayTrade() {
		try {
			while(resultSet.next()) {
				System.out.print(resultSet.getString(1) + "  ");
				System.out.print(resultSet.getString(2) + "  ");
				System.out.print(resultSet.getString(3) + "  ");
				System.out.print(resultSet.getString(4) + "  ");
				System.out.print(resultSet.getString(5) + "  ");
				System.out.print(resultSet.getString(6) + "  ");
				System.out.print(resultSet.getString(7) + "  ");
				System.out.print(resultSet.getString(8) + "  ");
				System.out.print(resultSet.getString(9) + "  ");
				System.out.println(resultSet.getString(10) + "  ");
			}
		} catch (SQLException e) {
			dbUtilities.printSQLException(e);
		}
	}
	
	public void displayListTrades() {
		for(TradePOJO trade : listTrades)
			System.out.println(trade);
	}
}
