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
import com.chua.evergrocery.serializer.json.BrandSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity(name = "Product")
@Table(name = Product.TABLE_NAME)
public class Product extends BaseObject {
	private static final long serialVersionUID = -4829246496849289618L;
	
	public static final String TABLE_NAME = "product";
	
	@JsonSerialize(using = BrandSerializer.class)
	private Brand brand;
	
	private Category category;
	
	private Company company;
	
	private Distributor distributor;
	
	private String name;
	private Float totalStockCount;
	
	@ManyToOne(targetEntity = Brand.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	@Where(clause = "valid = 1")
	@NotFound(action = NotFoundAction.IGNORE)
	public Brand getBrand() {
		return brand;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	@ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@Where(clause = "valid = 1")
	@NotFound(action = NotFoundAction.IGNORE)
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@ManyToOne(targetEntity = Company.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	@Where(clause = "valid = 1")
	@NotFound(action = NotFoundAction.IGNORE)
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@ManyToOne(targetEntity = Distributor.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "distributor_id")
	@Where(clause = "valid = 1")
	@NotFound(action = NotFoundAction.IGNORE)
	public Distributor getDistributor() {
		return distributor;
	}
	
	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}
	
	@Basic
	@Column(name = "name") // length = 255
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Basic
	@Column(name = "total_stock_count")
	public Float getTotalStockCount() {
		return totalStockCount;
	}
	
	public void setTotalStockCount(Float totalStockCount) {
		this.totalStockCount = totalStockCount;
	}
}
