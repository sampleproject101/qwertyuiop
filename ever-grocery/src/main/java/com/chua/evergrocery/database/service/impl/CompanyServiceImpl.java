package com.chua.evergrocery.database.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chua.evergrocery.database.dao.CompanyDAO;
import com.chua.evergrocery.database.entity.Company;
import com.chua.evergrocery.database.service.CompanyService;
import com.chua.evergrocery.objects.ObjectList;

@Service
public class CompanyServiceImpl 
		extends AbstractService<Company, Long, CompanyDAO>
		implements CompanyService {

	@Autowired
	private CompanyDAO companyDao;
	
	@PostConstruct
	public void postConstruct() {
		super.setDao(companyDao);
	}
	
	@Override
	public ObjectList<Company> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey) {
		return companyDao.findAllWithPaging(pageNumber, resultsPerPage, searchKey);
	}
}
