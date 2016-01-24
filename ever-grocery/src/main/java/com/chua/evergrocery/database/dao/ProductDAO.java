package com.chua.evergrocery.database.dao;

import com.chua.evergrocery.database.entity.Product;
import com.chua.evergrocery.database.prototype.ProductPrototype;

public interface ProductDAO extends DAO<Product, Long>, ProductPrototype {

}
