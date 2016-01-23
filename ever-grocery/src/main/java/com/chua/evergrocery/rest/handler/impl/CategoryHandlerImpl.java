package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.database.entity.Category;
import com.chua.evergrocery.database.service.CategoryService;
import com.chua.evergrocery.rest.handler.CategoryHandler;

@Transactional
@Component
public class CategoryHandlerImpl implements CategoryHandler {

	@Autowired
	private CategoryService categoryService;

	@Override
	public List<Category> getCategoryList() {
		return categoryService.findAllWithPaging(0, Application.ITEMS_PER_PAGE, null).getList();
	}

	@Override
	public Boolean removeCategory(Long categoryId) {
		return categoryService.delete(categoryService.find(categoryId));
	}
}
