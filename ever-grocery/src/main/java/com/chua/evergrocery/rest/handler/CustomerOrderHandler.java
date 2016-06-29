package com.chua.evergrocery.rest.handler;

import com.chua.evergrocery.beans.CustomerOrderFormBean;
import com.chua.evergrocery.beans.ResultBean;
import com.chua.evergrocery.database.entity.CustomerOrder;
import com.chua.evergrocery.objects.ObjectList;

public interface CustomerOrderHandler {

	ObjectList<CustomerOrder> getCustomerOrderList(Integer pageNumber, String searchKey);
	
	CustomerOrder getCustomerOrder(Long customerOrderId);
	
	ResultBean createCustomerOrder(CustomerOrderFormBean customerOrderForm);
	
	ResultBean updateCustomerOrder(CustomerOrderFormBean customerOrderForm);
	
	ResultBean removeCustomerOrder(Long customerOrderId);
}