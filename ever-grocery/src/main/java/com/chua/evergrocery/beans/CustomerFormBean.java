package com.chua.evergrocery.beans;

public class CustomerFormBean extends FormBean {

	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private Float totalPurchases;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getTotalPurchases() {
		return totalPurchases;
	}

	public void setTotalPurchases(Float totalPurchases) {
		this.totalPurchases = totalPurchases;
	}
}
