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
    	}
    };
});