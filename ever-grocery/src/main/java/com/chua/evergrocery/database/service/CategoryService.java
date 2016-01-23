package com.chua.evergrocery.database.service;

import com.chua.evergrocery.database.entity.Category;
import com.chua.evergrocery.database.prototype.CategoryPrototype;

public interface CategoryService 
		extends Service<Category, Long>, CategoryPrototype
{

}
