package com.chua.evergrocery.rest.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.chua.evergrocery.database.entity.Product;
import com.chua.evergrocery.rest.handler.ProductHandler;

@Component
public class ProductHandlerImpl implements ProductHandler {

	@Override
	public List<Product> getProductList() {
		final List<Product> productList = new ArrayList<>();
		
		final Product product1 = new Product();
		product1.setName("Hansel Crackers Milk");
		
		final Product product2 = new Product();
		product2.setName("Hansel Crackers Butter");
		
		productList.add(product1);
		productList.add(product2);
		return productList;
	}

}
