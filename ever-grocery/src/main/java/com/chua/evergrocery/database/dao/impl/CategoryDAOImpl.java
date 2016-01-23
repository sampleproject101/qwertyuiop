package com.chua.evergrocery.database.dao.impl;

import org.springframework.stereotype.Repository;

import com.chua.evergrocery.database.dao.CategoryDAO;
import com.chua.evergrocery.database.entity.Category;

@Repository
public class CategoryDAOImpl
		extends AbstractDAO<Category, Long>
		implements CategoryDAO {

}