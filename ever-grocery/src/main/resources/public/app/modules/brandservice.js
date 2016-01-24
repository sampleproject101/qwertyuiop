define(['jquery'], function ($) {
    return {
    	getBrandList: function(searchKey) {
    		return $.ajax({
    			url: '/services/brand/list',
    			data: {
    				searchKey: searchKey
    			}
    		});
    	},
    	
    	getBrand: function(brandId) {
    		return $.ajax({
    			url: '/services/brand/get',
    			async: false,
    			data: {
    				brandId: brandId
    			}
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