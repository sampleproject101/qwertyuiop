package com.chua.evergrocery.database.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chua.evergrocery.database.entity.base.BaseObject;

@Entity(name = "Brand")
@Table(name = "brand")
public class Brand extends BaseObject {

	private static final long serialVersionUID = 6913786396870052396L;

	private String name;
	
	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
