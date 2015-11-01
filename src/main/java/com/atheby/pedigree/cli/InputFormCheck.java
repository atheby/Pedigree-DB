package com.atheby.pedigree.cli;

import java.sql.*;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

public class InputFormCheck {
	
	static Scanner sc;
	
	public static String getString(String name) {
		
		String s = null;
		sc = new Scanner(System.in);
		
		do {
			System.out.print(name);
			try {
				s = sc.nextLine();
				s.trim();
			}catch(InputMismatchException e) {
			}
		}
		while(!s.matches("[a-zA-Z]*\\s*[a-zA-Z]*"));
		
		return StringUtils.capitalize(s.toLowerCase());
	}
	
	public static int getDepth(String name) {
		
		int c = 0;
		String s = null;
		sc = new Scanner(System.in);
		
		try {
			do {
				System.out.print(name);
				s = sc.nextLine();
			}while(!s.matches("[1-6]"));
			c = Integer.parseInt(s);
		}catch(InputMismatchException e) {
		}
		return c;
	}
	
	public static String getCode(String name) {
		
		String s = null;
		sc = new Scanner(System.in);
		
		do {
			System.out.print(name);
			try {
				s = sc.nextLine();
				s.trim();
				if(!s.matches("[a-zA-Z]{2}"))
					System.out.println("Two letters required!");
			}catch(InputMismatchException e) {
			}
		}
		while(!s.matches("[a-zA-Z]{2}"));
		
		return s.toUpperCase();
	}
	
	public static int getID(String name, ResultSet rs) {
		
		int c = 0;
		String s = null;
		Boolean match = false;
		sc = new Scanner(System.in);
		
		do {
			try {
				do {
					System.out.print(name);
					s = sc.nextLine();
				}while(!s.matches("[0-9]{1,3}"));
				c = Integer.parseInt(s);
				rs.beforeFirst();
				while(rs.next()) {
					if(rs.getInt("id") == c)
						match = true;
				}
			}catch(InputMismatchException | SQLException e) {
			}
		}while(!match);
		
		return c;
	}
	
	public static int getIDwithNull(String name, ResultSet rs) {
		
		int c = 0;
		String s = null;
		Boolean match = false;
		sc = new Scanner(System.in);
		
		do {
			try {
				do {
					System.out.print(name);
					s = sc.nextLine();
				}while(!s.matches("([0-9]{1,3})|^$"));
				if(!s.equals("")) {
				c = Integer.parseInt(s);
				rs.beforeFirst();
				while(rs.next()) {
					if(rs.getInt("id") == c)
						match = true;
				}
				}
				else
					return -1;
			}catch(InputMismatchException | SQLException e) {
			}
		}while(!match);
		
		return c;
	}
	
	public static String getDob() {
		String s = null;
		sc = new Scanner(System.in);
		
		try {
			do {
				System.out.print("Date of birth [YYYY; YYYY-MM-DD]: ");
				s = sc.nextLine();
			}while(!s.matches("((19|20)[0-9]{2})|((19|20)[0-9]{2})[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])"));

		}catch(InputMismatchException e) {
		}
		
		return s;
	}
}
