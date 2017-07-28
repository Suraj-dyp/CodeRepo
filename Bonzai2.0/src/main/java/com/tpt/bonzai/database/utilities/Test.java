package com.tpt.bonzai.database.utilities;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.*;
import com.tpt.bonzai.database.details.DatabaseDetails;

public class Test{
	
	/*public static void main(String[] args) throws SQLException {
		DatabaseDetails dbDetails = new DatabaseDetails();
		DatabaseUtilities dbUtilities = new DatabaseUtilities();
		Connection connection = dbUtilities.getConnection(dbDetails.getDB_URL(), dbDetails.getUSERNAME(), dbDetails.getPASSWORD());
		PreparedStatement ps=connection.prepareStatement("select price from market_price");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
	}*/
	
}