package com.chua.evergrocery.database.prototype;

import java.util.List;

import com.chua.evergrocery.database.entity.Brand;
import com.chua.evergrocery.objects.ObjectList;

public interface BrandPrototype extends Prototype<Brand, Long> {

	/**
	 * Find all brand with paging.
	 * 
	 * @param pageNumber the page number
	 * @param resultsPerPage the results per page
	 * @param searchKey the search key
	 * 
	 * @return the object list of brand
	 */
	ObjectList<Brand> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey);
	
	/**
	 * Search all brand by the given name.
	 * 
	 * @param name the name
	 * 
	 * @return the list of brand
	 */
	List<Brand> searchAll(String name);
}
