package com.atheby.pedigree.database.entity;

import java.sql.*;

public class SexEntity extends Entity {

	@Override
	public void duplicateCheck(Object[] obj) throws DuplicateException {
	}

	@Override
	public ResultSet fetchRow(int id) {
		
		query = "select * from sex where id = " + id;
		
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet fetchAll() {
		
		query = "select * from sex";
		
		try {
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public PreparedStatement insert(Object[] obj) {
		return null;
	}

	@Override
	public PreparedStatement update(Object[] obj) {
		return null;
	}

	@Override
	public PreparedStatement delete(int id) {
		return null;
	}

}
