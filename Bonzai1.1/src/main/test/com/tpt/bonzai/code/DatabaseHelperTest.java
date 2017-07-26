package com.tpt.bonzai.code;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class DatabaseHelperTest {

	ResultSet rsExpected;
	ResultSet rsActual;
	String query = "select * from test where d=?";
	String queryType = "SELECT";
	String name = "Suraj";
	
	@Before
	public void makeConnection() {
		DatabaseHelper.makeConnection();
	}
	
	@Test
	public final void testGetConnection() {
		Connection connection = DatabaseHelper.getConnection();
		assertNotNull(connection);
	}

	@Test
	public final void testQueryExecute() {
		
		DatabaseHelper.queryExecute(query, queryType, name);
		rsActual = DatabaseHelper.getResultSet();
		
		rsExpected = getExpectedResultSet();
		
		assertResultSet();
		
		
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
			PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(query);
			preparedStatement.setString(1, name);
			rsExpected = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rsExpected;
	}

}
