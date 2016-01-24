define(['jquery'], function ($) {
    return {
    	getProductList: function() {
    		return $.ajax({
    			url: '/services/product/list'
    		});
    	},
    	
    	editProduct: function() {
    		
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