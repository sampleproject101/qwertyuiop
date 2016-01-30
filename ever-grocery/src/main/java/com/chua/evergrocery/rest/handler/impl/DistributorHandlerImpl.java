package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.beans.DistributorFormBean;
import com.chua.evergrocery.database.entity.Distributor;
import com.chua.evergrocery.database.service.DistributorService;
import com.chua.evergrocery.objects.ObjectList;
import com.chua.evergrocery.rest.handler.DistributorHandler;

@Transactional
@Component
public class DistributorHandlerImpl implements DistributorHandler {

	@Autowired
	private DistributorService distributorService;

	@Override
	public ObjectList<Distributor> getDistributorObjectList(Integer pageNumber, String searchKey) {
		return distributorService.findAllWithPaging(pageNumber, Application.ITEMS_PER_PAGE, searchKey);
	}
	
	@Override
	public Distributor getDistributor(Long distributorId) {
		return distributorService.find(distributorId);
	}
	
	@Override
	public Boolean createDistributor(DistributorFormBean distributorForm) {
		final Distributor distributor = new Distributor();
		
		setDistributor(distributor, distributorForm);
		
		return distributorService.insert(distributor) != null;
	}
	
	@Override
	public Boolean updateDistributor(DistributorFormBean distributorForm) {
		final Boolean success;
		
		final Distributor distributor = distributorService.find(distributorForm.getId());
		if(distributor != null) {
			setDistributor(distributor, distributorForm);
			success = distributorService.update(distributor);
		} else {
			success = Boolean.FALSE;
		}
		
		return success;
	}

	@Override
	public Boolean removeDistributor(Long distributorId) {
		return distributorService.delete(distributorService.find(distributorId));
	}
	
	@Override
	public List<Distributor> getDistributorList() {
		return distributorService.findAllOrderByName();
	}
	
	private void setDistributor(Distributor distributor, DistributorFormBean distributorForm) {
		distributor.setName(distributorForm.getName());
		distributor.setAddress(distributorForm.getAddress());
		distributor.setAgent(distributorForm.getAgent());
		distributor.setPhoneNumber(distributorForm.getPhoneNumber());
	}
}
