package com.chua.evergrocery.rest.handler;

import java.util.List;

import com.chua.evergrocery.beans.CompanyFormBean;
import com.chua.evergrocery.database.entity.Company;

public interface CompanyHandler {

	List<Company> getCompanyList(String searchKey);
	
	Company getCompany(Long companyId);
	
	Boolean createCompany(CompanyFormBean companyForm);
	
	Boolean updateCompany(CompanyFormBean companyForm);
	
	Boolean removeCompany(Long companyId);
}
