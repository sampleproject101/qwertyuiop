define(['knockout', 'jquery'], function (ko, $) {
    return {
    	productList: ko.observable(),
    	
    	message: ko.observable('default value'),
    	
    	activate: function() {
    		var self = this;
    		
    		$.ajax({
    			url: '/services/product/list'
    		}).done(function(data) {
    			self.productList(data);
    		});
    	},
    	
    	buttonClicked: function() {
    		alert('clicked')
    	}
    };
});