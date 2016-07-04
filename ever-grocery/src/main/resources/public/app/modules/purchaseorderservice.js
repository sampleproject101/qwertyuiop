define(['jquery'], function ($) {
	return {
		getPurchaseOrderList: function(pageNumber, companyId, showChecked, async) {
    		return $.ajax({
    			url: '/services/purchaseorder/list',
    			async: async,
    			data: {
    				pageNumber: pageNumber - 1,
    				companyId: companyId,
    				showChecked: showChecked
    			}
    		});
    	},
    	
    	getPurchaseOrder: function(purchaseOrderId) {
    		return $.ajax({
    			url: '/services/purchaseorder/get',
    			data: {
    				purchaseOrderId: purchaseOrderId
    			}
    		});
    	},
    	
    	savePurchaseOrder: function(purchaseOrderFormData) {
    		return $.ajax({
    			url: '/services/purchaseorder/save',
    			method: 'POST',
    			data: {
    				purchaseOrderFormData: purchaseOrderFormData
    			}
    		});
    	},
    	
    	removePurchaseOrder: function(purchaseOrderId) {
    		return $.ajax({
    			url: '/services/purchaseorder/remove',
    			method: 'POST',
    			data: {
    				purchaseOrderId: purchaseOrderId
    			}
    		});
    	}
	};
});