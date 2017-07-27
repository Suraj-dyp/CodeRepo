package com.tpt.bonzai.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author suraj.kumar
 *
 */
public class DatabaseHelper {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://10.101.160.203:3306/bonzai_trading_system?useSSL=false";
	private static final String USERNAME = "suraj";
	private static final String PASSWORD = "1234";
	private static final String TRADE_TABLE = "trade_information";
	private static final String TRANSFER_TABLE = "trade_transfer";
	private static final String MARKET_TABLE = "market_price";
	private static final String EOD_TABLE = "end_of_day";
	private static final String SELECT = "select";
	private static final String DELETE = "delete";
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	
	public DatabaseHelper() {
		
	}
	
	
	public void makeConnection() {
		try {
			if(connection==null)
				connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public Connection getConnection() {
		return connection;
	}
	
	public ResultSet getResultSet() {
		return resultSet;
	}
	
	
	public void queryExecute(String query, String queryType, String ...parameters) {
		//System.out.println(parameters.length);
		try {
			preparedStatement = connection.prepareStatement(query);
			String regexNumber = "[+-]*[0-9]+";
			String regexNull = "NULL";
			String regexDate = "[0-9]+-[0-9]+-[0-9]+";
			int index = 0;
			for(String parameter : parameters ) {
				index++;
				if(parameter.matches(regexNumber)) {
					preparedStatement.setDouble(index, Double.parseDouble(parameter));
				}
				else if(parameter.matches(regexNull)) {
					preparedStatement.setNull(index, java.sql.Types.NULL);
				}
				else if(parameter.matches(regexDate)){
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			        java.util.Date myDate = null;
					try {
						myDate = format.parse(parameter);
					} catch (ParseException e) {
						e.printStackTrace();
					}
			        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			        preparedStatement.setDate(index, sqlDate);
				}
				else {
					preparedStatement.setString(index, parameter);
				}
					
			}
			if(queryType.equalsIgnoreCase(SELECT)) {
				resultSet = preparedStatement.executeQuery();
			}
			else if(queryType.equalsIgnoreCase(DELETE)) {
				preparedStatement.executeUpdate();
			}
			else {
				preparedStatement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


}
