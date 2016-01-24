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

import com.chua.evergrocery.beans.DistributorFormBean;
import com.chua.evergrocery.database.entity.Distributor;
import com.chua.evergrocery.rest.handler.DistributorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/distributor")
public class DistributorEndpoint {

	@Autowired
	private DistributorHandler distributorHandler;
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Distributor> getDistributorList() {
		return distributorHandler.getDistributorList();
	}
	
	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean saveDistributor(@FormParam("distributorFormData") String distributorFormData) throws IOException {
		final Boolean success;
		
		final DistributorFormBean distributorForm = new ObjectMapper().readValue(distributorFormData, DistributorFormBean.class);
		if(distributorForm.getId() != null) {
			success = distributorHandler.updateDistributor(distributorForm);
		} else {
			success = distributorHandler.createDistributor(distributorForm);
		}
		
		return success;
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean removeDistributor(@FormParam("distributorId") Long distributorId) {
		return distributorHandler.removeDistributor(distributorId);
	}
}
