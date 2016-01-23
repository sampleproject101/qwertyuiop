package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.database.entity.Company;
import com.chua.evergrocery.database.service.CompanyService;
import com.chua.evergrocery.rest.handler.CompanyHandler;

@Transactional
@Component
public class CompanyHandlerImpl implements CompanyHandler {

	@Autowired
	private CompanyService companyService;

	@Override
	public List<Company> getCompanyList() {
		return companyService.findAllWithPaging(0, Application.ITEMS_PER_PAGE, null).getList();
	}

	@Override
	public Boolean removeCompany(Long companyId) {
		return companyService.delete(companyService.find(companyId));
	}
}
