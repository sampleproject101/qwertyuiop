package com.chua.evergrocery.rest.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.chua.evergrocery.database.entity.Company;
import com.chua.evergrocery.rest.handler.CompanyHandler;

@Component
public class CompanyHandlerImpl implements CompanyHandler {

	@Override
	public List<Company> getCompanyList() {
		final List<Company> companyList = new ArrayList<>();
		
		final Company company1 = new Company();
		company1.setName("RKT Corporation");
		company1.setAddress("Laoag City");
		company1.setAgent("Unknown");
		company1.setPhoneNumber("247-79-31");
		
		final Company company2 = new Company();
		company2.setName("Fly Ace Corporation");
		company2.setAddress("Manila");
		company2.setAgent("Kevin");
		company2.setPhoneNumber("247-79-32");
		
		companyList.add(company1);
		companyList.add(company2);
		return companyList;
	}
}
