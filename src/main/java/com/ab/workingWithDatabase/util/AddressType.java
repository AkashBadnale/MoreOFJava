package com.ab.workingWithDatabase.util;

public enum AddressType {

	TEMPORARY("T"), 
	PERMANENT("P");
	
	private final String type;

    private AddressType(String type) {
        this.type = type;
    }
    
    public String getAddressType() {
        return this.type;
    }
}
