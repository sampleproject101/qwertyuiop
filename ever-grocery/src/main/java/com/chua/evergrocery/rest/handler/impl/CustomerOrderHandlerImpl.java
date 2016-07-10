package com.chua.evergrocery.rest.handler.impl;

import java.text.DecimalFormat;
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
import com.chua.evergrocery.enums.UserType;
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
	public ObjectList<CustomerOrder> getCustomerOrderList(Integer pageNumber, String searchKey, Boolean showPaid, Integer daysAgo) {
		if(showPaid) {
			return customerOrderService.findAllWithPaging(pageNumber, UserContextHolder.getItemsPerPage(), searchKey, null, daysAgo);
		} else {
			return customerOrderService.findAllWithPaging(pageNumber, UserContextHolder.getItemsPerPage(), searchKey, new Status[] { Status.LISTING,  Status.PRINTED }, daysAgo);
		}
	}
	
	@Override
	public CustomerOrder getCustomerOrder(Long customerOrderId) {
		return customerOrderService.find(customerOrderId);
	}
	
	@Override
	public ResultBean createCustomerOrder(CustomerOrderFormBean customerOrderForm) {
		final ResultBean result;
		
		if(customerOrderForm.getName() != null) {
			if(!customerOrderService.isExistsByNameAndStatus(customerOrderForm.getName(), new Status[] { Status.LISTING, Status.PRINTED })) {
				final CustomerOrder customerOrder = new CustomerOrder();
				setCustomerOrder(customerOrder, customerOrderForm);
				customerOrder.setTotalAmount(0.0f);
				customerOrder.setTotalItems(0);
				
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
		} else {
			result = new ResultBean(false, "Input a name for the customer order.");
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
						customerOrderService.isExistsByNameAndStatus(customerOrderForm.getName(), new Status[] { Status.LISTING, Status.PRINTED })) {
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
			if(customerOrder.getStatus() == Status.LISTING || customerOrder.getStatus() == Status.PRINTED) {
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
	
	@Override
	public ResultBean payCustomerOrder(Long customerOrderId, Float cash) {
		final ResultBean result;
		final DecimalFormat df = new DecimalFormat("#.##");
		final CustomerOrder customerOrder = customerOrderService.find(customerOrderId);
		
		if(customerOrder != null) {
			if(customerOrder.getStatus() != Status.PRINTED) {
				if(customerOrder.getTotalAmount() <= cash) {
					result = new ResultBean();
					
					customerOrder.setCashier(userService.find(UserContextHolder.getUser().getUserId()));
					customerOrder.setStatus(Status.PAID);
					
					result.setSuccess(customerOrderService.update(customerOrder));
					if(result.getSuccess()) {
						//#############################################################################################		remove from stock!!!
						this.printReceipt(customerOrder);
						
						result.setMessage("CHANGE: Php " + df.format(cash - customerOrder.getTotalAmount()));
					} else {
						result.setMessage("Failed to pay Customer order \"" + customerOrder.getName() + "\".");
					}
				} else {
					result = new ResultBean(false, "Insufficient cash.");
				}
			} else {
				result = new ResultBean(false, "Customer order already paid or not yet printed.");
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
	public ResultBean removeCustomerOrderDetail(Long customerOrderDetailId) {
		final ResultBean result;
		final CustomerOrderDetail customerOrderDetail = customerOrderDetailService.find(customerOrderDetailId);
		
		if(customerOrderDetail != null) {
			final CustomerOrder customerOrder = customerOrderDetail.getCustomerOrder();
			if(customerOrder != null) {
				if(customerOrder.getStatus() == Status.LISTING) {
					result = this.removeCustomerOrderDetail(customerOrderDetail);
				} else {
					result = new ResultBean(false, "Customer order cannot be edited right now.");
				}
			} else {
				result = new ResultBean(false, "Customer order not found.");
			}
		} else {
			result = new ResultBean(false, "Item not found.");
		}
		
		return result;
	}
	
	private ResultBean removeCustomerOrderDetail(CustomerOrderDetail customerOrderDetail) {
		final ResultBean result;
		
		final CustomerOrder customerOrder = customerOrderDetail.getCustomerOrder();
		result = new ResultBean();
		
		result.setSuccess(customerOrderDetailService.erase(customerOrderDetail));
		refreshCustomerOrder(customerOrder);
		if(result.getSuccess()) {
			result.setMessage("Successfully removed item \"" + customerOrderDetail.getProductName() + " (" + customerOrderDetail.getUnitType() + ")\".");
		} else {
			result.setMessage("Failed to remove Customer order \"" + customerOrderDetail.getProductName() + " (" + customerOrderDetail.getUnitType() + ")\".");
		}
		
		return result;
	}

	@Override
	public ResultBean addItemByBarcode(String barcode, Long customerOrderId) {
		final ResultBean result;
		final ProductDetail productDetail = productDetailService.findByBarcode(barcode);
		
		if(barcode != null && barcode.length() > 4 && productDetail != null) {
			result = this.addItemByProductDetailId(productDetail.getId(), customerOrderId, 1);
		} else {
			result = new ResultBean(false, "Barcode not found.");
		}
		
		return result;
	}
	
	@Override
	public ResultBean addItemByProductDetailId(Long productDetailId, Long customerOrderId, Integer quantity) {
		final ResultBean result;
		
		final ProductDetail productDetail = productDetailService.find(productDetailId);
		final CustomerOrder customerOrder = customerOrderService.find(customerOrderId);
		
		if(customerOrder != null) {
			if(customerOrder.getStatus() == Status.LISTING) {
				if(productDetail != null) {
					result = this.addItem(productDetail, customerOrder, quantity);
				} else {
					result = new ResultBean(false, "Product not found.");
				}
			} else {
				result = new ResultBean(false, "Customer order cannot be edited right now.");
			}
		} else {
			result = new ResultBean(false, "Customer order not found.");
		}
		
		return result;
	}
	
	private ResultBean addItem(ProductDetail productDetail, CustomerOrder customerOrder, Integer quantity) {
		final ResultBean result;
		
		final CustomerOrderDetail customerOrderDetail = customerOrderDetailService.findByOrderAndDetailId(customerOrder.getId(), productDetail.getId());
		
		if(customerOrderDetail == null) {
			result = new ResultBean();
			
			final CustomerOrderDetail newCustomerOrderDetail = new CustomerOrderDetail();
			setCustomerOrderDetail(newCustomerOrderDetail, customerOrder, productDetail);
			
			result.setSuccess(customerOrderDetailService.insert(newCustomerOrderDetail) != null &&
					this.changeCustomerOrderDetailQuantity(newCustomerOrderDetail, quantity).getSuccess());
			
			if(result.getSuccess()) {
				result.setMessage("Successfully added item.");
			} else {
				result.setMessage("Failed to add item.");
			}
		} else {
			result = this.changeCustomerOrderDetailQuantity(customerOrderDetail, customerOrderDetail.getQuantity() + quantity);
		}
		
		return result;
	}
	
	@Override
	public ResultBean changeCustomerOrderDetailQuantity(Long customerOrderDetailId, Integer quantity) {
		final ResultBean result;
		
		final CustomerOrderDetail customerOrderDetail = customerOrderDetailService.find(customerOrderDetailId);
		
		if(customerOrderDetail != null) {
			final CustomerOrder customerOrder = customerOrderDetail.getCustomerOrder();
			if(customerOrder != null) {
				if(customerOrder.getStatus() == Status.LISTING) {
					result = this.changeCustomerOrderDetailQuantity(customerOrderDetail, quantity);
				} else {
					result =  new ResultBean(false, "Customer order cannot be edited right now.");
				}
			} else {
				result = new ResultBean(false, "Customer order not found.");
			}
		} else {
			result = new ResultBean(false, "Customer order detail not found.");
		}
		
		return result;
	}
	
	private ResultBean changeCustomerOrderDetailQuantity(CustomerOrderDetail customerOrderDetail, Integer quantity) {
		final ResultBean result;
		
		quantity = resolveCustomerOrderDetailUnitType(customerOrderDetail, quantity);
		
		if(quantity > 0) {
			result = new ResultBean();
			
			setCustomerOrderDetailQuantity(customerOrderDetail, quantity);
			result.setSuccess(customerOrderDetailService.update(customerOrderDetail));
			
			refreshCustomerOrder(customerOrderDetail.getCustomerOrder());
			
			if(result.getSuccess()) {
				result.setMessage("Quantity successfully updated.");
			} else {
				result.setMessage("Failed to update quantity.");
			}
		} else {
			result = this.removeCustomerOrderDetail(customerOrderDetail);
		}
		
		return result;
	}
	
	private int resolveCustomerOrderDetailUnitType(CustomerOrderDetail customerOrderDetail, Integer quantity) {
		final int result;
		final ProductDetail productDetail = customerOrderDetail.getProductDetail();
		
		if(quantity == null) quantity = 0;
		
		if(productDetail != null && quantity >= productDetail.getQuantity()) {
			final ProductDetail newProductDetail;
			switch(productDetail.getTitle()) {
			case "Piece":
				newProductDetail = productDetailService.findByProductIdAndTitle(productDetail.getProduct().getId(), "Whole");
				if(newProductDetail != null) {
					this.addItem(newProductDetail, customerOrderDetail.getCustomerOrder(), quantity / productDetail.getQuantity());
					result = quantity % productDetail.getQuantity();
				} else {
					result = quantity;
				}
				break;
			case "Inner Piece":
				newProductDetail = productDetailService.findByProductIdAndTitle(productDetail.getProduct().getId(), "Piece");
				if(newProductDetail != null) {
					this.addItem(newProductDetail, customerOrderDetail.getCustomerOrder(), quantity / productDetail.getQuantity());
					result = quantity % productDetail.getQuantity();
				} else {
					result = quantity;
				}
				break;
			case "2nd Inner Piece":
				newProductDetail = productDetailService.findByProductIdAndTitle(productDetail.getProduct().getId(), "Inner Piece");
				if(newProductDetail != null) {
					this.addItem(newProductDetail, customerOrderDetail.getCustomerOrder(), quantity / productDetail.getQuantity());
					result = quantity % productDetail.getQuantity();
				} else {
					result = quantity;
				}
				break;
			default:
				result = quantity;
			}
		} else {
			result = quantity;
		}
		
		return result;
	}
	
	@Override
	public void refreshCustomerOrder(Long customerOrderId) {
		this.refreshCustomerOrder(customerOrderService.find(customerOrderId));
	}
	
	private void refreshCustomerOrder(CustomerOrder customerOrder) {
		float totalAmount = 0l;
		int totalItems = 0;
		
		List<CustomerOrderDetail> customerOrderDetails = customerOrderDetailService.findAllByCustomerOrderId(customerOrder.getId());
		
		for(CustomerOrderDetail customerOrderDetail : customerOrderDetails) {
			totalAmount += customerOrderDetail.getTotalPrice();
			totalItems += customerOrderDetail.getQuantity();
		}
		
		customerOrder.setTotalAmount(totalAmount);
		customerOrder.setTotalItems(totalItems);
		customerOrderService.update(customerOrder);
	}
	
	@Override
	public ResultBean printCustomerOrderList(Long customerOrderId) {
		final ResultBean result;
		
		final CustomerOrder customerOrder = customerOrderService.find(customerOrderId);
		
		if(customerOrder != null) {
			if(customerOrder.getStatus() == Status.LISTING || UserContextHolder.getUser().getUserType() == UserType.ADMINISTRATOR ||
					UserContextHolder.getUser().getUserType() == UserType.MANAGER || UserContextHolder.getUser().getUserType() == UserType.ASSISTANT_MANAGER) {
				result = new ResultBean();
				
				if(customerOrder.getStatus() == Status.LISTING) {
					customerOrder.setStatus(Status.PRINTED);
					result.setSuccess(customerOrderService.update(customerOrder));
				} else {
					result.setSuccess(true);
				}
				
				if(result.getSuccess()) {
					System.out.println("Printing Order List......... " + customerOrderId);
					
					result.setMessage("Successfully printed Customer order \"" + customerOrder.getName() + "\".");
				} else {
					result.setMessage("Failed to print Customer order \"" + customerOrder.getName() + "\".");
				}
			} else {
				result = new ResultBean(false, "You are not authorized to print a copy.");
			}
		} else {
			result = new ResultBean(false, "Customer order not found.");
		}
		
		return result;
	}
	
	private void printReceipt(CustomerOrder customerOrder) {
		System.out.println("Printing Receipt......." + customerOrder.getId());
	}
	
	private void setCustomerOrderDetail(CustomerOrderDetail customerOrderDetail, CustomerOrder customerOrder, ProductDetail productDetail) {
		customerOrderDetail.setCustomerOrder(customerOrder);
		customerOrderDetail.setProductDetail(productDetail);
		customerOrderDetail.setProductName(productDetail.getProduct().getName());
		customerOrderDetail.setUnitType(productDetail.getUnitType());
		customerOrderDetail.setUnitPrice(productDetail.getSellingPrice());
		customerOrderDetail.setQuantity(0);
		customerOrderDetail.setTotalPrice(0.0f);
	}
	
	private void setCustomerOrderDetailQuantity(CustomerOrderDetail customerOrderDetail, int quantity) {
		customerOrderDetail.setQuantity(quantity);
		customerOrderDetail.setTotalPrice(quantity * customerOrderDetail.getUnitPrice());
	}
}
