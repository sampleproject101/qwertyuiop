package com.chua.evergrocery.utility.print;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class OrderList implements Printable {

	public static final int LINELENGTH = 36;
	private String orderNumber;
	
	private String creator;
	private List<OrderItem> orderItems;
	
	@Override
	public void print(VelocityEngine velocityEngine) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("orderList", this);
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "orderList.vm", "UTF-8", model);
		Printer printer = new Printer();
		printer.print(text, getOrderNumber(), "Order List " + getOrderNumber());
	}
	
	public OrderList(String creator, String orderNumber, List<OrderItem> orderItems) {
		this.creator = creator;
		this.orderNumber = orderNumber;
		this.orderItems = orderItems;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public List<OrderItem> getOrderItems()
	{	
		return orderItems;
	}
	
	public void setOrderItems(List<OrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}
}
