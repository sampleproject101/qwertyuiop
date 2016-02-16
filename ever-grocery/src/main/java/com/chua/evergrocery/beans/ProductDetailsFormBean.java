package com.chua.evergrocery.beans;

public class ProductDetailsFormBean {

	private String barcode;
	
	private Integer quantity;
	
	private String unit;
	
	private Float grossPrice;
	
	private Float discount;
	
	private Float netPrice;
	
	private Float percentProfit;
	
	private Float sellingPrice;
	
	private Float netProfit;
	
	private Integer stockCount;
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Float getGrossPrice() {
		return grossPrice;
	}
	
	public void setGrossPrice(Float grossPrice) {
		this.grossPrice = grossPrice;
	}
	
	public Float getDiscount() {
		return discount;
	}
	
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	
	public Float getNetPrice() {
		return netPrice;
	}
	
	public void setNetPrice(Float netPrice) {
		this.netPrice = netPrice;
	}
	
	public Float getPercentProfit() {
		return percentProfit;
	}
	
	public void setPercentProfit(Float percentProfit) {
		this.percentProfit = percentProfit;
	}
	
	public Float getSellingPrice() {
		return sellingPrice;
	}
	
	public void setSellingPrice(Float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public Float getNetProfit() {
		return netProfit;
	}
	
	public void setNetProfit(Float netProfit) {
		this.netProfit = netProfit;
	}
	
	public Integer getStockCount() {
		return stockCount;
	}
	
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

}
