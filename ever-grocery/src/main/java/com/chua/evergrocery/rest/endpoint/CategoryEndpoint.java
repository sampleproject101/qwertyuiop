package com.chua.evergrocery.rest.endpoint;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.chua.evergrocery.database.entity.Category;
import com.chua.evergrocery.rest.handler.CategoryHandler;

@Path("/category")
public class CategoryEndpoint {

	@Autowired
	private CategoryHandler categoryHandler;
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Category> getCategoryList() {
		return categoryHandler.getCategoryList();
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean removeCategory(@FormParam("categoryId") Long categoryId) {
		return categoryHandler.removeCategory(categoryId);
	}
}
