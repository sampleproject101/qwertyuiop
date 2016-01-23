package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.database.entity.Distributor;
import com.chua.evergrocery.database.service.DistributorService;
import com.chua.evergrocery.rest.handler.DistributorHandler;

@Transactional
@Component
public class DistributorHandlerImpl implements DistributorHandler {

	@Autowired
	private DistributorService distributorService;

	@Override
	public List<Distributor> getDistributorList() {
		return distributorService.findAllWithPaging(0, Application.ITEMS_PER_PAGE, null).getList();
	}

	@Override
	public Boolean removeDistributor(Long distributorId) {
		return distributorService.delete(distributorService.find(distributorId));
	}
}
