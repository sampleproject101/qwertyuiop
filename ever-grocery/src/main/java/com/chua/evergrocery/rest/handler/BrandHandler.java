package com.chua.evergrocery.rest.handler;

import java.util.List;

import com.chua.evergrocery.beans.BrandFormBean;
import com.chua.evergrocery.database.entity.Brand;

public interface BrandHandler {
	
	List<Brand> getBrandList(String searchKey);
	
	Brand getBrand(Long brandId);
	
	Boolean createBrand(BrandFormBean brandForm);
	
	Boolean updateBrand(BrandFormBean brandForm);
	
	Boolean removeBrand(Long brandId);
}
