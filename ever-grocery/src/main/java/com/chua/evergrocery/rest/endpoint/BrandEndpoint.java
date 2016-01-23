package com.chua.evergrocery.rest.endpoint;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.chua.evergrocery.database.entity.Brand;
import com.chua.evergrocery.rest.handler.BrandHandler;

@Path("/brand")
public class BrandEndpoint {
	
	@Autowired
	private BrandHandler brandHandler;
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Brand> getBrandList() {
		return brandHandler.getBrandList();
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean removeBrand(@FormParam("brandId") Long brandId) {
		System.out.println("brand id ============================= " + brandId);
		return brandHandler.removeBrand(brandId);
	}
}
