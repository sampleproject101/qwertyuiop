package com.chua.evergrocery.rest.handler;

import java.util.List;

import com.chua.evergrocery.database.entity.Category;

public interface CategoryHandler {

	List<Category> getCategoryList();
	
	Boolean removeCategory(Long categoryId);
}
