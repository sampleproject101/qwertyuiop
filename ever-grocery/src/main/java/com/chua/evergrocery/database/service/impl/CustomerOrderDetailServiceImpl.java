package com.chua.evergrocery.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chua.evergrocery.database.dao.CustomerOrderDetailDAO;
import com.chua.evergrocery.database.entity.CustomerOrderDetail;
import com.chua.evergrocery.database.service.CustomerOrderDetailService;
import com.chua.evergrocery.objects.ObjectList;

@Service
public class CustomerOrderDetailServiceImpl
		extends AbstractService<CustomerOrderDetail, Long, CustomerOrderDetailDAO>
		implements CustomerOrderDetailService{

	@Autowired
	protected CustomerOrderDetailServiceImpl(CustomerOrderDetailDAO dao) {
		super(dao);
	}
	
	@Override
	public ObjectList<CustomerOrderDetail> findAllWithPaging(int pageNumber, int resultsPerPage, long customerOrderId) {
		return dao.findAllWithPaging(pageNumber, resultsPerPage, customerOrderId);
	}

	@Override
	public CustomerOrderDetail findByOrderAndDetailId(long customerOrderId, long productDetailId) {
		return dao.findByOrderAndDetailId(customerOrderId, productDetailId);
	}	
}
