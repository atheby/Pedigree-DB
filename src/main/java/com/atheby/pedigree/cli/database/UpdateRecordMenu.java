package com.atheby.pedigree.cli.database;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.atheby.pedigree.cli.InputFormCheck;
import com.atheby.pedigree.cli.Menu;
import com.atheby.pedigree.cli.SelectedMenuException;
import com.atheby.pedigree.database.Database;
import com.atheby.pedigree.database.entity.*;

public class UpdateRecordMenu extends Menu {

	@Override
	protected void showOptions() {
		System.out.println(" -- Update record -- ");
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
			switch(sel) {
				case 1: updateHorse();
						break;
				case 2: updateBreeder();
						break;
				case 3: updateColor();
						break;
				case 4: updateCountry();
						break;
				case 5: new DBMainMenu();
						break;
			}
		}catch(SelectedMenuException e) {
			System.out.println("\nInput number between 1 and 5!\n");
			new UpdateRecordMenu();
		}
	}

	private void updateHorse() {
		
		HorseEntity he = new HorseEntity();
		BreederEntity be = new BreederEntity();
		ColorEntity cle = new ColorEntity();
		SexEntity se = new SexEntity();
		Object[] obj = new Object[9];
		String name, dateString; 
		Date dob = null;
		int sex, color, dam, sire, breeder, selected;
		Boolean yearonly = false;
		ResultSet rs;
		
		System.out.println(" -- Update horse -- ");
		rs = he.fetchAll();
		displayResultSet(rs);
		selected = InputFormCheck.getID("Horse ID: ", rs);
		name = InputFormCheck.getString("Horse name: ");
		rs = se.fetchAll();
		displayResultSet(rs);
		sex = InputFormCheck.getID("Sex: ", rs);
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
		}catch (ParseException e) {
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
		obj[8] = new Integer(selected);
		try {
			Database.sendExecuteUpdate(he.update(obj));
			System.out.println("\nHorse updated\n");
		}catch(DuplicateException e) {
			System.out.println("\nHorse already exists in database\n");
		}
		new UpdateRecordMenu();
	}
	
	private void updateBreeder() {
		
		BreederEntity be = new BreederEntity();
		CountryEntity ce = new CountryEntity();
		ResultSet rs;
		int selected, country;
		Object[] obj = new Object[3];
		String name;
		
		System.out.println(" -- Update breeder -- ");
		rs = be.fetchAll();
		displayResultSet(rs);
		selected = InputFormCheck.getID("Breeder ID: ", rs);
		name = InputFormCheck.getString("Breeder name: ");
		rs = ce.fetchAll();
		displayResultSet(rs);
		country = InputFormCheck.getID("Country: ", rs);
		obj[0] = new String(name);
		obj[1] = new Integer(country);
		obj[2] = new Integer(selected);
		try {
			Database.sendExecuteUpdate(be.update(obj));
			System.out.println("\nBreeder updated\n");
		}catch(DuplicateException e) {
			System.out.println("\nBreeder already exists in database\n");
		}
		new UpdateRecordMenu();
	}
	
	private void updateColor() {
		
		ColorEntity ce = new ColorEntity();
		ResultSet rs;
		int selected;
		Object[] obj = new Object[3];
		String lname, sname;
		
		System.out.println(" -- Update color -- ");
		rs = ce.fetchAll();
		displayResultSet(rs);
		selected = InputFormCheck.getID("Color ID: ", rs);
		lname = InputFormCheck.getString("Long name: ");
		sname = InputFormCheck.getString("Short name: ");
		obj[0] = new String(lname);
		obj[1] = new String(sname);
		obj[2] = new Integer(selected);
		try {
			Database.sendExecuteUpdate(ce.update(obj));
			System.out.println("\nColor updated\n");
		}catch(DuplicateException e) {
			System.out.println("\nColor already exists in database\n");
		}
		new UpdateRecordMenu();
	}
	
	private void updateCountry() {
		
		CountryEntity ce = new CountryEntity();
		ResultSet rs;
		int selected;
		Object[] obj = new Object[3];
		String name, code;
		
		System.out.println(" -- Update country -- ");
		rs = ce.fetchAll();
		displayResultSet(rs);
		selected = InputFormCheck.getID("Country ID: ", rs);
		name = InputFormCheck.getString("Country name: ");
		code = InputFormCheck.getCode("Country code: ");
		obj[0] = new String(name);
		obj[1] = new String(code);
		obj[2] = new Integer(selected);
		try {
			Database.sendExecuteUpdate(ce.update(obj));
			System.out.println("\nCountry updated\n");
		}catch(DuplicateException e) {
			System.out.println("\nCountry already exists in database\n");
		}
		new UpdateRecordMenu();
	}
}
