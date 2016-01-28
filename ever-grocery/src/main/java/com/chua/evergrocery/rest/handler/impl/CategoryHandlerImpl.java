package com.chua.evergrocery.rest.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.beans.CategoryFormBean;
import com.chua.evergrocery.database.entity.Category;
import com.chua.evergrocery.database.service.CategoryService;
import com.chua.evergrocery.objects.ObjectList;
import com.chua.evergrocery.rest.handler.CategoryHandler;

@Transactional
@Component
public class CategoryHandlerImpl implements CategoryHandler {

	@Autowired
	private CategoryService categoryService;

	@Override
	public ObjectList<Category> getCategoryList(Integer pageNumber, String searchKey) {
		return categoryService.findAllWithPaging(pageNumber, Application.ITEMS_PER_PAGE, searchKey);
	}
	
	@Override
	public Category getCategory(Long categoryId) {
		return categoryService.find(categoryId);
	}
	
	@Override
	public Boolean createCategory(CategoryFormBean categoryForm) {
		final Category category = new Category();
		
		setCategory(category, categoryForm);
		
		return categoryService.insert(category) != null;
	}
	
	@Override
	public Boolean updateCategory(CategoryFormBean categoryForm) {
		final Boolean success;
		
		final Category category = categoryService.find(categoryForm.getId());
		if(category != null) {
			setCategory(category, categoryForm);
			success = categoryService.update(category);
		} else {
			success = Boolean.FALSE;
		}
		
		return success;
	}

	@Override
	public Boolean removeCategory(Long categoryId) {
		return categoryService.delete(categoryService.find(categoryId));
	}
	
	private void setCategory(Category category, CategoryFormBean categoryForm) {
		category.setName(categoryForm.getName());
	}
}
