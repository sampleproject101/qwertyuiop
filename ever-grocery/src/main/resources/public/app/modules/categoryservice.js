define(['jquery'], function ($) {
    return {
    	getCategoryList: function() {
    		return $.ajax({
    			url: '/services/category/list'
    		});
    	},
    	
    	editCategory: function() {
    		
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