package com.chua.evergrocery.database.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chua.evergrocery.database.entity.base.BaseObject;

@Entity(name = "ProductDetail")
@Table(name = ProductDetail.TABLE_NAME)
public class ProductDetail extends BaseObject {
	private static final long serialVersionUID = 4868322850907223089L;

	public static final String TABLE_NAME = "product_detail";
	
	private String barcode;
	private Float grossPrice;
	private Float discount;
	private Float netPrice;
	private Float sellingPrice;
	private Float profit;
	
	private String unitType;
	private Integer typeDepth;
	
	@Basic
	@Column(name = "barcode")
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	@Basic
	@Column(name = "grossPrice")
	public Float getGrossPrice() {
		return grossPrice;
	}
	
	public void setGrossPrice(Float grossPrice) {
		this.grossPrice = grossPrice;
	}
	
	@Basic
	@Column(name = "discount")
	public Float getDiscount() {
		return discount;
	}
	
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	
	@Basic
	@Column(name = "netPrice")
	public Float getNetPrice() {
		return netPrice;
	}
	
	public void setNetPrice(Float netPrice) {
		this.netPrice = netPrice;
	}
	
	@Basic
	@Column(name = "sellingPrice")
	public Float getSellingPrice() {
		return sellingPrice;
	}
	
	public void setSellingPrice(Float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	@Basic
	@Column(name = "profit")
	public Float getProfit() {
		return profit;
	}
	
	public void setProfit(Float profit) {
		this.profit = profit;
	}
	
	@Basic
	@Column(name = "unitType")
	public String getUnitType() {
		return unitType;
	}
	
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	
	@Basic
	@Column(name = "typeDepth")
	public Integer getTypeDepth() {
		return typeDepth;
	}
	
	public void setTypeDepth(Integer typeDepth) {
		this.typeDepth = typeDepth;
	}
}
