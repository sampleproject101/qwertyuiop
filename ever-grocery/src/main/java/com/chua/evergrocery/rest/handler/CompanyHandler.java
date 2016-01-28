package com.chua.evergrocery.rest.handler;

import com.chua.evergrocery.beans.CompanyFormBean;
import com.chua.evergrocery.database.entity.Company;
import com.chua.evergrocery.objects.ObjectList;

public interface CompanyHandler {

	ObjectList<Company> getCompanyList(Integer pageNumber, String searchKey);
	
	Company getCompany(Long companyId);
	
	Boolean createCompany(CompanyFormBean companyForm);
	
	Boolean updateCompany(CompanyFormBean companyForm);
	
	Boolean removeCompany(Long companyId);
}
