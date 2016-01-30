package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.beans.BrandFormBean;
import com.chua.evergrocery.database.entity.Brand;
import com.chua.evergrocery.database.service.BrandService;
import com.chua.evergrocery.objects.ObjectList;
import com.chua.evergrocery.rest.handler.BrandHandler;

@Transactional
@Component
public class BrandHandlerImpl implements BrandHandler {

	@Autowired
	private BrandService brandService;

	@Override
	public ObjectList<Brand> getBrandObjectList(Integer pageNumber, String searchKey) {
		return brandService.findAllWithPaging(pageNumber, Application.ITEMS_PER_PAGE, searchKey);
	}
	
	@Override
	public Brand getBrand(Long brandId) {
		return brandService.find(brandId);
	}
	
	@Override
	public Boolean createBrand(BrandFormBean brandForm) {
		final Brand brand = new Brand();
		
		setBrand(brand, brandForm);
		
		return brandService.insert(brand) != null;
	}
	
	@Override
	public Boolean updateBrand(BrandFormBean brandForm) {
		final Boolean success;
		
		final Brand brand = brandService.find(brandForm.getId());
		if(brand != null) {
			setBrand(brand, brandForm);
			success = brandService.update(brand);
		} else {
			success = Boolean.FALSE;
		}
		
		return success;
	}

	@Override
	public Boolean removeBrand(Long brandId) {
		return brandService.delete(brandService.find(brandId));
	}
	
	@Override
	public List<Brand> getBrandList() {
		return brandService.findAllOrderByName();
	}
	
	private void setBrand(Brand brand, BrandFormBean brandForm) {
		brand.setName(brandForm.getName());
	}
}
