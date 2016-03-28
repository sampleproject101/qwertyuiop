package com.chua.evergrocery.database.service;

import com.chua.evergrocery.database.entity.User;
import com.chua.evergrocery.database.prototype.UserPrototype;

public interface UserService
		extends Service<User, Long>, UserPrototype {

}
