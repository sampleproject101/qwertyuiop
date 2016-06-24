package com.chua.evergrocery.database.service;

import com.chua.evergrocery.database.entity.CustomerOrder;
import com.chua.evergrocery.database.prototype.CustomerOrderPrototype;

public interface CustomerOrderService 
		extends Service<CustomerOrder, Long>, CustomerOrderPrototype {
	
	Boolean isExistsByName(String name);
}
