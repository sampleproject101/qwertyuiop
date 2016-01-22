package com.chua.evergrocery.rest.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.chua.evergrocery.database.entity.Category;
import com.chua.evergrocery.rest.handler.CategoryHandler;

@Component
public class CategoryHandlerImpl implements CategoryHandler {

	@Override
	public List<Category> getCategoryList() {
		final List<Category> categoryList = new ArrayList<>();
		
		final Category category1 = new Category();
		category1.setName("Dairy");
		
		final Category category2 = new Category();
		category2.setName("Canned Goods");
		
		categoryList.add(category1);
		categoryList.add(category2);
		return categoryList;
	}
}
