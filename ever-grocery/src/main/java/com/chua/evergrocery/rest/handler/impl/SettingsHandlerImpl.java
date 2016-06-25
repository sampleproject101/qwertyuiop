package com.chua.evergrocery.rest.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.UserContextHolder;
import com.chua.evergrocery.beans.PasswordBean;
import com.chua.evergrocery.beans.ResultBean;
import com.chua.evergrocery.beans.UserFormBean;
import com.chua.evergrocery.database.entity.User;
import com.chua.evergrocery.database.service.UserService;
import com.chua.evergrocery.rest.handler.SettingsHandler;
import com.chua.evergrocery.utility.EncryptionUtil;

@Transactional
@Component
public class SettingsHandlerImpl implements SettingsHandler {

	@Autowired
	private UserService userService;
	
	@Override
	public ResultBean updateSettings(UserFormBean userForm) {
		final ResultBean result;
		
		final User user = userService.find(1l);  //id from user context holder
		
		if(user != null) {
			user.setFirstName(userForm.getFirstName());
			user.setLastName(userForm.getLastName());
			user.setItemsPerPage(userForm.getItemsPerPage());
				
			result = new ResultBean();
			result.setSuccess(userService.update(user));
			if(result.getSuccess()) {
				UserContextHolder.refreshUser(user);
				result.setMessage("Your profile has been updated.");
			} else {
				result.setMessage("Failed to update your profile.");
			}
		} else {
			result = new ResultBean(false, "Internal Error: User not found!");
		}
		
		return result;
	}

	@Override
	public ResultBean changePassword(PasswordBean passwordBean) {
		final ResultBean result;
		
		final User user = userService.find(1l); // find by id from user context holder
		
		if(user != null) {
			if(EncryptionUtil.getMd5(passwordBean.getCurrentPassword()).equals(UserContextHolder.getUser().getPassword())) {
				if(passwordBean.getNewPassword().equals(passwordBean.getRePassword())) {
					user.setPassword(EncryptionUtil.getMd5(passwordBean.getNewPassword()));
					
					result = new ResultBean();
					result.setSuccess(userService.insert(user) != null);
					if(result.getSuccess()) {
						result.setMessage("User successfully created.");
					} else {
						result.setMessage("Failed to create user.");
					}
				} else {
					result = new ResultBean(false, "Password mismatch.");
				}
			} else {
				result = new ResultBean(false, "Wrong password.");
			}
		} else {
			result = new ResultBean(false, "Internal Error: User not found!");
		}
		
		return result;
	}
}
