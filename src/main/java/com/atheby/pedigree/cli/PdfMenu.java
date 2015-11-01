package com.atheby.pedigree.cli;

import java.sql.ResultSet;

import com.atheby.pedigree.Pdf;
import com.atheby.pedigree.Pedigree;
import com.atheby.pedigree.database.FetchObjects;
import com.atheby.pedigree.database.entity.HorseEntity;

public class PdfMenu {
	
	public PdfMenu() {
		System.out.println(" -- Generate PDF -- ");
		int horse = displayAllHorses();
		int depth = InputFormCheck.getDepth("Depth: ");
		Pdf.generatePdf(Pedigree.getPedigree(FetchObjects.getHorse(horse), depth));
		new MainMenu();
	}
	private int displayAllHorses() {
		
		HorseEntity he = new HorseEntity();
		ResultSet rs = he.fetchAll();
		int selected;
		
		Menu.displayResultSet(rs);
		selected = InputFormCheck.getID("Choose ID: ", rs);
		return selected;
	}
}
