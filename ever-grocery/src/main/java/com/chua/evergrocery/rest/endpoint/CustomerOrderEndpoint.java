package com.chua.evergrocery.rest.endpoint;

import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.chua.evergrocery.beans.CustomerOrderFormBean;
import com.chua.evergrocery.beans.ResultBean;
import com.chua.evergrocery.database.entity.CustomerOrder;
import com.chua.evergrocery.objects.ObjectList;
import com.chua.evergrocery.rest.handler.CustomerOrderHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/customerorder")
public class CustomerOrderEndpoint {

	@Autowired
	private CustomerOrderHandler customerOrderHandler;
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public ObjectList<CustomerOrder> getCustomerOrderList(@QueryParam("pageNumber") Integer pageNumber, @QueryParam("searchKey") String searchKey) {
		return customerOrderHandler.getCustomerOrderList(pageNumber, searchKey);
	}
	
	@GET
	@Path("/get")
	@Produces({ MediaType.APPLICATION_JSON })
	public CustomerOrder getCustomerOrder(@QueryParam("customerOrderId") Long customerOrderId) {
		return customerOrderHandler.getCustomerOrder(customerOrderId);
	}
	
	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean saveCustomerOrder(@FormParam("customerOrderFormData") String customerOrderFormData) throws IOException {
		final ResultBean result;
		
		final CustomerOrderFormBean customerOrderForm = new ObjectMapper().readValue(customerOrderFormData, CustomerOrderFormBean.class);
		if(customerOrderForm.getId() != null) {
			result = customerOrderHandler.updateCustomerOrder(customerOrderForm);
		} else {
			result = customerOrderHandler.createCustomerOrder(customerOrderForm);
		}
		
		return result;
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean removeCustomerOrder(@FormParam("customerOrderId") Long customerOrderId) {
		return customerOrderHandler.removeCustomerOrder(customerOrderId);
	}
}