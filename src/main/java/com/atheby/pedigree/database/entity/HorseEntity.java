package com.atheby.pedigree.database.entity;

import java.sql.*;

public class HorseEntity extends Entity {
	
	@Override
	public void duplicateCheck(Object[] obj) {
		
		query = "select name from horse where name = '" + obj[0] + "' and breeder = " + obj[7];
		
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
			if(rs.next())
				throw new DuplicateException();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void duplicateCheckUpdate(Object[] obj) {
		
		query = "select name from horse where name = '" + obj[0] + "' and breeder = " + obj[7] + " and id <> " + obj[8];
		
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
		
		query = "select * from horse where id = " + id;
		
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
		
		query = "select * from horse";
		
		try {
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet fetchSireCandidates(String date) {
		
		query = "select * from horse where sex = 0 and year(to_date('" + date + "', 'YYYY-MM-DD')) - year(dob) > 3 and year(to_date('" + date + "', 'YYYY-MM-DD')) - year(dob) < 30";
		
		try {
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet fetchDamCandidates(String date) {
		
		query = "select * from horse where sex = 1 and year(to_date('" + date + "', 'YYYY-MM-DD')) - year(dob) > 2 and year(to_date('" + date + "', 'YYYY-MM-DD')) - year(dob) < 30";
		
		try {
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet fetchFoals(int id) {
		
		query = "select * from horse where dam = " + id + " or sire = " + id;
		
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 *  @param <p><b>Object[] = {String.name, Int.sex, Int.color, Date.dob, Bool.yearonly, Int.dam, Int.sire, Int.breeder}</b></p>
	 */
	@Override
	public PreparedStatement insert(Object[] obj) {
		
			duplicateCheck(obj);
			query = "insert into horse (name, sex, color, dob, yearonly, dam, sire, breeder) values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			try{
				if(obj[5].equals(new Integer(-1)))
					obj[5] = null;
				if(obj[6].equals(new Integer(-1)))
					obj[6] = null;
				st = con.prepareStatement(query);
				st.setObject(1, obj[0]);
				st.setObject(2, obj[1]);
				st.setObject(3, obj[2]);
				st.setObject(4, obj[3]);
				st.setObject(5, obj[4]);
				st.setObject(6, obj[5]);
				st.setObject(7, obj[6]);
				st.setObject(8, obj[7]);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return st;
	}

	/**
	 *  @param <p><b>Object[] = {String.name, Int.sex, Int.color, Date.dob, Bool.yearonly, Int.dam, Int.sire, Int.breeder, Int.id}</b></p>
	 */
	@Override
	public PreparedStatement update(Object[] obj) {
		
		duplicateCheck(obj);
		query = "update horse set name = ?, sex = ? , color = ?, dob = ?, yearonly = ?, dam = ?, sire = ?, breeder = ? where id = ?";
		
		try {
			if(obj[5].equals(new Integer(-1)))
				obj[5] = null;
			if(obj[6].equals(new Integer(-1)))
				obj[6] = null;
			st = con.prepareStatement(query);
			st.setObject(1, obj[0]);
			st.setObject(2, obj[1]);
			st.setObject(3, obj[2]);
			st.setObject(4, obj[3]);
			st.setObject(5, obj[4]);
			st.setObject(6, obj[5]);
			st.setObject(7, obj[6]);
			st.setObject(8, obj[7]);
			st.setObject(9, obj[8]);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st;
	}

	@Override
	public PreparedStatement delete(int i) {
		
		query = "delete from horse where id = ?";
		
		try {
			st = con.prepareStatement(query);
			st.setInt(1, i);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
}
