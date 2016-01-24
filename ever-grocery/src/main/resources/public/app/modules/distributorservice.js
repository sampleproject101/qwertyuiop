define(['jquery'], function ($) {
    return {
    	getDistributorList: function(searchKey) {
    		return $.ajax({
    			url: '/services/distributor/list',
    			data: {
    				searchKey: searchKey
    			}
    		});
    	},
    	
    	getDistributor: function(distributorId) {
    		return $.ajax({
    			url: '/services/distributor/get',
    			data: {
    				distributorId: distributorId
    			}
    		});
    	},
    	
    	saveDistributor: function(distributorFormData) {
    		return $.ajax({
    			url: '/services/distributor/save',
    			method: 'POST',
    			data: {
    				distributorFormData: distributorFormData
    			}
    		});
    	},
    	
    	removeDistributor: function(distributorId) {
    		return $.ajax({
    			url: '/services/distributor/remove',
    			method: 'POST',
    			data: {
    				distributorId: distributorId
    			}
    		});
    	}
    };
});