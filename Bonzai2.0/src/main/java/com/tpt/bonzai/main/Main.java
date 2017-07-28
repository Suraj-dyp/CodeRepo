package com.tpt.bonzai.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tpt.bonzai.eod.EODCalculation;

public class Main{
	
	public static void main(String[] args) throws SQLException {
		EODCalculation eodCalculation = new EODCalculation();
		eodCalculation.storeEOD();
		/*while(resultSet.next()) {
			System.out.print(resultSet.getString(1) + "  ");
			System.out.print(resultSet.getString(2) + "  ");
			System.out.print(resultSet.getString(3) + "  ");
			System.out.print(resultSet.getString(4) + "  ");
			System.out.print(resultSet.getString(5) + "  ");
			System.out.print(resultSet.getString(6) + "  ");
			System.out.println(resultSet.getString(7) + "  ");
		}
		if(resultSet!=null) {resultSet.close();}
		//if(preparedStatement!=null) {preparedStatement.close();}
*/		
		
	}
	
}