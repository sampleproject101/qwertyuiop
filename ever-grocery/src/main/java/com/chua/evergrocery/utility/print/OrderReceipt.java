package com.chua.evergrocery.utility.print;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.app.VelocityEngine;
import org.joda.time.DateTime;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author Kevin Roy K. Chua
 *
 * @version 1.0, Jul 9, 2016
 * @since 1.0, Jul 9, 2016
 *
 */
public class OrderReceipt
		implements Printable
{
	public static final int LINELENGTH = 36;
	private DateTime dateTime;
	private String orderNumber;
	
	private String cashier;
	private String remarks;
	private String cashDue;
	private String totalAmount;
	private String customer;
	private OrderReceiptConfig config;
	
	public OrderReceipt(DateTime date, String cashier, String orderNumber, String customer, 
			String totalAmount, OrderReceiptConfig config, String remarks, String cashDue)
	{
		this.dateTime = date;
		this.cashier = cashier;
		this.orderNumber = orderNumber;
		this.totalAmount = totalAmount;
		this.customer = customer;
		this.config = config;
		this.remarks = remarks;
		this.cashDue = cashDue;
	}
	
	public OrderReceipt(DateTime date, String cashier, String orderNumber, String customer,
			String totalAmount, OrderReceiptConfig config, String remarks)
	{
		this.dateTime = date;
		this.cashier = cashier;
		this.orderNumber = orderNumber;
		this.totalAmount = totalAmount;
		this.customer = customer;
		this.config = config;
		this.remarks = remarks;
	}
	
	@Override
	public void print(VelocityEngine velocityEngine) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("orderReceipt", this);
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "orderReceipt.vm", "UTF-8", model);
		Printer printer = new Printer();
		//printer.print(text.getBytes(), kickDrawer, Boolean.TRUE);
		printer.print(text, getOrderNumber(), "Receipt " + getOrderNumber());
		/*if(PrintConstant.LOCAL_HOST.equalsIgnoreCase(RequestUtil.getRemoteComputerName(request)))
		{
			Printer printer = new Printer();
			printer.print(text.getBytes(), kickDrawer, Boolean.TRUE);
		}
		else
		{
			Printer printer = new Printer(request);
			printer.print(text.getBytes(), kickDrawer, Boolean.TRUE);
		}*/					
	}
	
	public String getTextToPrint(VelocityEngine velocityEngine, HttpServletRequest request, ServletContext servletContext) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("orderReceipt", this);
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "", "UTF-8", model);
		
		return text;
	}
	
	public String getFormattedHeader(){
		String header;		
		String title = config.getTitle();
		int startLine = 0;
		startLine = startLine + ((startLine%2 == 0)? 0:1);
		for(int i=0; i<startLine; i++){
			title = " " + title;
		}
		title += "\n";
		
		header = title;
		return header;
	}
	
	public String getFormattedDateTime()
	{
		if(dateTime == null)
		{
			dateTime = new DateTime();
		}
		
		String time = "";
		int center = 0;
		for(int i=0; i<center; i++){
			time = " " + time;
		}
		
		return time;
	}
	
	/**
	 * @return the formatted remarks
	 */
	public String getFormattedRemarks()
	{
		try {
			String formattedRemarks = "Remarks  " + ": " + remarks;
			return formattedRemarks;
		} catch(Exception e) {
			return "";
		}
	}
	
	public String getFormattedCustomer() {
		try {
			String formattedCashier = "Customer : " + cashier;
			return formattedCashier;
		}catch(Exception e) {
			return "";
		}
	}
	
	public String getFormattedOrderNumber() {
		try {
			String formattedOrderNumber = "Order #  : " + orderNumber;
			return formattedOrderNumber;
		} catch(Exception e) {
			return "";
		}
	}
	
	public String getFormattedCashDue()
	{
		String strAdded = "";
		
		strAdded = " "+ "Cash";
		int center = 0;
		center = LINELENGTH - (strAdded.length() + getFormattedCash().length() - 1 + 2);
		for(int i=0; i<center; i++)
		{
			strAdded += " ";
		}
		
		strAdded += getFormattedCash();
		
		strAdded += "\n " + "Change";
		
		center = 0;
		center = LINELENGTH - (6 + 6 + getFormattedChange().length() ) - 1;
		
		for(int i=0; i<center; i++)
			strAdded += " ";
		
		strAdded += getFormattedChange() + "\n";	
		
		return strAdded;
	}

	public String getFormattedCash()
	{
		String amount = cashDue+"";
		return amount;
		
	}
	
	public String getFormattedChange()
	{
		Float amount = Float.parseFloat(getTotalAmount());
		
		amount = Float.parseFloat(getCashDue()) - amount;
		String amountString = amount + "";
		return amountString;
	}
	
	public DateTime getDateTime()
	{
		return dateTime;
	}
	
	public void setDateTime(DateTime dateTime)
	{
		this.dateTime = dateTime;
	}
	
	public String getOrderNumber()
	{
		return orderNumber;
	}
	
	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}
	
	public String getCashier()
	{
		/*String cashier1 = cashier;
		int start = (LINELENGTH - cashier.length()) / 2;
		start = start + ((start%2 == 0)? 0:1);
		
		for(int i=0; i<start; i++)
			cashier1 = " " + cashier1;
		*/
		return cashier;
	}
	
	public void setCashier(String cashier)
	{
		this.cashier = cashier;
	}
	
	public String getTotalAmount()
	{
		return totalAmount;
	}
	
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/**
	 * @return the cashDue
	 */
	public String getCashDue() {
		return cashDue;
	}
	
	/**
	 * @param cashDue the cashDue to set
	 */
	public void setCashDue(String cashDue) {
		this.cashDue = cashDue;
	}
	
	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		
		if(remarks != null && remarks.length() < 100)
			return remarks.substring(0, 100);
		
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
