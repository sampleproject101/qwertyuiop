define(['knockout', 'jquery'], function (ko, $) {
    return {
    	distributorList: ko.observable(),
    	
    	activate: function() {
    		var self = this;
    		
    		$.ajax({
    			url: '/services/distributor/list'
    		}).done(function(data) {
    			self.distributorList(data);
    		});
    	}
    };
});