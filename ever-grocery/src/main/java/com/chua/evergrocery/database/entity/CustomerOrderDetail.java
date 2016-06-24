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

@Entity(name = "CustomerOrderDetail")
@Table(name = CustomerOrderDetail.TABLE_NAME)
public class CustomerOrderDetail extends BaseObject {

	private static final long serialVersionUID = 6024149457706035805L;
	
	public static final String TABLE_NAME = "customer_order_detail";
	
	private CustomerOrder customerOrder;
	
	private ProductDetail productDetail;
	
	private Float price;
	
	private Float quantity;
	
	private Float total;

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

	@ManyToOne(targetEntity = ProductDetail.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_detail_id")
	@Where(clause = "valid = 1")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	@Basic
	@Column(name = "price")
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Basic
	@Column(name = "quantity")
	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	@Basic
	@Column(name = "total")
	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
}
