package com.atheby.pedigree.database.entity;

@SuppressWarnings("serial")
public class DuplicateException extends IllegalArgumentException {
	
	public DuplicateException() {
		super("The entry already exists in the table");
	}
}
