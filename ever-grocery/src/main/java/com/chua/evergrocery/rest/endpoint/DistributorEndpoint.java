package com.chua.evergrocery.rest.endpoint;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.chua.evergrocery.database.entity.Distributor;
import com.chua.evergrocery.rest.handler.DistributorHandler;

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
}
