package com.chua.evergrocery.rest.handler;

import com.chua.evergrocery.beans.ProductFormBean;
import com.chua.evergrocery.database.entity.Product;
import com.chua.evergrocery.objects.ObjectList;

public interface ProductHandler {

	ObjectList<Product> getProductList(Integer pageNumber, String searchKey);
	
	Product getProduct(Long productId);
	
	Boolean createProduct(ProductFormBean productForm);
	
	Boolean updateProduct(ProductFormBean productForm);
	
	Boolean removeProduct(Long productId);
}
