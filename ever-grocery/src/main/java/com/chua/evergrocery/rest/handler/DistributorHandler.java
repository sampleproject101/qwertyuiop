package com.chua.evergrocery.rest.handler;

import java.util.List;

import com.chua.evergrocery.database.entity.Distributor;

public interface DistributorHandler {

	List<Distributor> getDistributorList();
	
	Boolean removeDistributor(Long distributorId);
}
