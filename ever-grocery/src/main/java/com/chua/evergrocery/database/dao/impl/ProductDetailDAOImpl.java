package com.chua.evergrocery.database.dao.impl;

import org.springframework.stereotype.Repository;

import com.chua.evergrocery.database.dao.ProductDetailDAO;
import com.chua.evergrocery.database.entity.ProductDetail;

@Repository
public class ProductDetailDAOImpl 
		extends AbstractDAO<ProductDetail, Long>
		implements ProductDetailDAO 
{

}