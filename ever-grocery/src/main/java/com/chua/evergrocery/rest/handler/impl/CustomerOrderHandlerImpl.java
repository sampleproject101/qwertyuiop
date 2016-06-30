package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.UserContextHolder;
import com.chua.evergrocery.beans.CustomerOrderFormBean;
import com.chua.evergrocery.beans.ResultBean;
import com.chua.evergrocery.database.entity.CustomerOrder;
import com.chua.evergrocery.database.entity.CustomerOrderDetail;
import com.chua.evergrocery.database.entity.ProductDetail;
import com.chua.evergrocery.database.service.CustomerOrderDetailService;
import com.chua.evergrocery.database.service.CustomerOrderService;
import com.chua.evergrocery.database.service.CustomerService;
import com.chua.evergrocery.database.service.ProductDetailService;
import com.chua.evergrocery.database.service.UserService;
import com.chua.evergrocery.enums.Status;
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
	
	@Autowired
	private CustomerOrderDetailService customerOrderDetailService;
	
	@Autowired
	private ProductDetailService productDetailService;

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
			customerOrder.setTotalAmount(0.0f);
			
			customerOrder.setCreator(userService.find(UserContextHolder.getUser().getUserId()));
			customerOrder.setStatus(Status.LISTING);
			
			result = new ResultBean();
			result.setSuccess(customerOrderService.insert(customerOrder) != null);
			if(result.getSuccess()) {
				result.setMessage("Customer order successfully created.");
			} else {
				result.setMessage("Failed to create customer order.");
			}
		} else {
			result = new ResultBean(false, "Customer order \"" + customerOrderForm.getName() + "\" already exists!");
		}
		
		return result;
	}
	
	@Override
	public ResultBean updateCustomerOrder(CustomerOrderFormBean customerOrderForm) {
		final ResultBean result;
		
		final CustomerOrder customerOrder = customerOrderService.find(customerOrderForm.getId());
		if(customerOrder != null) {
			if(customerOrder.getStatus() == Status.LISTING) {
				if(!(StringUtils.trimToEmpty(customerOrder.getName()).equalsIgnoreCase(customerOrderForm.getName())) &&
						customerOrderService.isExistsByName(customerOrderForm.getName())) {
					result = new ResultBean(false, "Customer order \"" + customerOrderForm.getName() + "\" already exists!");
				} else {
					setCustomerOrder(customerOrder, customerOrderForm);
					
					result = new ResultBean();
					result.setSuccess(customerOrderService.update(customerOrder));
					if(result.getSuccess()) {
						result.setMessage("Customer order successfully updated.");
					} else {
						result.setMessage("Failed to update customer order.");
					}
				}
			} else {
				result = new ResultBean(false, "Customer order cannot be edited right now.");
			}
		} else {
			result = new ResultBean(false, "Customer order not found.");
		}
		
		return result;
	}

	@Override
	public ResultBean removeCustomerOrder(Long customerOrderId) {
		final ResultBean result;
		
		final CustomerOrder customerOrder = customerOrderService.find(customerOrderId);
		if(customerOrder != null) {
			if(customerOrder.getStatus() == Status.LISTING) {
				result = new ResultBean();
				
				customerOrder.setStatus(Status.CANCELLED);
				
				result.setSuccess(customerOrderService.delete(customerOrder));
				if(result.getSuccess()) {
					result.setMessage("Successfully removed Customer order \"" + customerOrder.getName() + "\".");
				} else {
					result.setMessage("Failed to remove Customer order \"" + customerOrder.getName() + "\".");
				}
			} else {
				result = new ResultBean(false, "Customer order cannot be removed right now.");
			}
		} else {
			result = new ResultBean(false, "Customer order not found.");
		}
		
		return result;
	}
	
	private void setCustomerOrder(CustomerOrder customerOrder, CustomerOrderFormBean customerOrderForm) {
		customerOrder.setName(customerOrderForm.getName());
		customerOrder.setCustomer(customerService.find(customerOrderForm.getCustomerId()));
	}

	@Override
	public ObjectList<CustomerOrderDetail> getCustomerOrderDetailList(Integer pageNumber, Long customerOrderId) {
		return customerOrderDetailService.findAllWithPaging(pageNumber, UserContextHolder.getItemsPerPage(), customerOrderId);
	}

	@Override
	public ResultBean addItemByBarcode(String barcode, Long customerOrderId) {
		final ResultBean result;
		final CustomerOrder customerOrder = customerOrderService.find(customerOrderId);
		
		if(customerOrder != null) {
			if(customerOrder.getStatus() == Status.LISTING) {
				final ProductDetail productDetail = productDetailService.findByBarcode(barcode);
				
				if(barcode != null && barcode.length() > 4 && productDetail != null) {
					final CustomerOrderDetail customerOrderDetail = customerOrderDetailService.findByOrderAndDetailId(customerOrderId, productDetail.getId());
					
					if(customerOrderDetail == null) {
						final CustomerOrderDetail newCustomerOrderDetail = new CustomerOrderDetail();
						setCustomerOrderDetail(newCustomerOrderDetail, customerOrder, productDetail);
						setCustomerOrderDetailQuantity(newCustomerOrderDetail, 1);
						
						customerOrderDetailService.insert(newCustomerOrderDetail);
					} else {
						setCustomerOrderDetail(customerOrderDetail, customerOrder, productDetail);
						setCustomerOrderDetailQuantity(customerOrderDetail, customerOrderDetail.getQuantity() + 1);
						
						customerOrderDetailService.update(customerOrderDetail);
					}
					
					result = new ResultBean(true, "");
				} else {
					result = new ResultBean(false, "Barcode not found.");
				}
			} else {
				result = new ResultBean(false, "Customer order cannot be edited right now.");
			}
			
			refreshCustomerOrder(customerOrder);
		} else {
			result = new ResultBean(false, "Customer order not found.");
		}
		
		return result;
	}
	
	@Override
	public ResultBean removeCustomerOrderDetail(Long customerOrderDetailId) {
		final ResultBean result;
		
		final CustomerOrderDetail customerOrderDetail = customerOrderDetailService.find(customerOrderDetailId);
		if(customerOrderDetail != null) {
			final CustomerOrder customerOrder = customerOrderDetail.getCustomerOrder();
			if(customerOrder != null) {
				result = new ResultBean();
				
				result.setSuccess(customerOrderDetailService.delete(customerOrderDetail));
				refreshCustomerOrder(customerOrder);
				if(result.getSuccess()) {
					result.setMessage("Successfully removed item \"" + customerOrderDetail.getProductName() + " (" + customerOrderDetail.getUnitType() + ")\".");
				} else {
					result.setMessage("Failed to remove Customer order \"" + customerOrderDetail.getProductName() + " (" + customerOrderDetail.getUnitType() + ")\".");
				}
			} else {
				result = new ResultBean(false, "Customer order not found.");
			}
		} else {
			result = new ResultBean(false, "Item not found.");
		}
		
		return result;
	}
	
	@Override
	public void refreshCustomerOrder(Long customerOrderId) {
		refreshCustomerOrder(customerOrderService.find(customerOrderId));
	}
	
	private void refreshCustomerOrder(CustomerOrder customerOrder) {
		float totalAmount = 0l;
		
		List<CustomerOrderDetail> customerOrderDetails = customerOrderDetailService.findAllByCustomerOrderId(customerOrder.getId());
		
		for(CustomerOrderDetail customerOrderDetail : customerOrderDetails) {
			totalAmount += customerOrderDetail.getTotalPrice();
		}
		
		customerOrder.setTotalAmount(totalAmount);
		customerOrderService.update(customerOrder);
	}
	
	private void setCustomerOrderDetail(CustomerOrderDetail customerOrderDetail, CustomerOrder customerOrder, ProductDetail productDetail) {
		customerOrderDetail.setCustomerOrder(customerOrder);
		customerOrderDetail.setProductDetailId(productDetail.getId());
		customerOrderDetail.setProductName(productDetail.getProduct().getName());
		customerOrderDetail.setUnitType(productDetail.getUnitType());
		customerOrderDetail.setUnitPrice(productDetail.getSellingPrice());
	}
	
	private void setCustomerOrderDetailQuantity(CustomerOrderDetail customerOrderDetail, int quantity) {
		customerOrderDetail.setQuantity(quantity);
		customerOrderDetail.setTotalPrice(quantity * customerOrderDetail.getUnitPrice());
	}
}
