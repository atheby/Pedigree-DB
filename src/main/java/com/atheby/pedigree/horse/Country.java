package com.atheby.pedigree.horse;

public class Country {

    private long id;
    private String name;
    private String code;

    public Country(long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
    public long getID() {
    	return id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

