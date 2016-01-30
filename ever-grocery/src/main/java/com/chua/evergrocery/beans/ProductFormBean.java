package com.chua.evergrocery.beans;

public class ProductFormBean extends FormBean {

	private String name;
	
	private Long brandId;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getBrandId() {
		return brandId;
	}
	
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
}
