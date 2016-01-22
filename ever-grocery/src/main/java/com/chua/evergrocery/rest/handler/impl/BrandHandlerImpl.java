package com.chua.evergrocery.rest.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.database.entity.Brand;
import com.chua.evergrocery.database.service.BrandService;
import com.chua.evergrocery.rest.handler.BrandHandler;

@Transactional
@Component
public class BrandHandlerImpl implements BrandHandler {

	@Autowired
	private BrandService brandService;
	
	@Override
	public List<Brand> getBrandList() {
		final Brand b = brandService.find(1L);
		b.setName("Nestle 2");
		brandService.update(b);
		
		System.out.println("brand service ------------------------------- " + brandService);
		final List<Brand> brandList = new ArrayList<>();
		
		final Brand brand1 = new Brand();
		brand1.setName("Nestle");
		brandService.insert(brand1);
		final Brand brand2 = new Brand();
		brand2.setName("Rebisco");
		
		brandList.add(brand1);
		brandList.add(brand2);
		return brandList;
	}
	
}
