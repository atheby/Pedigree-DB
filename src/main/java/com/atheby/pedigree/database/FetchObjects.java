package com.atheby.pedigree.database;

import java.sql.*;
import java.util.*;

import com.atheby.pedigree.database.entity.*;
import com.atheby.pedigree.horse.*;

public class FetchObjects {
		
	private static HorseEntity he = new HorseEntity();
	private static CountryEntity ce = new CountryEntity();
	private static SexEntity se = new SexEntity();
	private static ColorEntity cle = new ColorEntity();
	private static BreederEntity be = new BreederEntity();

	public static Horse getHorse(int id) {
		
		ResultSet rs = he.fetchRow(id);
		
		try {
			while(rs.next() ) {
				return new Horse((long)rs.getInt("id"), rs.getString("name"), getSex(rs.getInt("sex")), getDOB(rs.getInt("id")), getColor(rs.getInt("color")),
						getHorse(rs.getInt("sire")), getHorse(rs.getInt("dam")), getBreeder(rs.getInt("breeder")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Horse> getHorsesList() {
		
		ResultSet rs = he.fetchAll();
		List<Horse> horses = new ArrayList<Horse>();
		
		try {
			while(rs.next()) {
				horses.add(new Horse((long)rs.getInt("id"), rs.getString("name"), getSex(rs.getInt("sex")), getDOB(rs.getInt("id")), getColor(rs.getInt("color")),
							getHorse(rs.getInt("sire")), getHorse(rs.getInt("dam")), getBreeder(rs.getInt("breeder"))));
			}
			return horses;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Breeder getBreeder(int id) {
		
		ResultSet rs = be.fetchRow(id);
		
		try {
			while(rs.next()) {
				return new Breeder((long)rs.getInt("id"), rs.getString("name"), getCountry(rs.getInt("country")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Color getColor(int id) {
		
		ResultSet rs = cle.fetchRow(id);
		
		try {
			while(rs.next()) {
				return new Color((long)rs.getInt("id"), rs.getString("lname"), rs.getString("sname"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static DateOfBirth getDOB(int id) {
		
		ResultSet rs = he.fetchRow(id);
		
		try{
			while(rs.next()) {
				return new DateOfBirth(rs.getDate("dob"), rs.getBoolean("yearonly"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Sex getSex(int id) {
		
		ResultSet rs = se.fetchRow(id);
		
		try{
			while(rs.next()) {
				return Sex.valueOf(rs.getString("name").toUpperCase());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Country getCountry(int id) {
		
		ResultSet rs = ce.fetchRow(id);
		
		try{
			while(rs.next()) {
				return new Country((long)rs.getInt("id"), rs.getString("name"), rs.getString("code"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}