package com.atheby.pedigree.database.entity;

import java.sql.*;

public class ColorEntity extends Entity {
	
	@Override
	public void duplicateCheck(Object[] obj) {
		
		query = "select * from color where lower(lname) = lower('" + obj[0] + "') or lower(sname) = lower('" + obj[1] + "')";
		
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
		
		query = "select * from color where id = " + id;
		
		try{
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	@Override
	public ResultSet fetchAll() {
		
		query = "select id, lname as name, sname from color";
		
		try {
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 *  @param <p><b>Object[] = {String.lname, String.sname}</b></p>
	 */
	@Override
	public PreparedStatement insert(Object[] obj) {
		
			duplicateCheck(obj);
			query = "insert into color (lname, sname) values (?, ?)";
			
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
	 *  @param <p><b>Object[] = {String.lname, String.sname, Int.id}</b></p>
	 */
	@Override
	public PreparedStatement update(Object[] obj) {
		
		duplicateCheck(obj);
		query = "update color set lname = ?, sname = ? where id = ?";
		
		try {
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
		
		query = "delete from color where id = ?";
		
		try {
			st = con.prepareStatement(query);
			st.setInt(1, i);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
}
