package com.chua.evergrocery.beans;

public class ResultBean {

	private Boolean success;
	
	private String message;
	
	public ResultBean() {
		
	}
	
	public ResultBean(Boolean success, String message) {
		setSuccess(success);
		setMessage(message);
	}
	
	public Boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
