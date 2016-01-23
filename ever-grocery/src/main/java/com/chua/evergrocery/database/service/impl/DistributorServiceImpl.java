package com.chua.evergrocery.database.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chua.evergrocery.database.dao.DistributorDAO;
import com.chua.evergrocery.database.entity.Distributor;
import com.chua.evergrocery.database.service.DistributorService;

@Service
public class DistributorServiceImpl
		extends AbstractService<Distributor, Long, DistributorDAO>
		implements DistributorService {

	@Autowired
	private DistributorDAO distributorDao;
	
	@PostConstruct
	public void postConstruct() {
	super.setDao(distributorDao);
	}
}
