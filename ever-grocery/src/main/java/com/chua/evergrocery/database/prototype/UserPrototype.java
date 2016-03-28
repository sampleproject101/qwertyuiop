package com.chua.evergrocery.database.prototype;

import com.chua.evergrocery.database.entity.User;

public interface UserPrototype {

	User findByUsernameAndPassword(String username, String password);
}
