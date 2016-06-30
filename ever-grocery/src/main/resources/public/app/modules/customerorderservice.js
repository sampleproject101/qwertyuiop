define(['jquery'], function ($) {
    return {
    	getCustomerOrderList: function(pageNumber, searchKey) {
    		return $.ajax({
    			url: '/services/customerorder/list',
    			data: {
    				pageNumber: pageNumber - 1,
    				searchKey: searchKey
    			}
    		});
    	},
    	
    	getCustomerOrder: function(customerOrderId) {
    		return $.ajax({
    			url: '/services/customerorder/get',
    			data: {
    				customerOrderId: customerOrderId
    			}
    		});
    	},
    	
    	saveCustomerOrder: function(customerOrderFormData) {
    		return $.ajax({
    			url: '/services/customerorder/save',
    			method: 'POST',
    			data: {
    				customerOrderFormData: customerOrderFormData
    			}
    		});
    	},
    	
    	removeCustomerOrder: function(customerOrderId) {
    		return $.ajax({
    			url: '/services/customerorder/remove',
    			method: 'POST',
    			data: {
    				customerOrderId: customerOrderId
    			}
    		});
    	},
    	
    	refreshCustomerOrder: function(customerOrderId) {
    		return $.ajax({
    			url: '/services/customerorder/refreshcustomerorder',
    			method: 'POST',
    			data: {
    				customerOrderId: customerOrderId
    			}
    		});
    	},
    	
    	getCustomerOrderDetailList: function(pageNumber, customerOrderId) {
    		return $.ajax({
    			url: '/services/customerorder/detaillist',
    			data: {
    				pageNumber: pageNumber - 1,
    				customerOrderId: customerOrderId
    			}
    		});
    	},
    	
    	addItemByBarcode: function(barcode, customerOrderId) {
    		return $.ajax({
    			url: '/services/customerorder/additembybarcode',
    			method: 'POST',
    			data: {
    				barcode: barcode,
    				customerOrderId: customerOrderId
    			}
    		});
    	},
    	
    	removeCustomerOrderDetail: function(customerOrderDetailId) {
    		return $.ajax({
    			url: '/services/customerorder/removeitem',
    			method: 'POST',
    			data: {
    				customerOrderDetailId: customerOrderDetailId
    			}
    		});
    	}
    };
});