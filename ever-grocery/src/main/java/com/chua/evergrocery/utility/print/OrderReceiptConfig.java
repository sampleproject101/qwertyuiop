package com.chua.evergrocery.utility.print;

/**
 * @author Kevin Roy K. Chua
 *
 * @version 1.0, Jul 9, 2016
 * @since 1.0, Jul 9, 2016
 *
 */
public class OrderReceiptConfig
{
	private String title;
	private String website;
	private String telephone;
	private String branch;
	private String tin;
	
	public OrderReceiptConfig() {}
	
	public OrderReceiptConfig(String title, String website, String telephone, String branch, String tin) 
	{
		setTitle(title);
		setWebsite(website);
		setTelephone(telephone);
		setBranch(branch);
		setTin(tin);
	}
	
	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * @return the website
	 */
	public String getWebsite()
	{
		return website;
	}
	
	/**
	 * @return the telephone
	 */
	public String getTelephone()
	{
		return telephone;
	}
	
	/**
	 * @return the branch
	 */
	public String getBranch()
	{
		return branch;
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website)
	{
		this.website = website;
	}
	
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}
	
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch)
	{
		this.branch = branch;
	}

	/**
	 * @param tin the tin to set
	 */
	public void setTin(String tin) {
		this.tin = tin;
	}

	/**
	 * @return the tin
	 */
	public String getTin() {
		return tin;
	}
}
