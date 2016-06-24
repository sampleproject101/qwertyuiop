package com.chua.evergrocery.database.dao;

import com.chua.evergrocery.database.entity.CustomerOrder;
import com.chua.evergrocery.database.prototype.CustomerOrderPrototype;

public interface CustomerOrderDAO extends DAO<CustomerOrder, Long>, CustomerOrderPrototype {

	CustomerOrder findByName(String name);
}