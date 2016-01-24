define(['knockout', 'modules/productservice', 'viewmodels/forms/productform'], function (ko, productService, ProductForm) {
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
	
	Product.prototype.create = function() {
		var self = this;
		
		ProductForm.show('Create', null).then(function(response) {
			if(response) {
				self.refreshProductList();
			}
		});
	};
	
	Product.prototype.edit = function(productId) {
		ProductForm.show('Update', productId);
	};
	
	Product.prototype.remove = function(productId) {alert(productId);
		var self = this;
		
		productService.removeProduct(productId).done(function(data) {
			self.refreshProductList();
		});
	};
	
    return Product;
});