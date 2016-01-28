package com.chua.evergrocery.rest.handler;

import com.chua.evergrocery.beans.DistributorFormBean;
import com.chua.evergrocery.database.entity.Distributor;
import com.chua.evergrocery.objects.ObjectList;

public interface DistributorHandler {

	ObjectList<Distributor> getDistributorList(Integer pageNumber, String searchKey);
	
	Distributor getDistributor(Long distributorId);
	
	Boolean createDistributor(DistributorFormBean distributorForm);
	
	Boolean updateDistributor(DistributorFormBean distributorForm);
	
	Boolean removeDistributor(Long distributorId);
}
