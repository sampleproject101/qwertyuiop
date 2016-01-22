package com.chua.evergrocery;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.chua.evergrocery.rest.endpoint.BrandEndpoint;
import com.chua.evergrocery.rest.endpoint.CategoryEndpoint;
import com.chua.evergrocery.rest.endpoint.CompanyEndpoint;
import com.chua.evergrocery.rest.endpoint.DistributorEndpoint;
import com.chua.evergrocery.rest.endpoint.ProductEndpoint;

@Component
@ApplicationPath("services")
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		//
		
		// Register End Points
		register(ProductEndpoint.class);
		register(BrandEndpoint.class);
		register(CategoryEndpoint.class);
		register(CompanyEndpoint.class);
		register(DistributorEndpoint.class);
	}
}
