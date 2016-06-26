package com.chua.evergrocery.enums;

public enum Status {

	CANCELLED("Cancelled"),
	
	LISTING("Listing"),
	
	CASHIER_PROCESSING("Cashier Processing"),
	
	PAID("Paid");
	
	private final String description;
	
	Status(final String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
