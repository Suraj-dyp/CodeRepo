package com.tpt.bonzai.eod;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tpt.bonzai.database.details.DatabaseDetails;
import com.tpt.bonzai.database.utilities.DatabaseUtilities;
import com.tpt.bonzai.pojo.ForexPOJO;
import com.tpt.bonzai.pojo.TradePOJO;

public class ForexRetrieval {
	
	ArrayList<ForexPOJO> listForex = new ArrayList<>();
	String query = "SELECT F.currency, F.inr_factor from foreign_exchange AS F WHERE F.date=?";
			
	private DatabaseDetails dbDetails = new DatabaseDetails();
	private DatabaseUtilities dbUtilities = new DatabaseUtilities();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public void readForex(Date date) {
		try {
			connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDate(1, date);
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
				String currency = resultSet.getString(1);
				double inrFactor = resultSet.getDouble(2);
				ForexPOJO forex = new ForexPOJO(currency, inrFactor);
				listForex.add(forex);
			}
		} catch (SQLException e) {
			dbUtilities.printSQLException(e);
		}
		
	}

	public ArrayList<ForexPOJO> getListForex() {
		return listForex;
	}

}
