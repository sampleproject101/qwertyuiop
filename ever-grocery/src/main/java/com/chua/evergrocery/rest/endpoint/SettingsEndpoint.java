package com.chua.evergrocery.rest.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.chua.evergrocery.rest.handler.SettingsHandler;

@Path("/settings")
public class SettingsEndpoint {

	@Autowired
	private SettingsHandler settingsHandler;
	
	@GET
	@Path("/itemsperpage")
	@Produces({ MediaType.APPLICATION_JSON })
	public Integer getItemsPerPage() {
		return settingsHandler.getItemsPerPage();
	}
}
