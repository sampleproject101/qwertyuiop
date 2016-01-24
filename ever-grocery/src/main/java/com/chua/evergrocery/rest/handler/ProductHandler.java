package com.chua.evergrocery.rest.handler;

import java.util.List;

import com.chua.evergrocery.database.entity.Product;

public interface ProductHandler {

	List<Product> getProductList();
	
	Boolean removeProduct(Long productId);
}
