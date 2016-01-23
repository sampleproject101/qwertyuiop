define(['jquery'], function ($) {
    return {
    	getDistributorList: function() {
    		return $.ajax({
    			url: '/services/distributor/list'
    		});
    	},
    	
    	editDistributor: function() {
    		
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