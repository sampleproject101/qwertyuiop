package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.beans.CompanyFormBean;
import com.chua.evergrocery.database.entity.Company;
import com.chua.evergrocery.database.service.CompanyService;
import com.chua.evergrocery.objects.ObjectList;
import com.chua.evergrocery.rest.handler.CompanyHandler;

@Transactional
@Component
public class CompanyHandlerImpl implements CompanyHandler {

	@Autowired
	private CompanyService companyService;

	@Override
	public ObjectList<Company> getCompanyObjectList(Integer pageNumber, String searchKey) {
		return companyService.findAllWithPaging(pageNumber, Application.ITEMS_PER_PAGE, searchKey);
	}
	
	@Override
	public Company getCompany(Long companyId) {
		return companyService.find(companyId);
	}
	
	@Override
	public Boolean createCompany(CompanyFormBean companyForm) {
		final Company company = new Company();
		
		setCompany(company, companyForm);
		
		return companyService.insert(company) != null;
	}
	
	@Override
	public Boolean updateCompany(CompanyFormBean companyForm) {
		final Boolean success;
		
		final Company company = companyService.find(companyForm.getId());
		if(company != null) {
			setCompany(company, companyForm);
			success = companyService.update(company);
		} else {
			success = Boolean.FALSE;
		}
		
		return success;
	}

	@Override
	public Boolean removeCompany(Long companyId) {
		return companyService.delete(companyService.find(companyId));
	}
	
	@Override
	public List<Company> getCompanyList() {
		return companyService.findAllOrderByName();
	}
	
	private void setCompany(Company company, CompanyFormBean companyForm) {
		company.setName(companyForm.getName());
		company.setAddress(companyForm.getAddress());
		company.setAgent(companyForm.getAgent());
		company.setPhoneNumber(companyForm.getPhoneNumber());
	}
}
