package com.chua.evergrocery.rest.endpoint;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import com.chua.evergrocery.rest.handler.SettingsHandler;

@Path("/settings")
public class SettingsEndpoint {

	@Autowired
	private SettingsHandler settingsHandler;
}
