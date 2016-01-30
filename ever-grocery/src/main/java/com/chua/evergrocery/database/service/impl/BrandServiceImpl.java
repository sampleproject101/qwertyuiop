package com.chua.evergrocery.database.service.impl;

import java.util.List;

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
	protected BrandServiceImpl(BrandDAO dao) {
		super(dao);
	}

	@Override
	public ObjectList<Brand> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey) {
		return dao.findAllWithPaging(pageNumber, resultsPerPage, searchKey);
	}

	@Override
	public List<Brand> searchAll(String name) {
		return dao.searchAll(name);
	}
}
