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
		distributor1.setAddress("Batac City");
		distributor1.setAgent("Vincent");
		distributor1.setPhoneNumber("247-79-33");
		
		final Distributor distributor2 = new Distributor();
		distributor2.setName("Laoag Marketing Corporation");
		distributor2.setAddress("Makati City");
		distributor2.setAgent("Unknown");
		distributor2.setPhoneNumber("247-79-34");
		
		distributorList.add(distributor1);
		distributorList.add(distributor2);
		return distributorList;
	}
}
