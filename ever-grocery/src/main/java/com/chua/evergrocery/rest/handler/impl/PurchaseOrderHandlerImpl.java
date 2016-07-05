package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.UserContextHolder;
import com.chua.evergrocery.beans.PurchaseOrderFormBean;
import com.chua.evergrocery.beans.ResultBean;
import com.chua.evergrocery.database.entity.ProductDetail;
import com.chua.evergrocery.database.entity.PurchaseOrder;
import com.chua.evergrocery.database.entity.PurchaseOrderDetail;
import com.chua.evergrocery.database.service.CompanyService;
import com.chua.evergrocery.database.service.ProductDetailService;
import com.chua.evergrocery.database.service.PurchaseOrderDetailService;
import com.chua.evergrocery.database.service.PurchaseOrderService;
import com.chua.evergrocery.database.service.UserService;
import com.chua.evergrocery.enums.Status;
import com.chua.evergrocery.objects.ObjectList;
import com.chua.evergrocery.rest.handler.PurchaseOrderHandler;

@Transactional
@Component
public class PurchaseOrderHandlerImpl implements PurchaseOrderHandler {

	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private PurchaseOrderDetailService purchaseOrderDetailService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductDetailService productDetailService;

	@Override
	public ObjectList<PurchaseOrder> getCustomerOrderList(Integer pageNumber, Long companyId, Boolean showChecked) {
		if(showChecked) {
			return purchaseOrderService.findAllWithPaging(pageNumber,  UserContextHolder.getItemsPerPage(), companyId);
		} else {
			return this.getActivePurchaseOrderList(pageNumber, companyId);
		}
	}
	
	private ObjectList<PurchaseOrder> getActivePurchaseOrderList(Integer pageNumber, Long companyId) {
		return purchaseOrderService.findAllWithPagingAndStatus(pageNumber, UserContextHolder.getItemsPerPage(), companyId, new Status[] { Status.LISTING });
	}

	@Override
	public PurchaseOrder getPurchaseOrder(Long purchaseOrderId) {
		return purchaseOrderService.find(purchaseOrderId);
	}

	@Override
	public ResultBean createPurchaseOrder(PurchaseOrderFormBean purchaseOrderForm) {
		final ResultBean result;
		
		final PurchaseOrder purchaseOrder = new PurchaseOrder();
		setPurchaseOrder(purchaseOrder, purchaseOrderForm);
		purchaseOrder.setTotalAmount(0.0f);
		purchaseOrder.setTotalItems(0);
		
		purchaseOrder.setCreator(userService.find(UserContextHolder.getUser().getUserId()));
		purchaseOrder.setStatus(Status.LISTING);
		
		result = new ResultBean();
		result.setSuccess(purchaseOrderService.insert(purchaseOrder) != null);
		if(result.getSuccess()) {
			result.setMessage("Purchase order successfully created.");
		} else {
			result.setMessage("Failed to create purchase order.");
		}
		
		return result;
	}

	@Override
	public ResultBean removePurchaseOrder(Long purchaseOrderId) {
		final ResultBean result;
		
		final PurchaseOrder purchaseOrder = purchaseOrderService.find(purchaseOrderId);
		if(purchaseOrder != null) {
			if(purchaseOrder.getStatus() == Status.LISTING) {
				result = new ResultBean();
				
				purchaseOrder.setStatus(Status.CANCELLED);
				
				result.setSuccess(purchaseOrderService.delete(purchaseOrder));
				if(result.getSuccess()) {
					result.setMessage("Successfully removed Purchase order \"" + purchaseOrder.getId() + "(" + purchaseOrder.getCompany().getName() + ")\".");
				} else {
					result.setMessage("Failed to remove Purchase order \"" + purchaseOrder.getId() + " of " + purchaseOrder.getCompany().getName() + "\".");
				}
			} else {
				result = new ResultBean(false, "Purchase order cannot be removed right now.");
			}
		} else {
			result = new ResultBean(false, "Purchase order not found.");
		}
		
		return result;
	}
	
	private void setPurchaseOrder(PurchaseOrder purchaseOrder, PurchaseOrderFormBean purchaseOrderForm) {
		purchaseOrder.setCompany(companyService.find(purchaseOrderForm.getCompanyId()));
	}

	@Override
	public void refreshPurchaseOrder(Long purchaseOrderId) {
		this.refreshPurchaseOrder(purchaseOrderService.find(purchaseOrderId));
	}
	
	private void refreshPurchaseOrder(PurchaseOrder purchaseOrder) {
		float totalAmount = 0l;
		int totalItems = 0;
		
		List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailService.findAllByPurchaseOrderId(purchaseOrder.getId());
		
		for(PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails) {
			totalAmount += purchaseOrderDetail.getTotalPrice();
			totalItems += purchaseOrderDetail.getQuantity();
		}
		
		purchaseOrder.setTotalAmount(totalAmount);
		purchaseOrder.setTotalItems(totalItems);
		purchaseOrderService.update(purchaseOrder);
	}
	
	@Override
	public ObjectList<PurchaseOrderDetail> getPurchaseOrderDetailList(Integer pageNumber, Long purchaseOrderId) {
		return purchaseOrderDetailService.findAllWithPaging(pageNumber, UserContextHolder.getItemsPerPage(), purchaseOrderId);
	}
	
	@Override
	public ResultBean addItemByProductDetailId(Long productDetailId, Long purchaseOrderId, Integer quantity) {
		final ResultBean result;
		
		final ProductDetail productDetail = productDetailService.find(productDetailId);
		final PurchaseOrder purchaseOrder = purchaseOrderService.find(purchaseOrderId);
		
		if(purchaseOrder != null) {
			if(purchaseOrder.getStatus() == Status.LISTING) {
				if(productDetail != null) {
					result = this.addItem(productDetail, purchaseOrder, quantity);
				} else {
					result = new ResultBean(false, "Product not found.");
				}
			} else {
				result = new ResultBean(false, "Purchase order cannot be edited right now.");
			}
		} else {
			result = new ResultBean(false, "Purchase order not found.");
		}
		
		return result;
	}
	
	private ResultBean addItem(ProductDetail productDetail, PurchaseOrder purchaseOrder, Integer quantity) {
		final ResultBean result;
		
		result = new ResultBean();
		
		final PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
		setPurchaseOrderDetail(purchaseOrderDetail, purchaseOrder, productDetail);
		setPurchaseOrderDetailQuantity(purchaseOrderDetail, quantity);
		
		result.setSuccess(purchaseOrderDetailService.insert(purchaseOrderDetail) != null);
		
		if(result.getSuccess()) {
			result.setMessage("Successfully added item.");
		} else {
			result.setMessage("Failed to add item.");
		}
		
		return result;
	}
	
	@Override
	public ResultBean removePurchaseOrderDetail(Long purchaseOrderDetailId) {
		final ResultBean result;
		final PurchaseOrderDetail purchaseOrderDetail = purchaseOrderDetailService.find(purchaseOrderDetailId);
		
		if(purchaseOrderDetail != null) {
			final PurchaseOrder purchaseOrder = purchaseOrderDetail.getPurchaseOrder();
			if(purchaseOrder != null) {
				if(purchaseOrder.getStatus() == Status.LISTING) {
					result = this.removePurchaseOrderDetail(purchaseOrderDetail);
				} else {
					result = new ResultBean(false, "Purchase order cannot be edited right now.");
				}
			} else {
				result = new ResultBean(false, "Purchase order not found.");
			}
		} else {
			result = new ResultBean(false, "Item not found.");
		}
		
		return result;
	}
	
	private ResultBean removePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
		final ResultBean result;
		
		final PurchaseOrder purchaseOrder = purchaseOrderDetail.getPurchaseOrder();
		result = new ResultBean();
		
		result.setSuccess(purchaseOrderDetailService.erase(purchaseOrderDetail));
		refreshPurchaseOrder(purchaseOrder);
		if(result.getSuccess()) {
			result.setMessage("Successfully removed item \"" + purchaseOrderDetail.getProductName() + " (" + purchaseOrderDetail.getUnitType() + ")\".");
		} else {
			result.setMessage("Failed to remove Purchase order \"" + purchaseOrderDetail.getProductName() + " (" + purchaseOrderDetail.getUnitType() + ")\".");
		}
		
		return result;
	}
	
	@Override
	public ResultBean changePurchaseOrderDetailQuantity(Long purchaseOrderDetailId, Integer quantity) {
		final ResultBean result;
		
		final PurchaseOrderDetail purchaseOrderDetail = purchaseOrderDetailService.find(purchaseOrderDetailId);
		
		if(purchaseOrderDetail != null) {
			final PurchaseOrder purchaseOrder = purchaseOrderDetail.getPurchaseOrder();
			if(purchaseOrder != null) {
				if(purchaseOrder.getStatus() == Status.LISTING) {
					result = this.changePurchaseOrderDetailQuantity(purchaseOrderDetail, quantity);
				} else {
					result =  new ResultBean(false, "Purchase order cannot be edited right now.");
				}
			} else {
				result = new ResultBean(false, "Purchase order not found.");
			}
		} else {
			result = new ResultBean(false, "Purchase order detail not found.");
		}
		
		return result;
	}
	
	private ResultBean changePurchaseOrderDetailQuantity(PurchaseOrderDetail purchaseOrderDetail, Integer quantity) {
		final ResultBean result;
		
		if(quantity > 0) {
			result = new ResultBean();
			
			setPurchaseOrderDetailQuantity(purchaseOrderDetail, quantity);
			result.setSuccess(purchaseOrderDetailService.update(purchaseOrderDetail));
			
			refreshPurchaseOrder(purchaseOrderDetail.getPurchaseOrder());
			
			if(result.getSuccess()) {
				result.setMessage("Quantity successfully updated.");
			} else {
				result.setMessage("Failed to update quantity.");
			}
		} else {
			result = this.removePurchaseOrderDetail(purchaseOrderDetail);
		}
		
		return result;
	}
	
	private void setPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail, PurchaseOrder purchaseOrder, ProductDetail productDetail) {
		purchaseOrderDetail.setPurchaseOrder(purchaseOrder);
		purchaseOrderDetail.setProductDetail(productDetail);
		purchaseOrderDetail.setProductName(productDetail.getProduct().getName());
		purchaseOrderDetail.setUnitType(productDetail.getUnitType());
		purchaseOrderDetail.setGrossPrice(productDetail.getGrossPrice());
		purchaseOrderDetail.setNetPrice(productDetail.getNetPrice());
		purchaseOrderDetail.setQuantity(0);
		purchaseOrderDetail.setTotalPrice(0.0f);
	}
	
	private void setPurchaseOrderDetailQuantity(PurchaseOrderDetail purchaseOrderDetail, int quantity) {
		purchaseOrderDetail.setQuantity(quantity);
		purchaseOrderDetail.setTotalPrice(quantity * purchaseOrderDetail.getNetPrice());
	}
}
