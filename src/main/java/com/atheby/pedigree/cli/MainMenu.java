package com.atheby.pedigree.cli;

import com.atheby.pedigree.cli.database.DBMainMenu;

public class MainMenu extends Menu {
	
	@Override
	protected void showOptions() {
		System.out.println(" -- Main menu -- ");
		System.out.println("1. Show pedigree");
		System.out.println("2. Search foals");
		System.out.println("3. Generate PDF");
		System.out.println("4. Edit database");
	}
	
	@Override
	protected void selected(int sel) throws SelectedMenuException {
		try {
			if(sel < 1 || sel > 4)
				throw new SelectedMenuException();
			switch(sel) {
				case 1: new PedigreeMenu();
						break;
				case 2: new FoalsMenu();
						break;
				case 3: new PdfMenu();
						break;
				case 4: new DBMainMenu();
						break;
			}
		}catch(SelectedMenuException e) {
			System.out.println("\nInput number between 1 and 4!\n");
			new MainMenu();
		}
	}
}
