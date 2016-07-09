package com.chua.evergrocery.utility.print;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.joda.time.DateTime;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.chua.evergrocery.database.entity.Customer;

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
	private String pn;
	private DateTime dateTime;
	private String orderNumber;
	
	private List<OrderItem> orderItems;		
	private String cashier;
	private String paymentMode;
	private String dispatchMode;
	private String source;
	private String remarks;
	private String clinician;
	private String cashDue;
	private Customer customer;
	private OrderReceiptConfig config;
	private String customMessage;
	
	public OrderReceipt(String pn, DateTime date, String cashier, String orderNumber, Customer customer, 
			String paymentMode, String dispatchMode, String clinician, List<OrderItem> orderItems, 
			OrderReceiptConfig config, String source, String remarks, String cashDue)
	{
		this.pn = pn;
		this.dateTime = date;
		this.cashier = cashier;
		this.orderNumber = orderNumber;
		this.paymentMode = paymentMode;
		this.dispatchMode = dispatchMode;
		this.orderItems = orderItems;	
		this.customer = customer;
		this.config = config;
		this.source = source;
		this.remarks = remarks;
		this.clinician = clinician;
		this.cashDue = cashDue;
	}
	
	public OrderReceipt(String pn, DateTime date, String cashier, String orderNumber, Customer customer, 
			String paymentMode, String dispatchMode, String clinician, List<OrderItem> orderItems, 
			OrderReceiptConfig config, String source, String remarks, String cashDue, String customMessage)
	{
		this.pn = pn;
		this.dateTime = date;
		this.cashier = cashier;
		this.orderNumber = orderNumber;
		this.paymentMode = paymentMode;
		this.dispatchMode = dispatchMode;
		this.orderItems = orderItems;	
		this.customer = customer;
		this.config = config;
		this.source = source;
		this.remarks = remarks;
		this.clinician = clinician;
		this.cashDue = cashDue;
		this.customMessage = customMessage;
	}
	
	
	public OrderReceipt(String pn, DateTime date, String cashier, String orderNumber, Customer customer, 
			String paymentMode, String dispatchMode, String clinician, List<OrderItem> orderItems, 
			OrderReceiptConfig config, String source, String remarks)
	{
		this.pn = pn;
		this.dateTime = date;
		this.cashier = cashier;
		this.orderNumber = orderNumber;
		this.paymentMode = paymentMode;
		this.dispatchMode = dispatchMode;
		this.orderItems = orderItems;	
		this.customer = customer;
		this.config = config;
		this.source = source;
		this.remarks = remarks;
		this.clinician = clinician;
	}
	
	public OrderReceipt(String pn, DateTime date, String cashier, String orderNumber, 
			Customer customer, String paymentMode, String dispatchMode, List<OrderItem> orderItems)
	{
		this.pn = pn;
		this.dateTime = date;
		this.cashier = cashier;
		this.orderNumber = orderNumber;
		this.paymentMode = paymentMode;
		this.dispatchMode = dispatchMode;
		this.orderItems = orderItems;	
		this.customer = customer;
	}
	
	public OrderReceipt(String pn, DateTime dateTime, String orderNumber, String cashier, List<OrderItem> orderItems)
	{
		this.pn = pn;
		this.dateTime = dateTime;
		this.orderNumber = orderNumber;
		this.cashier = cashier;
		this.orderItems = orderItems;
	}
	
	/**
	 * 
	 * @return the pn
	 */
	public String getPn()
	{
		//trim pn to 5 characters and if number show otherwise do not show
		if(StringUtils.isNotBlank(StringUtils.trimToEmpty(pn)))
		{			
			if(pn.length() > 5)
			{
				pn = pn.substring(0, 5);
			}
			
			/*int index = 0;
			if(pn.length() > 5)
			{
				index = 5;
			}
			else
			{
				index = pn.length();
			}
			
			String temp = "";
			for(int c = index; c >= 1; c--)
			{
				temp = pn.substring(0, c);
				try {
					Integer.parseInt(temp);
					break;
				} catch(Exception e) {
					temp = "";
				}
			}
			pn = temp;*/
		}
		return pn;
	}
	
	/**
	 * 
	 * @param pn the pn
	 */
	public void setPn(String pn)
	{
		this.pn = pn;
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
	
	public List<OrderItem> getOrderItems()
	{
		for(OrderItem o: orderItems)
		{
			String name = o.getName();
			if(name.length() > 25)
			{
				//o.setName(name.substring(0, 22)+"...");
			}
			else if(name.length() < 25)
			{
				while(name.length() < 25)
				{
					name += " ";
				}
				//o.setName(name);
			}
		}		
		return orderItems;
	}
	
	public void setOrderItems(List<OrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}	
	
	public Float getTotalAmount()
	{
		Float totalAmount = 0F;
		for(OrderItem orderItem : getOrderItems())
		{
			totalAmount += orderItem.getGrossAmount() - orderItem.getAddedtDiscountAmount();
			//totalAmount += orderItem.getGrossAmount() - orderItem.getAddedtDiscountAmount() - orderItem.getAdjustment();
		}
		return totalAmount;
	}
	
	public String getFormattedTotalAmount()
	{	
		String amount = getTotalAmount()+"";
		return amount;
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
	
	public String getFormattedCashDue()
	{
		String strAdded = "";
		
		if(getPaymentMode().equalsIgnoreCase("CASH") && cashDue != null)
		{
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
			
		}		
		
		return strAdded;
	}

	public String getFormattedCash()
	{
		String amount = cashDue+"";
		return amount;
		
	}
	
	public String getFormattedChange()
	{
		Float amount = getTotalAmount();
		
		amount = Float.parseFloat(getCashDue()) - amount;
		String amountString = amount + "";
		return amountString;
	}
	
	@Override
	public void print(VelocityEngine velocityEngine, Boolean kickDrawer, HttpServletRequest request, ServletContext servletContext) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("orderReceipt", this);
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "", "UTF-8", model);
		Printer printer = new Printer(request, servletContext);
		//printer.print(text.getBytes(), kickDrawer, Boolean.TRUE);
		printer.print(text, getOrderNumber(), "Receipt " + getOrderNumber(), kickDrawer);
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
	
	public String getTextToPrint(VelocityEngine velocityEngine, Boolean kickDrawer, HttpServletRequest request, ServletContext servletContext) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("orderReceipt", this);
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "", "UTF-8", model);
		
		return text;
	}
	
	public String getFormattedHeader(){
		String header;		
		String title = config.getTitle();
		String branch = config.getBranch();
		String telephone = "Tel. No." + " : " + config.getTelephone();
		String website = config.getWebsite();
		String tin = "";
		int startLine = 0;
		startLine = startLine + ((startLine%2 == 0)? 0:1);
		for(int i=0; i<startLine; i++){
			title = " " + title;
		}
		title += "\n";
		
		startLine = startLine + ((startLine%2 == 0)? 0:1);
		for(int i=0; i<startLine; i++){
			branch = " " + branch;
		}
		branch += "\n";
		
		startLine = startLine + ((startLine%2 == 0)? 0:1);
		for(int i=0; i<startLine; i++){
			telephone = " " + telephone;
		}
		telephone += "\n";
		
		startLine = startLine + ((startLine%2 == 0)? 0:1);
		for(int i=0; i<startLine; i++){
			website = " " + website;
		}
		website += "\n";
		
		header = title + branch + telephone + tin + website;
		return header;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		String pm = "";
		if(paymentMode.indexOf("|") != -1)
			pm = paymentMode.split("\\|")[1];
		else pm = paymentMode;
		
		return pm;
	}

	/**
	 * @param dispatchMode the dispatchMode to set
	 */
	public void setDispatchMode(String dispatchMode) {
		this.dispatchMode = dispatchMode;
	}

	/**
	 * @return the dispatchMode
	 */
	public String getDispatchMode() {
		String dm = "";
		if(dispatchMode.indexOf("|") != -1)
			dm = dispatchMode.split("\\|")[1];
		else dm =  dispatchMode;
		return dm;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	 * @param clinician the clinician to set
	 */
	public void setClinician(String clinician) {
		this.clinician = clinician;
	}

	/**
	 * @return the clinician
	 */
	public String getClinician() {
		return clinician;
	}

	/**
	 * @param cashDue the cashDue to set
	 */
	public void setCashDue(String cashDue) {
		this.cashDue = cashDue;
	}

	/**
	 * @return the cashDue
	 */
	public String getCashDue() {
		return cashDue;
	}
	
	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}

	/**
	 * Format: Customer Name
	 * 
	 * @return the customer name
	 */
	public String getCustomerName()
	{
		try {
			String name =  customer.getFirstName();
			
			return name;
		} catch(Exception e) {
			return "";
		}
	}
	
	/**
	 * @return the formatted remarks
	 */
	public String getFormattedRemarks()
	{
		try {
			String formattedRemarks = "Remarks" + ": " + remarks;
			return formattedRemarks;
		} catch(Exception e) {
			return "";
		}
	}
}
