package com.chua.evergrocery.database.service.impl;

import javax.annotation.PostConstruct;

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
	private ProductDAO productDao;
	
	@PostConstruct
	public void postConstruct() {
		super.setDao(productDao);
	}
	
	@Override
	public ObjectList<Product> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey) {
		return productDao.findAllWithPaging(pageNumber, resultsPerPage, searchKey);
	}
}
