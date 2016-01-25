package com.chua.evergrocery.rest.handler;

import java.util.List;

import com.chua.evergrocery.beans.ProductFormBean;
import com.chua.evergrocery.database.entity.Product;

public interface ProductHandler {

	List<Product> getProductList(String searchKey);
	
	Product getProduct(Long productId);
	
	Boolean createProduct(ProductFormBean productForm);
	
	Boolean updateProduct(ProductFormBean productForm);
	
	Boolean removeProduct(Long productId);
}
