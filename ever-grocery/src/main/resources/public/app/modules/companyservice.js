define(['jquery'], function ($) {
    return {
    	getCompanyList: function() {
    		return $.ajax({
    			url: '/services/company/list'
    		});
    	},
    	
    	saveCompany: function(companyFormData) {
    		return $.ajax({
    			url: '/services/company/save',
    			method: 'POST',
    			data: {
    				companyFormData: companyFormData
    			}
    		});
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