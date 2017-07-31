package com.tpt.bonzai.database.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtilities {
	
	public void closeConnection(Connection connection) {
	    try {
	      if (connection != null) {
	        connection.close();
	      }
	    } catch (SQLException sqle) {
	      printSQLException(sqle);
	    }
	  }
	
	public void closeStatement(PreparedStatement preparedStatement) {
	    try {
	      if (preparedStatement != null) {
	        preparedStatement.close();
	      }
	    } catch (SQLException sqle) {
	      printSQLException(sqle);
	    }
	  }
	
	public void closeResultSet(ResultSet resultSet) {
	    try {
	      if (resultSet != null) {
	        resultSet.close();
	      }
	    } catch (SQLException sqle) {
	      printSQLException(sqle);
	    }
	  }
	
	public Connection getConnection(String dbURL, String userName, String password) throws SQLException {
		Connection connection = DriverManager.getConnection(dbURL, userName, password);
		return connection;
	}
	
	public  void printSQLException(SQLException ex) {
	    for (Throwable e : ex) {
	      if (e instanceof SQLException) {
	        if (ignoreSQLException(((SQLException)e).getSQLState()) == false) {
	          e.printStackTrace(System.err);
	          System.err.println("SQLState: " + ((SQLException)e).getSQLState());
	          System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
	          System.err.println("Message: " + e.getMessage());
	          Throwable t = ex.getCause();
	          while (t != null) {
	            System.out.println("Cause: " + t);
	            t = t.getCause();
	          }
	        }
	      }
	    }
	  }
	
	public  boolean ignoreSQLException(String sqlState) {
		boolean ignore = false;
	    if (sqlState == null) {
	      System.out.println("The SQL state is not defined!");
	      ignore = false;
	    }
	    if (sqlState.equalsIgnoreCase("X0Y32"))
	      ignore = true;
	    return ignore;
	  }
	

}
