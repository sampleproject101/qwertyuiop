package com.chua.evergrocery.utility.print;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Roy K. Chua
 *
 * @version 1.0, Jul 9, 2016
 * @since 1.0, Jul 9, 2016
 *
 */
public class OrderItem
{
	private Float unitPrice;
	private Float listPrice;
	private Float grossAmount;
	private Float defaultDiscount;
	private Float addedDiscount;	
	private Float adjustment;
	private List<String> nextLineList;
	private Boolean breakHere = Boolean.FALSE;
	private Boolean showDiscount;
	private Integer quantity;
	
	public OrderItem(String unitPrice, String listPrice, String grossAmount, String addedDiscount, String adjustment, Boolean showDiscount)
	{
		this.unitPrice = Float.parseFloat(unitPrice);
		this.listPrice = Float.parseFloat(listPrice);
		this.addedDiscount = Float.parseFloat(addedDiscount);
		this.adjustment = Float.parseFloat(adjustment);
		this.grossAmount = Float.parseFloat(grossAmount);
		this.showDiscount = showDiscount;
		this.quantity = (int) (getGrossAmount() / getUnitPrice());
		setDefaultDiscount();
	}

	public OrderItem(String unitPrice, String listPrice,
			String grossAmount, String addedDiscount, String adjustment,
			Boolean showDiscount, Double exchangeRate) {
		this.unitPrice = Float.parseFloat(unitPrice);
		this.listPrice = Float.parseFloat(listPrice);
		this.addedDiscount = Float.parseFloat(addedDiscount);
		this.adjustment = Float.parseFloat(adjustment);
		this.grossAmount = Float.parseFloat(grossAmount);
		this.showDiscount = showDiscount;
		this.quantity = (int) (getGrossAmount() / getUnitPrice());
		setDefaultDiscount();
	}

	private void setDefaultDiscount()
	{
		if(unitPrice.equals(listPrice))
		{
			this.defaultDiscount = 0F;
		}
		else
		{
			this.defaultDiscount = (100 * (listPrice - unitPrice)) / listPrice;
		}		
	}
	
	public String getName()
	{	
		String tmp = "item description";
		
		if(quantity > 1)
		{
			tmp += " X " + quantity;		
		}
		
		int length = tmp.length(), index = 25;
		nextLineList = new ArrayList<String> ();
		
		if(length > 25)
		{			
			try {
				while(length > index) {
					nextLineList.add(tmp.substring(index, 25 + index) + "\n");
					index += 25;
				}
			} catch(Exception e) {				
				nextLineList.add(tmp.substring(index) + "\n");
			}
			tmp = tmp.substring(0, 25);			
		}
		else
		{
			while(tmp.length() < 25)
			{
				tmp += " ";
			}
		}
		
		return tmp;
		
		/*String tmp = this.item.getImhDesc();
		tmp = tmp.trim();
		
		if(tmp.length() > 20)
		{
			tmp = tmp.substring(0, 17) + "...";			
		}
		else
		{
			while(tmp.length() < 20)
			{
				tmp += " ";
			}
		}
		
		return tmp;*/
	}
	
	public String getDisplayName(String name)
	{				
		StringBuffer tmp = new StringBuffer(name.trim());			
		if(tmp.length() > 26)
		{
			tmp = new StringBuffer(tmp.toString().substring(0, 23) + "...");			
		}
		else
		{
			while(tmp.length() < 26)
			{
				tmp.append(" ");				
			}
		}		
		return tmp.toString();
	}
	
	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setListPrice(Float listPrice) {
		this.listPrice = listPrice;
	}

	public Float getListPrice() {
		return listPrice;
	}

	public void setDefaultDiscount(Float defaultDiscount) {
		this.defaultDiscount = defaultDiscount;
	}

	public Float getDefaultDiscount() {
		return defaultDiscount;
	}

	public void setAddedDiscount(Float addedDiscount) {
		this.addedDiscount = addedDiscount;
	}

	public Float getAddedDiscount() {
		return addedDiscount;
	}
	
	public void setAdjustment(Float adjustment) {
		this.adjustment = adjustment;
	}

	public Float getAdjustment() {
		return adjustment;
	}
	
	public void setGrossAmount(Float grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Float getGrossAmount() {
		return grossAmount;
	}	
	
	public String getFormattedListPrice()
	{
		String formattedAmount = (listPrice * quantity) + "";
		
		return formattedAmount;
	}
	
	public String getFormattedGrossAmount()
	{
		String formattedAmount = "";
		
		return formattedAmount;
	}
	
	public String getFormattedDefaultDiscount()
	{
		String discount = "";
		return discount;		
	}
	
	public Float getDefaultDiscountAmount()
	{
		return quantity * (listPrice * (defaultDiscount/100) );
	}

	public String getFormattedDefaultDiscountAmount()
	{
		String discount = "";
		return discount;
	}
	
	public String getFormattedAddedDiscount()
	{
		String discount = "";
		return discount;
	}
	
	public Float getAddedtDiscountAmount()
	{
		return (grossAmount * (addedDiscount/100) );
	}
	
	public String getFormattedAddedDiscountAmount()
	{
		String discount = "";
		
		return discount;
	}	
		
	public String getTransaction()
	{
		String transaction = "";
		String tmpStr;
		if(showDiscount && listPrice >= unitPrice)
		{
			transaction = "" + getName() + "  " +  "" + getFormattedListPrice() + "\n";
			for(String nextLine : nextLineList)
			{
				transaction += nextLine;
			}
			
			if(defaultDiscount > 0 && addedDiscount > 0)
			{
				tmpStr = " " + "Less" + ": " +" " + getFormattedDefaultDiscount() + "     " ;
				transaction += tmpStr ;
				transaction += getFormattedDefaultDiscountAmount() + "\n";
				tmpStr ="       " + "Add'l Discount" + "t " + getFormattedAddedDiscount();
				//transaction += CountryUtil.addFillers(CountryUtil.CURRENT_COUNTRY.getReceiptLineLength() - tmpStr.length() - getFormattedAddedDiscountAmount().length());
				transaction += getFormattedAddedDiscountAmount() + "\n";
			}
			else if(defaultDiscount > 0)
			{
				tmpStr = " " + "Less" + ": " + "Discount" +" " + getFormattedDefaultDiscount() + "     " ;
				transaction += tmpStr ;
				transaction += getFormattedDefaultDiscountAmount() + "\n";				
			}
			else if(addedDiscount > 0)
			{
				tmpStr = " " + "Less" + ": " + "Discount" +" " + getFormattedAddedDiscount() +"     ";
				transaction += tmpStr; 
				transaction += getFormattedAddedDiscountAmount() + "\n";
			}
		}		
		else 
		{				
			transaction = getName() + "   " + this.getFormattedGrossAmount() + "\n";
			for(String nextLine : nextLineList)
			{
				transaction += nextLine;
			}
			//transaction += " Add'l Charge " + getFormattedDefaultDiscount() + "          " + getFormattedDefaultDiscountAmount() + "\n";
			if(showDiscount && addedDiscount > 0)
			{
				tmpStr = " " + "Less" + ": " + "Discount" +" " + getFormattedAddedDiscount() +"      ";
				transaction += tmpStr; 
				//transaction += CountryUtil.addFillers(CountryUtil.CURRENT_COUNTRY.getReceiptLineLength() - tmpStr.length() - getFormattedAddedDiscountAmount().length());		
				transaction += getFormattedAddedDiscountAmount() + "\n";
			}
		}
		
		if(breakHere)
		{
			transaction += "\n\n";
		}
		return transaction;
	}
	
	/**
	 * 
	 * @param breakHere the break control to separate lab from imaging tests
	 */
	public void setBreakHere(Boolean breakHere)
	{
		this.breakHere = breakHere;
	}
}