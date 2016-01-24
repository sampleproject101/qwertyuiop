define(['knockout', 'modules/productservice'], function (ko, productService) {
	var Product = function() {
		this.productList = ko.observable();
	};
	
	Product.prototype.activate = function() {
		this.refreshProductList();
	};
	
	Product.prototype.refreshProductList = function() {
		var self = this;
		
		productService.getProductList().done(function(data) {
			self.productList(data);
		});
	};
	
	Product.prototype.edit = function() {
		alert('edit');
	};
	
	Product.prototype.remove = function(productId) {alert(productId);
		var self = this;
		
		productService.removeProduct(productId).done(function(data) {
			self.refreshProductList();
		});
	};
	
    return Product;
});