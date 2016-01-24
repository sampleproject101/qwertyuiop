package com.chua.evergrocery.rest.endpoint;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.chua.evergrocery.beans.CategoryFormBean;
import com.chua.evergrocery.database.entity.Category;
import com.chua.evergrocery.rest.handler.CategoryHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/category")
public class CategoryEndpoint {

	@Autowired
	private CategoryHandler categoryHandler;
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Category> getCategoryList(@QueryParam("searchKey") String searchKey) {
		return categoryHandler.getCategoryList(searchKey);
	}
	
	@GET
	@Path("/get")
	@Produces({ MediaType.APPLICATION_JSON })
	public Category getCategory(@QueryParam("categoryId") Long categoryId) {
		return categoryHandler.getCategory(categoryId);
	}
	
	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean saveCategory(@FormParam("categoryFormData") String categoryFormData) throws IOException {
		final Boolean success;
		
		final CategoryFormBean categoryForm = new ObjectMapper().readValue(categoryFormData, CategoryFormBean.class);
		if(categoryForm.getId() != null) {
			success = categoryHandler.updateCategory(categoryForm);
		} else {
			success = categoryHandler.createCategory(categoryForm);
		}
		
		return success;
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean removeCategory(@FormParam("categoryId") Long categoryId) {
		return categoryHandler.removeCategory(categoryId);
	}
}
