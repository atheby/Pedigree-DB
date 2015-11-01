package com.atheby.pedigree.cli;

import java.sql.ResultSet;
import java.util.*;

import com.atheby.pedigree.Pedigree;
import com.atheby.pedigree.database.FetchObjects;
import com.atheby.pedigree.database.entity.HorseEntity;
import com.atheby.pedigree.horse.Horse;

public class PedigreeMenu {
	
	public PedigreeMenu() {
		System.out.println(" -- Show pedigree -- ");
		int horse = displayAllHorses();
		int depth = InputFormCheck.getDepth("Depth: ");
		showPedigree(horse, depth);
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
	
	private void showPedigree(int id, int depth) {
		
		Map<Integer, List<Horse>> pedigree = Pedigree.getPedigree(FetchObjects.getHorse(id), depth);
		List<Horse> currentGen = new ArrayList<Horse>();
		List<Horse> nextGen = new ArrayList<Horse>();
		String tabs = "";
		Boolean steps = false;
		
		for(int d = 0; d <= depth; d++) {
			currentGen = pedigree.get(d);
			if(d < depth)
				nextGen = pedigree.get(d + 1);
			for(Horse hr : currentGen) {
				if(hr != null) {
					System.out.print(hr.getName() + " ");
				}
			}
			if(nextGen != null)
				for(Horse next : nextGen) {
					if(next != null && d < depth)
						steps = true;
				}
			if(steps) {
				System.out.print("\n" + tabs + "|");
				System.out.print("\n" + tabs + "|______ ");
				steps = false;
				nextGen = null;
			}
			tabs += "\t";
		}
		System.out.print("\n\n");
	}
}
