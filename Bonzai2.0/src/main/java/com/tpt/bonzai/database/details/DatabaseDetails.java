package com.tpt.bonzai.database.details;

public final class DatabaseDetails {
	
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_NAME = "bonzai_trading_system"; 
	private final String IP = "localhost"; //10.101.160.203
	private final int PORT = 3306;
	private final String DB_URL = "jdbc:mysql://" + IP + ":" + PORT + "/"+ DB_NAME + "?useSSL=false";
	private final String USERNAME = "root"; //suraj
	private final String PASSWORD = "1234";
	
	private final String TRADE_TABLE = "trade_information";
	private final String TRANSFER_TABLE = "trade_transfer";
	private final String MARKET_TABLE = "market_price";
	private final String EOD_TABLE = "end_of_day";
	private final String COMPANY_TABLE = "company";
	private final String WAREHOUSE_TABLE = "warehouse";
	
	public String getJDBC_DRIVER() {
		return JDBC_DRIVER;
	}
	public String getDB_NAME() {
		return DB_NAME;
	}
	public String getIP() {
		return IP;
	}
	public int getPORT() {
		return PORT;
	}
	public String getDB_URL() {
		return DB_URL;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public String getTRADE_TABLE() {
		return TRADE_TABLE;
	}
	public String getTRANSFER_TABLE() {
		return TRANSFER_TABLE;
	}
	public String getMARKET_TABLE() {
		return MARKET_TABLE;
	}
	public String getEOD_TABLE() {
		return EOD_TABLE;
	}
	public String getCOMPANY_TABLE() {
		return COMPANY_TABLE;
	}
	public String getWAREHOUSE_TABLE() {
		return WAREHOUSE_TABLE;
	}
	
	
	
  

}
