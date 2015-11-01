package com.atheby.pedigree.database.entity;

import java.sql.*;

public class CountryEntity extends Entity {
	
	@Override
	public void duplicateCheck(Object[] obj) throws DuplicateException {
		
		query = "select name from country where lower(name) = lower('" + obj[0] + "') or lower(code) = lower('" + obj[1] + "')";
		
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
			if(rs.next())
				throw new DuplicateException();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ResultSet fetchRow(int id) {
		
		query = "select * from country where id = " + id;
		
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
		
		query = "select * from country";
		
		try {
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 *  @param <p><b>Object[] = {String.name, String.code}</b></p>
	 */
	@Override
	public PreparedStatement insert(Object[] obj) {
		
			duplicateCheck(obj);
			query = "insert into country (name, code) values (?, ?)";
			
			try {
				st = con.prepareStatement(query);
				st.setObject(1, obj[0]);
				st.setObject(2, obj[1]);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return st;
	}

	/**
	 *  @param <p><b>Object[] = {String.name, String.code, Int.id}</b></p>
	 */
	@Override
	public PreparedStatement update(Object[] obj) {
		
		duplicateCheck(obj);
		query = "update country set name = ?, code = ? where id = ?";
		
		try{
			st = con.prepareStatement(query);
			st.setObject(1, obj[0]);
			st.setObject(2, obj[1]);
			st.setObject(3, obj[2]);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st;
	}

	@Override
	public PreparedStatement delete(int i) {
		
		query = "delete from country where id = ?";
		
		try {
			st = con.prepareStatement(query);
			st.setInt(1, i);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
}
