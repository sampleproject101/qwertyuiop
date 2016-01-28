package com.chua.evergrocery.rest.handler.impl;

import org.springframework.stereotype.Component;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.rest.handler.SettingsHandler;

@Component
public class SettingsHandlerImpl implements SettingsHandler {

	@Override
	public Integer getItemsPerPage() {
		return Application.ITEMS_PER_PAGE;
	}
}
