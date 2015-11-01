package com.atheby.pedigree.cli;

import java.util.InputMismatchException;

@SuppressWarnings("serial")
public class SelectedMenuException extends InputMismatchException {
	
	public SelectedMenuException() {
		super("The selected menu is out of range");
	}
}
