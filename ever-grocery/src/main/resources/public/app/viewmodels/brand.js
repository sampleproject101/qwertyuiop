define(['knockout', 'jquery'], function (ko, $) {
    return {
    	brandList: ko.observable(),
    	
    	activate: function() {
    		var self = this;
    		
    		$.ajax({
    			url: '/services/brand/list'
    		}).done(function(data) {
    			self.brandList(data);
    		});
    	}
    };
});