package com.chua.evergrocery.database.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.chua.evergrocery.database.entity.base.BaseObject;

@Entity(name = "ProductDetail")
@Table(name = ProductDetail.TABLE_NAME)
public class ProductDetail extends BaseObject {
	private static final long serialVersionUID = 4868322850907223089L;

	public static final String TABLE_NAME = "product_detail";
	
	private Product product;
	
	//private UnitType unitType;
	private String barcode;
	private Float grossPrice;
	private Float discount;
	private Float netPrice;
	private Float sellingPrice;
	private Float profit;
	
	@ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@Where(clause = "valid = 1")
	@NotFound(action = NotFoundAction.IGNORE)
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
/*	// change to primitive data
	public UnitType getUnit() {
		return unit;
	}
	
	// 
	public void setUnit(UnitType unitType) {
		
	}*/
	
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
}
