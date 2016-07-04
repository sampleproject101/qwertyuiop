package com.chua.evergrocery.rest.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chua.evergrocery.UserContextHolder;
import com.chua.evergrocery.beans.PurchaseOrderFormBean;
import com.chua.evergrocery.beans.ResultBean;
import com.chua.evergrocery.database.entity.PurchaseOrder;
import com.chua.evergrocery.database.service.CompanyService;
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
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;

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
}
