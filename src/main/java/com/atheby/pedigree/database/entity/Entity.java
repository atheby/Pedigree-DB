package com.atheby.pedigree.database.entity;

import java.sql.*;

import com.atheby.pedigree.database.Database;

abstract class Entity extends Database implements Crud {
	
	protected static String query;
	protected static ResultSet rs;
	
	@Override
	public PreparedStatement setNull(String table, String column, int id) {
		
		query = "update " + table + " set " + column +  " = null where " + column + " = ?";
		
		try {
			st = con.prepareStatement(query);
			st.setInt(1, id);
		}catch(SQLException e) {
			System.out.println("\nError setting to null");
		}
		return st;
	}
}