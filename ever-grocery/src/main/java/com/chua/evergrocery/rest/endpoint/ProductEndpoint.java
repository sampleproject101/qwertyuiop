package com.chua.evergrocery.rest.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/product")
public class ProductEndpoint {

	@GET
	@Path("/test")
	@Produces({ MediaType.TEXT_HTML })
	public String getMessage() {
		return "Hello Jasper Shuki";
	}
}
