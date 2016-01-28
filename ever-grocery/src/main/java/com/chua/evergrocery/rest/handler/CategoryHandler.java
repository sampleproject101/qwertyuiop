package com.chua.evergrocery.rest.handler;

import com.chua.evergrocery.beans.CategoryFormBean;
import com.chua.evergrocery.database.entity.Category;
import com.chua.evergrocery.objects.ObjectList;

public interface CategoryHandler {

	ObjectList<Category> getCategoryList(Integer pageNumber, String searchKey);
	
	Category getCategory(Long categoryId);
	
	Boolean createCategory(CategoryFormBean categoryForm);
	
	Boolean updateCategory(CategoryFormBean categoryForm);
	
	Boolean removeCategory(Long categoryId);
}
