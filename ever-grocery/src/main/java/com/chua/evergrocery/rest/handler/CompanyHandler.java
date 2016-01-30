package com.chua.evergrocery.rest.handler;

import java.util.List;

import com.chua.evergrocery.beans.CompanyFormBean;
import com.chua.evergrocery.database.entity.Company;
import com.chua.evergrocery.objects.ObjectList;

public interface CompanyHandler {

	ObjectList<Company> getCompanyObjectList(Integer pageNumber, String searchKey);
	
	Company getCompany(Long companyId);
	
	Boolean createCompany(CompanyFormBean companyForm);
	
	Boolean updateCompany(CompanyFormBean companyForm);
	
	Boolean removeCompany(Long companyId);
	
	List<Company> getCompanyList();
}
