package com.chua.evergrocery.database.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chua.evergrocery.database.dao.CustomerOrderDAO;
import com.chua.evergrocery.database.entity.CustomerOrder;
import com.chua.evergrocery.database.service.CustomerOrderService;
import com.chua.evergrocery.enums.Status;
import com.chua.evergrocery.objects.ObjectList;

@Service
public class CustomerOrderServiceImpl
		extends AbstractService<CustomerOrder, Long, CustomerOrderDAO>
		implements CustomerOrderService 
{
	@Autowired
	protected CustomerOrderServiceImpl(CustomerOrderDAO dao) {
	super(dao);
	}
	
	@Override
	public ObjectList<CustomerOrder> findAllWithPagingAndDayLimit(int pageNumber, int resultsPerPage, String searchKey, int daysAgo) {
		return dao.findAllWithPagingAndDayLimit(pageNumber, resultsPerPage, searchKey, daysAgo);
	}
	
	@Override
	public ObjectList<CustomerOrder> findAllWithPagingAndStatus(int pageNumber, int resultsPerPage, String searchKey, Status[] status) {
	return dao.findAllWithPagingAndStatus(pageNumber, resultsPerPage, searchKey, status);
	}
	
	@Override
	public Boolean isExistsByNameAndStatus(String name, Status[] status) {
	return dao.findByNameAndStatus(StringUtils.trimToEmpty(name), status) != null;
	}
}
