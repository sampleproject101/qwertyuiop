package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.beans.ResultBean;
import com.chua.evergrocery.beans.UserFormBean;
import com.chua.evergrocery.database.entity.User;
import com.chua.evergrocery.database.service.UserService;
import com.chua.evergrocery.objects.ObjectList;
import com.chua.evergrocery.rest.handler.UserHandler;

@Transactional
@Component
public class UserHandlerImpl implements UserHandler {

	@Autowired
	private UserService userService;

	@Override
	public ObjectList<User> getUserObjectList(Integer pageNumber, String searchKey) {
		return userService.findAllWithPaging(pageNumber, Application.ITEMS_PER_PAGE, searchKey);
	}
	
	@Override
	public User getUser(Long userId) {
		return userService.find(userId);
	}
	
	@Override
	public ResultBean createUser(UserFormBean userForm) {
		final ResultBean result;
		
		if(!userService.isExistsByFirstName(userForm.getFirstName())) {
			final User user = new User();
			setUser(user, userForm);
			
			result = new ResultBean();
			result.setSuccess(userService.insert(user) != null);
			if(result.getSuccess()) {
				result.setMessage("User successfully created.");
			} else {
				result.setMessage("Failed to create user.");
			}
		} else {
			result = new ResultBean(false, "User \"" + userForm.getFirstName() + "\" already exists!");
		}
		
		return result;
	}
	
	@Override
	public ResultBean updateUser(UserFormBean userForm) {
		final ResultBean result;
		
		final User user = userService.find(userForm.getId());
		if(user != null) {
			if(!(StringUtils.trimToEmpty(user.getFirstName()).equalsIgnoreCase(userForm.getFirstName())) &&
					userService.isExistsByFirstName(userForm.getFirstName())) {
				result = new ResultBean(false, "User \"" + userForm.getFirstName() + "\" already exists!");
			} else {
				setUser(user, userForm);
				
				result = new ResultBean();
				result.setSuccess(userService.update(user));
				if(result.getSuccess()) {
					result.setMessage("User successfully updated.");
				} else {
					result.setMessage("Failed to update user.");
				}
			}
		} else {
			result = new ResultBean(false, "User not found.");
		}
		
		return result;
	}

	@Override
	public ResultBean removeUser(Long userId) {
		final ResultBean result;
		
		final User user = userService.find(userId);
		if(user != null) {
			result = new ResultBean();
			
			result.setSuccess(userService.delete(user));
			if(result.getSuccess()) {
				result.setMessage("Successfully removed User \"" + user.getFirstName() + "\".");
			} else {
				result.setMessage("Failed to remove User \"" + user.getFirstName() + "\".");
			}
		} else {
			result = new ResultBean(false, "User not found.");
		}
		
		return result;
	}
	
	@Override
	public List<User> getUserList() {
		return userService.findAllOrderByFirstName();
	}
	
	private void setUser(User user, UserFormBean userForm) {
		user.setFirstName(userForm.getFirstName());
	}
}
