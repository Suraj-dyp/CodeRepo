package com.tpt.bonzai;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHandler {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://10.101.160.203:3306/bonzai_trading_system?useSSL=false";
	private static final String USERNAME = "suraj";
	private static final String PASSWORD = "1234";
	private static Connection connection  = null;

	public DataBaseHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() {
		try {
			if(connection==null)
				connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public void getPreparedStatement() {
		try {
			//String query = "SELECT * from trade_information";
			String sql = "INSERT into trade_information values (55, \"a\", \"b\", \"c\", 100, \"Mt\", 100, \"ccxc\", ? ,\"xcxc\",\"cscs\",\"ccdc\")";
			preparedStatement = connection.prepareStatement(sql);
			Date date = new Date(new java.util.Date().getTime());
			preparedStatement.setDate(1, date);
			//pst.setInt(1, 1135);
			preparedStatement.execute();
			while(rs.next()) {
				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}*/

}
