package com.chua.evergrocery.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chua.evergrocery.database.dao.ProductDetailDAO;
import com.chua.evergrocery.database.entity.ProductDetail;
import com.chua.evergrocery.database.service.ProductDetailService;

@Service
public class ProductDetailServiceImpl 
		extends AbstractService<ProductDetail, Long, ProductDetailDAO>
		implements ProductDetailService 
{
	@Autowired
	protected ProductDetailServiceImpl(ProductDetailDAO dao) {
		super(dao);
	}
}
