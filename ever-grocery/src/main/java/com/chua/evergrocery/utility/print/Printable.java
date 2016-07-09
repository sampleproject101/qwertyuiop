package com.chua.evergrocery.utility.print;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.app.VelocityEngine;

/**
 * @author Kevin Roy K. Chua
 *
 * @version 1.0, Jul 9, 2016
 * @since 1.0, Jul 9, 2016
 *
 */
public interface Printable
{
	/**
	 * 
	 * @param velocityEngine
	 * @param kickDrawer
	 * @param request
	 * @param servletContext
	 * @throws Exception
	 */
	public void print(VelocityEngine velocityEngine, Boolean kickDrawer, HttpServletRequest request, ServletContext servletContext) throws Exception;
}
