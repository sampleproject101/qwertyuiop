define(['knockout', 'jquery'], function (ko, $) {
    return {
    	companyList: ko.observable(),
    	
    	activate: function() {
    		var self = this;
    		
    		$.ajax({
    			url: '/services/company/list'
    		}).done(function(data) {
    			self.companyList(data);
    		});
    	}
    };
});