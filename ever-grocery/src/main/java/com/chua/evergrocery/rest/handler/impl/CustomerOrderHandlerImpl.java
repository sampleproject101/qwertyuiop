package com.chua.evergrocery.rest.handler.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.UserContextHolder;
import com.chua.evergrocery.beans.CustomerOrderFormBean;
import com.chua.evergrocery.beans.ResultBean;
import com.chua.evergrocery.database.entity.CustomerOrder;
import com.chua.evergrocery.database.service.CustomerOrderService;
import com.chua.evergrocery.database.service.CustomerService;
import com.chua.evergrocery.database.service.UserService;
import com.chua.evergrocery.objects.ObjectList;
import com.chua.evergrocery.rest.handler.CustomerOrderHandler;

@Transactional
@Component
public class CustomerOrderHandlerImpl implements CustomerOrderHandler {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerOrderService customerOrderService;

	@Override
	public ObjectList<CustomerOrder> getCustomerOrderList(Integer pageNumber, String searchKey) {
		return customerOrderService.findAllWithPaging(pageNumber, UserContextHolder.getItemsPerPage(), searchKey);
	}
	
	@Override
	public CustomerOrder getCustomerOrder(Long customerOrderId) {
		return customerOrderService.find(customerOrderId);
	}
	
	@Override
	public ResultBean createCustomerOrder(CustomerOrderFormBean customerOrderForm) {
		final ResultBean result;
		
		if(!customerOrderService.isExistsByName(customerOrderForm.getName())) {
			final CustomerOrder customerOrder = new CustomerOrder();
			setCustomerOrder(customerOrder, customerOrderForm);
			
			//creator id
			
			result = new ResultBean();
			result.setSuccess(customerOrderService.insert(customerOrder) != null);
			if(result.getSuccess()) {
				result.setMessage("CustomerOrder successfully created.");
			} else {
				result.setMessage("Failed to create customerOrder.");
			}
		} else {
			result = new ResultBean(false, "CustomerOrder \"" + customerOrderForm.getName() + "\" already exists!");
		}
		
		return result;
	}
	
	@Override
	public ResultBean updateCustomerOrder(CustomerOrderFormBean customerOrderForm) {
		final ResultBean result;
		
		final CustomerOrder customerOrder = customerOrderService.find(customerOrderForm.getId());
		if(customerOrder != null) {
			if(!(StringUtils.trimToEmpty(customerOrder.getName()).equalsIgnoreCase(customerOrderForm.getName())) &&
					customerOrderService.isExistsByName(customerOrderForm.getName())) {
				result = new ResultBean(false, "CustomerOrder \"" + customerOrderForm.getName() + "\" already exists!");
			} else {
				setCustomerOrder(customerOrder, customerOrderForm);
				
				result = new ResultBean();
				result.setSuccess(customerOrderService.update(customerOrder));
				if(result.getSuccess()) {
					result.setMessage("CustomerOrder successfully updated.");
				} else {
					result.setMessage("Failed to update customerOrder.");
				}
			}
		} else {
			result = new ResultBean(false, "CustomerOrder not found.");
		}
		
		return result;
	}

	@Override
	public ResultBean removeCustomerOrder(Long customerOrderId) {
		final ResultBean result;
		
		final CustomerOrder customerOrder = customerOrderService.find(customerOrderId);
		if(customerOrder != null) {
			result = new ResultBean();
			
			result.setSuccess(customerOrderService.delete(customerOrder));
			if(result.getSuccess()) {
				result.setMessage("Successfully removed CustomerOrder \"" + customerOrder.getName() + "\".");
			} else {
				result.setMessage("Failed to remove CustomerOrder \"" + customerOrder.getName() + "\".");
			}
		} else {
			result = new ResultBean(false, "CustomerOrder not found.");
		}
		
		return result;
	}
	
	private void setCustomerOrder(CustomerOrder customerOrder, CustomerOrderFormBean customerOrderForm) {
		customerOrder.setName(customerOrderForm.getName());
		customerOrder.setCustomer(customerService.find(customerOrderForm.getCustomerId()));
		customerOrder.setCashier(userService.find(customerOrderForm.getCashierId()));
		customerOrder.setTotalAmount(customerOrderForm.getTotalAmount());
	}
}
