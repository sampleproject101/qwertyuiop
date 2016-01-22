package com.chua.evergrocery.rest.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.chua.evergrocery.database.entity.Distributor;
import com.chua.evergrocery.rest.handler.DistributorHandler;

@Component
public class DistributorHandlerImpl implements DistributorHandler {

	@Override
	public List<Distributor> getDistributorList() {
		final List<Distributor> distributorList = new ArrayList<>();
		
		final Distributor distributor1 = new Distributor();
		distributor1.setName("Isonn Marketing");
		
		final Distributor distributor2 = new Distributor();
		distributor2.setName("Laoag Marketing Corporation");
		
		distributorList.add(distributor1);
		distributorList.add(distributor2);
		return distributorList;
	}
}
