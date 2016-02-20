package com.chua.evergrocery.database.service;

import com.chua.evergrocery.database.entity.Product;
import com.chua.evergrocery.database.prototype.ProductPrototype;

public interface ProductService
		extends Service<Product, Long>, ProductPrototype {
	
	Boolean isExistsByName(String name);
}
