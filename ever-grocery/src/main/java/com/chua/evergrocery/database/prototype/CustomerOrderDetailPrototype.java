package com.chua.evergrocery.database.prototype;

import com.chua.evergrocery.database.entity.CustomerOrderDetail;
import com.chua.evergrocery.objects.ObjectList;

public interface CustomerOrderDetailPrototype {

	ObjectList<CustomerOrderDetail> findAllWithPaging(int pageNumber, int resultsPerPage, long customerOrderId);
	
	CustomerOrderDetail findByOrderAndDetailId(long customerOrderId, long productDetailId);
}
