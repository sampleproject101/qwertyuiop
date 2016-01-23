package com.chua.evergrocery.rest.endpoint;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.chua.evergrocery.database.entity.Company;
import com.chua.evergrocery.rest.handler.CompanyHandler;

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
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean removeCompany(@FormParam("companyId") Long companyId) {
		return companyHandler.removeCompany(companyId);
	}
}
