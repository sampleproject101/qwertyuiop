package com.chua.evergrocery.database.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chua.evergrocery.database.dao.CustomerOrderDAO;
import com.chua.evergrocery.database.entity.CustomerOrder;
import com.chua.evergrocery.database.service.CustomerOrderService;
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
	public ObjectList<CustomerOrder> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey) {
	return dao.findAllWithPaging(pageNumber, resultsPerPage, searchKey);
	}
	
	@Override
	public Boolean isExistsByName(String name) {
	return dao.findByName(StringUtils.trimToEmpty(name)) != null;
	}
}
