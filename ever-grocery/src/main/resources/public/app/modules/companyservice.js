define(['jquery'], function ($) {
    return {
    	getCompanyList: function() {
    		return $.ajax({
    			url: '/services/company/list'
    		});
    	},
    	
    	editCompany: function() {
    		
    	},
    	
    	removeCompany: function(companyId) {
    		return $.ajax({
    			url: '/services/company/remove',
    			method: 'POST',
    			data: {
    				companyId: companyId
    			}
    		});
    	}
    };
});