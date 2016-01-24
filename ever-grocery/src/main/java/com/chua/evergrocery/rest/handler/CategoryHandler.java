package com.chua.evergrocery.rest.handler;

import java.util.List;

import com.chua.evergrocery.beans.CategoryFormBean;
import com.chua.evergrocery.database.entity.Category;

public interface CategoryHandler {

	List<Category> getCategoryList(String searchKey);
	
	Category getCategory(Long categoryId);
	
	Boolean createCategory(CategoryFormBean categoryForm);
	
	Boolean updateCategory(CategoryFormBean categoryForm);
	
	Boolean removeCategory(Long categoryId);
}
