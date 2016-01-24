package com.chua.evergrocery.rest.endpoint;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.chua.evergrocery.database.entity.Product;
import com.chua.evergrocery.rest.handler.ProductHandler;

@Path("/product")
public class ProductEndpoint {

	@Autowired
	private ProductHandler productHandler;
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> getProductList() {
		return productHandler.getProductList();
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean removeProduct(@FormParam("productId") Long productId) {
		return productHandler.removeProduct(productId);
	}
}
