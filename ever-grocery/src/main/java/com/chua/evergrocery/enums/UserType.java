package com.chua.evergrocery.enums;

public enum UserType {

	ADMINISTRATOR ("Administrator"),
	
	MANAGER ("Manager"),
	
	ASSISTANT_MANAGER ("Assistant Manager"),
	
	CASHIER ("Cashier"),
	
	SENIOR_STAFF ("Senior Staff"),
	
	STAFF ("Staff"),
	
	FRESH_STAFF ("Fresh Staff");
	
	private final String description;
	
	UserType(final String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
