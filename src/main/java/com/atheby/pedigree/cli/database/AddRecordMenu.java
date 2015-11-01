package com.atheby.pedigree.cli.database;

import java.sql.ResultSet;
import java.text.*;
import java.util.Date;

import com.atheby.pedigree.cli.InputFormCheck;
import com.atheby.pedigree.cli.Menu;
import com.atheby.pedigree.cli.SelectedMenuException;
import com.atheby.pedigree.database.Database;
import com.atheby.pedigree.database.entity.*;

public class AddRecordMenu extends Menu {

	@Override
	protected void showOptions() {
		System.out.println(" -- Add record -- ");
		System.out.println("1. Horse");
		System.out.println("2. Breeder");
		System.out.println("3. Color");
		System.out.println("4. Country");
		System.out.println("5. <- Back");
	}

	@Override
	protected void selected(int sel) throws SelectedMenuException {
		try {
			if(sel < 1 || sel > 5)
				throw new SelectedMenuException();
			switch(sel){
				case 1: addHorse();
						break;
				case 2: addBreeder();
						break;
				case 3: addColor();
						break;
				case 4: addCountry();
						break;
				case 5: new DBMainMenu();
						break;
			}
		}catch(SelectedMenuException e) {
			System.out.println("\nInput number between 1 and 5!\n");
			new AddRecordMenu();
		}
	}
	
	private void addHorse() {
		
		HorseEntity he = new HorseEntity();
		BreederEntity be = new BreederEntity();
		ColorEntity cle = new ColorEntity();
		SexEntity se = new SexEntity();
		Object[] obj = new Object[8];
		String name, dateString; 
		Date dob = null;
		int sex, color, dam, sire, breeder;
		Boolean yearonly = false;
		ResultSet rs;
		
		System.out.println(" -- Add horse -- ");
		name = InputFormCheck.getString("Name: ");
		rs = se.fetchAll();
		displayResultSet(rs);
		sex = InputFormCheck.getID("Sex ID: ", rs);
		rs = cle.fetchAll();
		displayResultSet(rs);
		color = InputFormCheck.getID("Color ID: ", rs);
		rs = be.fetchAll();
		displayResultSet(rs);
		breeder = InputFormCheck.getID("Breeder ID: ", rs);
		dateString = InputFormCheck.getDob();		
		if(dateString.length() == 4) {
			dateString += "-01-01";
			yearonly = true;
		}
		try {
			dob = new SimpleDateFormat("yyyy-MM-DD").parse(dateString);
		}catch (ParseException e){
		}
		rs = he.fetchSireCandidates(dateString);
		displayResultSet(rs);
		sire = InputFormCheck.getIDwithNull("Sire ID [Enter:none]: ", rs);
		rs = he.fetchDamCandidates(dateString);
		displayResultSet(rs);
		dam = InputFormCheck.getIDwithNull("Dam ID [Enter:none]: ", rs);
		obj[0] = new String(name);
		obj[1] = new Integer(sex);
		obj[2] = new Integer(color);
		obj[3] = dob;
		obj[4] = new Boolean(yearonly);
		obj[5] = new Integer(dam);
		obj[6] = new Integer(sire);
		obj[7] = new Integer(breeder);
		
		try {
			if(Database.sendExecuteUpdate(he.insert(obj)))
				System.out.println("\nNew horse added\n");
		}catch(DuplicateException e) {
			System.out.println("\nHorse already exists in database\n");
		}
		new AddRecordMenu();
	}
	
	private void addBreeder() {

		BreederEntity be = new BreederEntity();
		CountryEntity ce = new CountryEntity();
		Object[] obj = new Object[2];
		String name;
		int codeID;
		ResultSet rs = ce.fetchAll();
		
		System.out.println(" -- Add breeder -- ");
		name = InputFormCheck.getString("Name: ");
		displayResultSet(rs);
		codeID = InputFormCheck.getID("Country ID: ", rs);
		obj[0] = new String(name);
		obj[1] = new Integer(codeID);
		try {
			Database.sendExecuteUpdate(be.insert(obj));
			System.out.println("\nNew breeder added\n");
		}catch(DuplicateException e) {
			System.out.println("\nBreeder already exists in database\n");
		}
		new AddRecordMenu();
	}
	
	private void addColor() {
		
		ColorEntity ce = new ColorEntity();
		Object[] obj = new Object[2];
		String lname, sname;
		
		System.out.println(" -- Add color -- ");
		lname = InputFormCheck.getString("Long name: ");
		sname = InputFormCheck.getString("Short name: ");
		obj[0] = new String(lname);
		obj[1] = new String(sname);
		try {
			Database.sendExecuteUpdate(ce.insert(obj));
			System.out.println("\nNew color added\n");
		}catch(DuplicateException e) {
			System.out.println("\nColor already exists in database\n");
		}
		new AddRecordMenu();
	}
	
	private void addCountry() {
		
		CountryEntity ce = new CountryEntity();
		Object[] obj = new Object[2];
		String name, code;
		
		System.out.println(" -- Add country -- ");
		name = InputFormCheck.getString("Country name: ");
		code = InputFormCheck.getCode("Country code: ");
		obj[0] = new String(name);
		obj[1] = new String(code);
		try {
			Database.sendExecuteUpdate(ce.insert(obj));
			System.out.println("\nNew country added\n");
		}catch(DuplicateException e) {
			System.out.println("\nCountry already exists in database\n");
		}
		new AddRecordMenu();
	}
}
