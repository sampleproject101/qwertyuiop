define(['jquery'], function ($) {
    return {
    	getCompanyList: function(searchKey) {
    		return $.ajax({
    			url: '/services/company/list',
				data: {
    				searchKey: searchKey
    			}
    		});
    	},
    	
    	getCompany: function(companyId) {
    		return $.ajax({
    			url: '/services/company/get',
    			data: {
    				companyId: companyId
    			}
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