define(['jquery'], function ($) {
    return {
    	getCategoryList: function() {
    		return $.ajax({
    			url: '/services/category/list'
    		});
    	},
    	
    	saveCategory: function(categoryFormData) {
    		return $.ajax({
    			url: '/services/category/save',
    			method: 'POST',
    			data: {
    				categoryFormData: categoryFormData
    			}
    		});
    	},
    	
    	removeCategory: function(categoryId) {
    		return $.ajax({
    			url: '/services/category/remove',
    			method: 'POST',
    			data: {
    				categoryId: categoryId
    			}
    		});
    	}
    };
});