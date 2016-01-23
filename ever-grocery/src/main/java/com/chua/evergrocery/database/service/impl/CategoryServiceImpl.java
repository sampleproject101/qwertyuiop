package com.chua.evergrocery.database.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chua.evergrocery.database.dao.CategoryDAO;
import com.chua.evergrocery.database.entity.Category;
import com.chua.evergrocery.database.service.CategoryService;

@Service
public class CategoryServiceImpl
		extends AbstractService<Category, Long, CategoryDAO>
		implements CategoryService {

	@Autowired
	private CategoryDAO categoryDao;
	
	@PostConstruct
	public void postConstruct() {
	super.setDao(categoryDao);
	}
}