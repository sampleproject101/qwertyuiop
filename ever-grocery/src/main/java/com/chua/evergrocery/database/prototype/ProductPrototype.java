package com.chua.evergrocery.database.prototype;

import com.chua.evergrocery.database.entity.Product;
import com.chua.evergrocery.objects.ObjectList;

public interface ProductPrototype {

	/**
	 * Find all product with paging.
	 * 
	 * @param pageNumber the page number
	 * @param resultsPerPage the results per page
	 * @param searchKey the search key
	 * 
	 * @return the object list of product
	 */
	public ObjectList<Product> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey);
}
