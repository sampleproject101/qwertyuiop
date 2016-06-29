package com.chua.evergrocery.database.service;

import com.chua.evergrocery.database.entity.CustomerOrderDetail;
import com.chua.evergrocery.database.prototype.CustomerOrderDetailPrototype;

public interface CustomerOrderDetailService 
		extends Service<CustomerOrderDetail, Long>, CustomerOrderDetailPrototype {

}
