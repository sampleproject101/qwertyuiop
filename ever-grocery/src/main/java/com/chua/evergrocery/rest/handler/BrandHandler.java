package com.chua.evergrocery.rest.handler;

import com.chua.evergrocery.beans.BrandFormBean;
import com.chua.evergrocery.database.entity.Brand;
import com.chua.evergrocery.objects.ObjectList;

public interface BrandHandler {
	
	ObjectList<Brand> getBrandList(Integer pageNumber, String searchKey);
	
	Brand getBrand(Long brandId);
	
	Boolean createBrand(BrandFormBean brandForm);
	
	Boolean updateBrand(BrandFormBean brandForm);
	
	Boolean removeBrand(Long brandId);
}
