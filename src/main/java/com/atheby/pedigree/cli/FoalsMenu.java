package com.atheby.pedigree.cli;

import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.atheby.pedigree.database.FetchObjects;
import com.atheby.pedigree.database.entity.HorseEntity;
import com.atheby.pedigree.horse.Horse;

public class FoalsMenu {
	
	HorseEntity he = new HorseEntity();
	
	public FoalsMenu() {
		System.out.println(" -- Show foals -- ");
		showFoals(displayAllHorses());
	}
	private int displayAllHorses() {
		
		ResultSet rs = he.fetchAll();
		int selected;
		
		Menu.displayResultSet(rs);
		selected = InputFormCheck.getID("Choose ID: ", rs);
		return selected;
	}
	
	private void showFoals(int id) {
		
		List<Horse> horses = FetchObjects.getHorsesList();
		Horse parent = FetchObjects.getHorse(id);
		Boolean isParent = false;
		
		for(Horse hr : horses) {
			if(hr.getDam() != null)
				if(hr.getDam().getID() == (long)id) {
					isParent = true;
					System.out.println(" ------------------------------------------------- ");
					System.out.println("\t\t" + hr.getName() + " - " + StringUtils.capitalize(hr.getSex().toString().toLowerCase()) + " - "  + hr.getDob().getDate());
					System.out.println(" ------------------------------------------------- ");
				}
			if(hr.getSire() != null)
				if(hr.getSire().getID() == (long)id) {
					isParent = true;
					System.out.println(" ------------------------------------------------- ");
					System.out.println("\t\t" + hr.getName() + " - " + StringUtils.capitalize(hr.getSex().toString().toLowerCase()) + " - "  + hr.getDob().getDate());
					System.out.println(" ------------------------------------------------- ");
				}
		}
		
		if(!isParent)
			System.out.print("\n" + parent.getName() + " doesn't have foals.\n\n");

		new MainMenu();
	}
}