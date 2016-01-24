package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.database.entity.Product;
import com.chua.evergrocery.database.service.ProductService;
import com.chua.evergrocery.rest.handler.ProductHandler;

@Transactional
@Component
public class ProductHandlerImpl implements ProductHandler {

	@Autowired
	private ProductService productService;

	@Override
	public List<Product> getProductList() {
		return productService.findAllWithPaging(0, Application.ITEMS_PER_PAGE, null).getList();
	}

	@Override
	public Boolean removeProduct(Long productId) {
		return productService.delete(productService.find(productId));
	}
}
