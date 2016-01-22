package com.chua.evergrocery.database.dao.impl;

import org.springframework.stereotype.Repository;

import com.chua.evergrocery.database.dao.BrandDAO;
import com.chua.evergrocery.database.entity.Brand;

@Repository
public class BrandDAOImpl 
		extends AbstractDAO<Brand, Long>
		implements BrandDAO {

}
