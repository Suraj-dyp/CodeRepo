package com.tpt.bonzai.code;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class DatabaseHelperTest {

	DatabaseHelper dbHelper = new DatabaseHelper();
	ResultSet rsExpected;
	ResultSet rsActual;
	String query = "select * from test where d=?";
	String queryType = "SELECT";
	String name = "Suraj";
	
	@Before
	public void makeConnection() {
		
		dbHelper.makeConnection();
	}
	
	@Test
	public final void testGetConnection() {
		Connection connection = dbHelper.getConnection();
		assertNotNull(connection);
	}

	@Test
	public final void testQueryExecute() {
		
		dbHelper.queryExecute(query, queryType, name);
		rsActual = dbHelper.getResultSet();
		
		rsExpected = getExpectedResultSet();
		
		assertResultSet();
		
		dbHelper.closeConnection();
		
		
	}

	private void assertResultSet() {
		try {
			while(rsActual.next() && rsExpected.next()) {
				boolean test = rsActual.getInt(1)==rsExpected.getInt(1)
						&& rsActual.getDouble(2) == rsExpected.getDouble(2)
						&& rsActual.getDate(3).equals(rsExpected.getDate(3))
						&& rsActual.getString(4).equalsIgnoreCase(rsExpected.getString(4));
				assertTrue(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ResultSet getExpectedResultSet() {
		try {
			PreparedStatement preparedStatement = dbHelper.getConnection().prepareStatement(query);
			preparedStatement.setString(1, name);
			rsExpected = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rsExpected;
	}

}
