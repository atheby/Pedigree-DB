package com.atheby.pedigree.horse;

import java.sql.Date;

public class DateOfBirth {
	
    private Date date;
    private Boolean yearOnly;
    
    public DateOfBirth(Date date, Boolean yearOnly) {
    	this.date = date;
    	this.yearOnly = yearOnly;
    }
    
    public void setDate(Date date, Boolean yearOnly) {
        this.date = date;
        this.yearOnly = yearOnly;
    }
    public String getDate() {
        if (yearOnly) {
            return date.toString().substring(0, 4);
        }else {
        	return date.toString();
        }
    }
}