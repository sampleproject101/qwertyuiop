package com.chua.evergrocery.rest.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.chua.evergrocery.database.entity.Brand;
import com.chua.evergrocery.rest.handler.BrandHandler;

@Component
public class BrandHandlerImpl implements BrandHandler {

	@Override
	public List<Brand> getBrandList() {
		final List<Brand> brandList = new ArrayList<>();
		
		final Brand brand1 = new Brand();
		brand1.setName("Nestle");
		
		final Brand brand2 = new Brand();
		brand2.setName("Rebisco");
		
		brandList.add(brand1);
		brandList.add(brand2);
		return brandList;
	}
	
}
