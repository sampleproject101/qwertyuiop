package com.chua.evergrocery.database.service;

import com.chua.evergrocery.database.entity.Company;
import com.chua.evergrocery.database.prototype.CompanyPrototype;

public interface CompanyService
		extends Service<Company, Long>, CompanyPrototype
{

}
