package com.atheby.pedigree.cli.database;

import java.sql.ResultSet;

import com.atheby.pedigree.cli.InputFormCheck;
import com.atheby.pedigree.cli.Menu;
import com.atheby.pedigree.cli.SelectedMenuException;
import com.atheby.pedigree.database.Database;
import com.atheby.pedigree.database.entity.*;

public class DeleteRecordMenu extends Menu {
	
	@Override
	protected void showOptions() {
		System.out.println(" -- Delete record -- ");
		System.out.println("1. Horse");
		System.out.println("2. Breeder");
		System.out.println("3. Color");
		System.out.println("4. Country");
		System.out.println("5. <- Back");
	}

	@Override
	protected void selected(int sel) throws SelectedMenuException {
		try{
			if(sel < 1 || sel > 5)
				throw new SelectedMenuException();
			switch(sel){
				case 1: deleteHorse();
						break;
				case 2: deleteBreeder();
						break;
				case 3: deleteColor();
						break;
				case 4: deleteCountry();
						break;
				case 5: new DBMainMenu();
						break;
			}
		}catch(SelectedMenuException e) {
			System.out.println("\nInput number between 1 and 5!\n");
			new DeleteRecordMenu();
		}
	}
	
	private void deleteHorse() {
		
		HorseEntity he = new HorseEntity();
		ResultSet rs;
		int selected;
		
		System.out.println(" -- Delete horse -- ");
		rs = he.fetchAll();
		displayResultSet(rs);
		selected = InputFormCheck.getID("Horse ID: ", rs);
		Database.sendExecuteUpdate(he.setNull("horse", "sire", selected));
		Database.sendExecuteUpdate(he.setNull("horse", "dam", selected));
		if(Database.sendExecuteUpdate(he.delete(selected)))
			System.out.print("\nHorse deleted\n\n");
		else
			System.out.print("\nOperation refused\n\n");
		new DeleteRecordMenu();
	}
	
	private void deleteBreeder() {
		
		BreederEntity be = new BreederEntity();
		ResultSet rs;
		int selected;
		
		System.out.println(" -- Delete breeder -- ");
		rs = be.fetchAll();
		displayResultSet(rs);
		selected = InputFormCheck.getID("Breeder ID: ", rs);
		Database.sendExecuteUpdate(be.setNull("horse", "breeder", selected));
		if(Database.sendExecuteUpdate(be.delete(selected)))
			System.out.print("\nBreeder deleted\n\n");
		else
			System.out.print("\nOperation refused\n\n");
		new DeleteRecordMenu();
	}

	private void deleteColor() {
		
		ColorEntity ce = new ColorEntity();
		ResultSet rs;
		int selected;
		
		System.out.println(" -- Delete color -- ");
		rs = ce.fetchAll();
		displayResultSet(rs);
		selected = InputFormCheck.getID("Color ID: ", rs);
		if(Database.sendExecuteUpdate(ce.delete(selected)))
			System.out.print("\nColor deleted\n\n");
		else
			System.out.print("\nHorses exist with selected color\n\n");
		new DeleteRecordMenu();
	}

	private void deleteCountry() {
		
		CountryEntity ce = new CountryEntity();
		ResultSet rs;
		int selected;
		
		System.out.println(" -- Delete country -- ");
		rs = ce.fetchAll();
		displayResultSet(rs);
		selected = InputFormCheck.getID("Country ID: ", rs);
		Database.sendExecuteUpdate(ce.setNull("breeder", "country", selected));
		if(Database.sendExecuteUpdate(ce.delete(selected)))
			System.out.print("\nCountry deleted\n\n");
		else
			System.out.print("\nOperation refused\n\n");
		new DeleteRecordMenu();
	}
}
