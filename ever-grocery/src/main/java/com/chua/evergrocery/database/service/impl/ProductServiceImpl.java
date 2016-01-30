package com.chua.evergrocery.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chua.evergrocery.database.dao.ProductDAO;
import com.chua.evergrocery.database.entity.Product;
import com.chua.evergrocery.database.service.ProductService;
import com.chua.evergrocery.objects.ObjectList;

@Service
public class ProductServiceImpl
		extends AbstractService<Product, Long, ProductDAO>
		implements ProductService {

	@Autowired
	protected ProductServiceImpl(ProductDAO dao) {
		super(dao);
	}
	
	@Override
	public ObjectList<Product> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey) {
		return dao.findAllWithPaging(pageNumber, resultsPerPage, searchKey);
	}
}
