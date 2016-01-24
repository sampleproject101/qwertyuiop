define(['jquery'], function ($) {
    return {
    	getBrandList: function() {
    		return $.ajax({
    			url: '/services/brand/list'
    		});
    	},
    	
    	saveBrand: function(brandFormData) {
    		return $.ajax({
    			url: '/services/brand/save',
    			method: 'POST',
    			data: {
    				brandFormData: brandFormData
    			}
    		});
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