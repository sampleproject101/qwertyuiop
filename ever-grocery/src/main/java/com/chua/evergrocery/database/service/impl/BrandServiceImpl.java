package com.chua.evergrocery.database.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chua.evergrocery.database.dao.BrandDAO;
import com.chua.evergrocery.database.entity.Brand;
import com.chua.evergrocery.database.service.BrandService;
import com.chua.evergrocery.objects.ObjectList;

@Service
public class BrandServiceImpl
		extends AbstractService<Brand, Long, BrandDAO>
		implements BrandService {

	@Autowired
	private BrandDAO brandDao;
	
	@PostConstruct
	public void postConstruct() {
		super.setDao(brandDao);
	}

	@Override
	public ObjectList<Brand> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey) {
		return brandDao.findAllWithPaging(pageNumber, resultsPerPage, searchKey);
	}
}
