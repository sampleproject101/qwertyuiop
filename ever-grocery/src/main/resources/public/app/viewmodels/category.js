define(['knockout', 'jquery'], function (ko, $) {
    return {
    	categoryList: ko.observable(),
    	
    	activate: function() {
    		var self = this;
    		
    		$.ajax({
    			url: '/services/category/list'
    		}).done(function(data) {
    			self.categoryList(data);
    		});
    	}
    };
});