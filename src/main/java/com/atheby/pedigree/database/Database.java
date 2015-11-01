package com.atheby.pedigree.database;

import java.sql.*;

import com.atheby.pedigree.cli.MainMenu;

public class Database {
	
	protected static Connection con;
	protected PreparedStatement st;
	
	public Database() {
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/pedigree","sa","");
		}catch(SQLException e) {
			System.out.println("\n ---- Database error! ---- \n");
			new MainMenu();
		}
	}
	
	public Database(int i) {
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:mem:TestPedigree","sa","");
		}catch(SQLException e){
			System.out.println("\n ---- Database error! ---- \n");
		}
	}
	
	public static Boolean sendExecuteUpdate(PreparedStatement st) {
		try {
			st.executeUpdate();
			return true;
		}catch(SQLException e) {
			System.out.println("\nError executing query!\n");
		}
		return false;
	}
}

