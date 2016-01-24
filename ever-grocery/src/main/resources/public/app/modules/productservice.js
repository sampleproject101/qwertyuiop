define(['jquery'], function ($) {
    return {
    	getProductList: function() {
    		return $.ajax({
    			url: '/services/product/list'
    		});
    	},
    	
    	saveProduct: function(productFormData) {
    		return $.ajax({
    			url: '/services/product/save',
    			method: 'POST',
    			data: {
    				productFormData: productFormData
    			}
    		});
    	},
    	
    	removeProduct: function(productId) {
    		return $.ajax({
    			url: '/services/product/remove',
    			method: 'POST',
    			data: {
    				productId: productId
    			}
    		});
    	}
    };
});