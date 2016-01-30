package com.chua.evergrocery.rest.handler;

import java.util.List;

import com.chua.evergrocery.beans.BrandFormBean;
import com.chua.evergrocery.database.entity.Brand;
import com.chua.evergrocery.objects.ObjectList;

public interface BrandHandler {
	
	ObjectList<Brand> getBrandObjectList(Integer pageNumber, String searchKey);
	
	Brand getBrand(Long brandId);
	
	Boolean createBrand(BrandFormBean brandForm);
	
	Boolean updateBrand(BrandFormBean brandForm);
	
	Boolean removeBrand(Long brandId);
	
	List<Brand> getBrandList();
}
