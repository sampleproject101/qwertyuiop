package com.chua.evergrocery.rest.endpoint;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.chua.evergrocery.beans.CompanyFormBean;
import com.chua.evergrocery.database.entity.Company;
import com.chua.evergrocery.rest.handler.CompanyHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/company")
public class CompanyEndpoint {

	@Autowired
	private CompanyHandler companyHandler;
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Company> getCompanyList() {
		return companyHandler.getCompanyList();
	}
	
	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean saveCompany(@FormParam("companyFormData") String companyFormData) throws IOException {
		final Boolean success;
		
		final CompanyFormBean companyForm = new ObjectMapper().readValue(companyFormData, CompanyFormBean.class);
		if(companyForm.getId() != null) {
			success = companyHandler.updateCompany(companyForm);
		} else {
			success = companyHandler.createCompany(companyForm);
		}
		
		return success;
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean removeCompany(@FormParam("companyId") Long companyId) {
		return companyHandler.removeCompany(companyId);
	}
}
