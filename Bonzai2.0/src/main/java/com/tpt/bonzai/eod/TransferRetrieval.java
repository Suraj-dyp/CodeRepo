package com.tpt.bonzai.eod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;
import com.tpt.bonzai.pojo.TradePOJO;
import com.tpt.bonzai.pojo.TransferPOJO;

public class TransferRetrieval {
	
	private ArrayList<TransferPOJO> listTransfers = new ArrayList<>();
	private DatabaseDetails dbDetails = new DatabaseDetails();
	private DatabaseUtilities dbUtilities = new DatabaseUtilities();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String query = "SELECT T.trade_id, T.counterpart, T.internal_company, TR.transfer_id, TR.commodity, TR.quantity, TR.uom, TR.transfer_type, " + 
			"TR.transfer_price, TR.currency AS transfer_curency, M.price AS market_price, M.currency AS market_currency, W.storage " + 
			"from trade_information AS T INNER JOIN market_price AS M INNER JOIN trade_transfer AS TR INNER JOIN warehouse AS W " + 
			"where W.storage_id=TR.storage_id AND T.trade_date<='2017-08-10' AND TR.transfer_date<='2017-08-10' AND M.date='2017-08-10' AND TR.commodity=M.commodity " + 
			"AND T.completion_status=\"Complete\" AND T.trade_id=TR.trade_id";
	
	public void readTransfer() {
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
	
	public void storeTransfer() {
		try {
			while(resultSet.next()) {
				int tradeId = resultSet.getInt(1);
				String counterpart = resultSet.getString(2);
				String internalCompany = resultSet.getString(3);
				int transferId =resultSet.getInt(4);
				String commodity = resultSet.getString(5);
				double quantity = resultSet.getDouble(6);
				String uom = resultSet.getString(7);
				String transferType = resultSet.getString(8);
				double tradePrice = resultSet.getDouble(9);
				String tradePriceCurrency = resultSet.getString(10);
				double marketPrice = resultSet.getDouble(11);
				String marketPriceCurrency = resultSet.getString(12);
				String storage = resultSet.getString(13);
				TransferPOJO transfer = new TransferPOJO(tradeId, transferId, transferType, counterpart, internalCompany, commodity,
						quantity, uom, tradePrice, tradePriceCurrency, marketPrice, marketPriceCurrency, storage);
				listTransfers.add(transfer);
			}
		} catch (SQLException e) {
			dbUtilities.printSQLException(e);
		}
		

		
	}

	public void displayTransfer() {
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
				System.out.print(resultSet.getString(10) + "  ");
				System.out.print(resultSet.getString(11) + "  ");
				System.out.println(resultSet.getString(10) + "  ");
			}
		} catch (SQLException e) {
			dbUtilities.printSQLException(e);
		}
	}
	
	public void displayListTransfers() {
		for(TransferPOJO transfer : listTransfers)
			System.out.println(transfer);
	}

	public ArrayList<TransferPOJO> getListTransfers() {
		return listTransfers;
	}

}
