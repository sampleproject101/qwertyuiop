define(['jquery'], function ($) {
    return {
    	getProductList: function(pageNumber, searchKey) {
    		return $.ajax({
    			url: '/services/product/list',
    			data: {
    				pageNumber: pageNumber - 1,
    				searchKey: searchKey
    			}
    		});
    	},
    	
    	getProduct: function(productId) {
    		return $.ajax({
    			url: '/services/product/get',
    			data: {
    				productId: productId
    			}
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