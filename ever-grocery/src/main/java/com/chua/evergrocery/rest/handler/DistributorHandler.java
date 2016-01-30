package com.chua.evergrocery.rest.handler;

import java.util.List;

import com.chua.evergrocery.beans.DistributorFormBean;
import com.chua.evergrocery.database.entity.Distributor;
import com.chua.evergrocery.objects.ObjectList;

public interface DistributorHandler {

	ObjectList<Distributor> getDistributorObjectList(Integer pageNumber, String searchKey);
	
	Distributor getDistributor(Long distributorId);
	
	Boolean createDistributor(DistributorFormBean distributorForm);
	
	Boolean updateDistributor(DistributorFormBean distributorForm);
	
	Boolean removeDistributor(Long distributorId);
	
	List<Distributor> getDistributorList();
}
