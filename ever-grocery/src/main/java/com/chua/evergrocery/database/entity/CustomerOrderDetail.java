package com.chua.evergrocery.database.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.chua.evergrocery.database.entity.base.BaseObject;
import com.chua.evergrocery.enums.UnitType;

@Entity(name = "CustomerOrderDetail")
@Table(name = CustomerOrderDetail.TABLE_NAME)
public class CustomerOrderDetail extends BaseObject {

	private static final long serialVersionUID = 6024149457706035805L;
	
	public static final String TABLE_NAME = "customer_order_detail";
	
	private CustomerOrder customerOrder;
	
	private Long productDetailId;
	
	private String productName;
	
	private UnitType unitType;
	
	private Float unitPrice;
	
	private Integer quantity;
	
	private Float totalPrice;

	@ManyToOne(targetEntity = CustomerOrder.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_order_id")
	@Where(clause = "valid = 1")
	@NotFound(action = NotFoundAction.IGNORE)
	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	@Basic
	@Column(name = "product_detail_id")
	public Long getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Long productDetailId) {
		this.productDetailId = productDetailId;
	}

	@Basic
	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "unit_type", length = 50)
	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	@Basic
	@Column(name = "unit_price")
	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Basic
	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Basic
	@Column(name = "total_price")
	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
}
