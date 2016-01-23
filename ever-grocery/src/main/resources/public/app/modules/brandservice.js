define(['jquery'], function ($) {
    return {
    	getBrandList: function() {
    		return $.ajax({
    			url: '/services/brand/list'
    		});
    	},
    	
    	editBrand: function() {
    		
    	},
    	
    	removeBrand: function(brandId) {
    		return $.ajax({
    			url: '/services/brand/remove',
    			method: 'POST',
    			data: {
    				brandId: brandId
    			}
    		});
    	}
    };
});