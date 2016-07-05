package com.chua.evergrocery.rest.handler.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.UserContextHolder;
import com.chua.evergrocery.beans.ResultBean;
import com.chua.evergrocery.beans.UserBean;
import com.chua.evergrocery.database.entity.User;
import com.chua.evergrocery.database.service.UserService;
import com.chua.evergrocery.rest.handler.SecurityHandler;

@Transactional
@Component
public class SecurityHandlerImpl implements SecurityHandler {
	
	@Autowired
	private UserService userService;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
	}

	@Override
	public UserBean getUser() {
		return UserContextHolder.getUser();
	}

	@Override
	public ResultBean authenticatePage(String page) {
		final User currentUser = userService.find(UserContextHolder.getUser().getUserId());
		final ResultBean result = new ResultBean();

		switch(currentUser.getUserType()) {
		case STAFF:
			if(page.equals("customerorder") || page.equals("customerorderpage")) result.setSuccess(true);
			else result.setSuccess(false);
			break;
		case CASHIER:
			if(page.equals("cashier")) result.setSuccess(true);
			else result.setSuccess(false);
			break;
		case ASSISTANT_MANAGER:
			if(page.contains(new StringBuilder("manage"))) result.setSuccess(false);
			else result.setSuccess(true);
			break;
		case MANAGER:
			if(page.equals("manage/user")) result.setSuccess(false);
			else result.setSuccess(true);
			break;
		default :
			result.setSuccess(true);
		}
		
		if(result.getSuccess()) {
			result.setMessage("Authentication success.");
		} else {
			result.setMessage("Authentication failed.");
		}
		
		return result;
	}
}
