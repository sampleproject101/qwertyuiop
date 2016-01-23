package com.chua.evergrocery.rest.handler;

import java.util.List;

import com.chua.evergrocery.database.entity.Company;

public interface CompanyHandler {

	List<Company> getCompanyList();
	
	Boolean removeCompany(Long companyId);
}
