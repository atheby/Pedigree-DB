package com.atheby.pedigree.cli.database;

import com.atheby.pedigree.cli.MainMenu;
import com.atheby.pedigree.cli.Menu;
import com.atheby.pedigree.cli.SelectedMenuException;

public class DBMainMenu extends Menu {
	
	@Override
	protected void showOptions() {
		System.out.println(" -- Manage database -- ");
		System.out.println("1. Add record");
		System.out.println("2. Update record");
		System.out.println("3. Delete record");
		System.out.println("4. <- Back");
	}
	
	@Override
	protected void selected(int sel) throws SelectedMenuException {
		try {
			if(sel < 1 || sel > 4)
				throw new SelectedMenuException();
			switch(sel){
				case 1: new AddRecordMenu();
					break;
				case 2: new UpdateRecordMenu();
					break;
				case 3: new DeleteRecordMenu();
					break;
				case 4: new MainMenu();
					break;
			}
		}catch(SelectedMenuException e) {
			System.out.println("\nInput number between 1 and 4!\n");
			new DBMainMenu();
		}
	}
}
