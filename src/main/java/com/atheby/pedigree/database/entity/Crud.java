package com.atheby.pedigree.database.entity;

import java.sql.*;

public interface Crud{
	
	public abstract void duplicateCheck(Object[] obj) throws DuplicateException;
	
	public abstract ResultSet fetchRow(int id);
	
	public abstract ResultSet fetchAll();
	
	public abstract PreparedStatement insert(Object[] obj);
	
	public abstract PreparedStatement update(Object[] obj);
	
	public abstract PreparedStatement delete(int id);
	
	public abstract PreparedStatement setNull(String table, String column, int id);
}