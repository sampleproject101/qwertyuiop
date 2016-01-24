define(['jquery'], function ($) {
    return {
    	getDistributorList: function() {
    		return $.ajax({
    			url: '/services/distributor/list'
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