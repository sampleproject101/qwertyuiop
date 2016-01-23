package com.chua.evergrocery.database.dao.impl;

import org.springframework.stereotype.Repository;

import com.chua.evergrocery.database.dao.CompanyDAO;
import com.chua.evergrocery.database.entity.Company;

@Repository
public class CompanyDAOImpl 
		extends AbstractDAO<Company, Long>
		implements CompanyDAO {

}
