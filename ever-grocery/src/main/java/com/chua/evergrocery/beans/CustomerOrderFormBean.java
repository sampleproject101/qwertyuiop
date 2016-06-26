package com.chua.evergrocery.beans;

import com.chua.evergrocery.enums.Status;

public class CustomerOrderFormBean extends FormBean {

	private String name;
	
	private Long customerId;
	
	private Long creatorId;
	
	private Long cashierId;
	
	private Float totalAmount;
	
	private Status status;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getCashierId() {
		return cashierId;
	}

	public void setCashierId(Long cashierId) {
		this.cashierId = cashierId;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
