package com.atheby.pedigree.cli;

import java.sql.*;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

public abstract class Menu {
	
	static Scanner sc;
	
	protected Menu() {
		showOptions();
		selected(checkSelected());
	}
	
	protected abstract void showOptions();
	protected abstract void selected(int sel) throws SelectedMenuException;
	
	public int checkSelected() {
		
		sc = new Scanner(System.in);
		
		try {
			int sel = sc.nextInt();
			return sel;
		}catch(InputMismatchException e) {
			return 0;
		}
	}
	
	protected static void displayResultSet(ResultSet rs) {
		try {
			System.out.print("\n[ ");
			int i = 1;
			while(rs.next()) {
				if(i % 15 == 0)
					System.out.println(rs.getInt("id") + ":" + StringUtils.capitalize(rs.getString("name").toLowerCase()) + "; ");
				else
					System.out.print(rs.getInt("id") + ":" + StringUtils.capitalize(rs.getString("name").toLowerCase()) + "; ");
				i++;
			}
			System.out.print("]\n");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
