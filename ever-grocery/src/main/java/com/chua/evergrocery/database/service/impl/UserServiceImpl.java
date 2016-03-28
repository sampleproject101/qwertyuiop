package com.chua.evergrocery.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chua.evergrocery.database.dao.UserDAO;
import com.chua.evergrocery.database.entity.User;
import com.chua.evergrocery.database.service.UserService;

@Service
public class UserServiceImpl 
		extends AbstractService<User, Long, UserDAO>
		implements UserService {

	@Autowired
	protected UserServiceImpl(UserDAO dao) {
		super(dao);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return dao.findByUsernameAndPassword(username, password);
	}
}
