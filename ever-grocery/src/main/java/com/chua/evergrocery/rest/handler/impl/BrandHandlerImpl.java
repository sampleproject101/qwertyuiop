package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.database.entity.Brand;
import com.chua.evergrocery.database.service.BrandService;
import com.chua.evergrocery.rest.handler.BrandHandler;

@Component
public class BrandHandlerImpl implements BrandHandler {

	@Autowired
	private BrandService brandService;
	
	@Transactional
	@Override
	public List<Brand> getBrandList() {
		return brandService.findAllWithPaging(0, Application.ITEMS_PER_PAGE, null).getList();
	}

	@Transactional
	@Override
	public Boolean removeBrand(Long brandId) {
		return brandService.delete(brandService.find(brandId));
	}
	
}
