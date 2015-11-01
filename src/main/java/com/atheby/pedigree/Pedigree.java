package com.atheby.pedigree;

import java.util.*;

import com.atheby.pedigree.horse.Horse;

public class Pedigree {
	
	public static Map<Integer, List<Horse>> getPedigree(Horse horse, int depth) {
		
		Map<Integer, List<Horse>> pedigree = new HashMap<Integer, List<Horse>>();
		
		pedigree.put(0, new ArrayList<Horse>(Arrays.asList(horse)));
		
    	for(int i = 0; i <= depth; i++) {
    		List<Horse> currentGen = pedigree.get(i);
    		List<Horse> nextGen = new ArrayList<Horse>();
    		for(int h = 0; h < currentGen.size(); h++) {
    			if(currentGen.get(h) == null) {
    				nextGen.add(null);
    				nextGen.add(null);
    			}
    			else {
    				if(currentGen.get(h).getSire() == null)
    					nextGen.add(null);
    				else
    					nextGen.add(currentGen.get(h).getSire());
    				if(currentGen.get(h).getDam() == null)
    					nextGen.add(null);
    				else
    					nextGen.add(currentGen.get(h).getDam());
    			}
    					
    		}
    		pedigree.put(i + 1, nextGen);	
    	}
    	pedigree.remove(depth + 1);
    	return pedigree;
	}
}

