package com.chua.evergrocery.rest.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chua.evergrocery.beans.ResultBean;
import com.chua.evergrocery.beans.UserBean;

public interface SecurityHandler {

	void logout(HttpServletRequest request, HttpServletResponse response);
	
	UserBean getUser();
	
	ResultBean authenticatePage(String page);
}
