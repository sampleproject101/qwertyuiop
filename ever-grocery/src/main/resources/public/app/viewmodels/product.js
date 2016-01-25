define(['durandal/app', 'knockout', 'modules/productservice', 'viewmodels/forms/productform'], function (app, ko, productService, ProductForm) {
	var Product = function() {
		this.productList = ko.observable();
		
		this.searchKey = ko.observable();
	};
	
	Product.prototype.activate = function() {
		this.refreshProductList();
	};
	
	Product.prototype.refreshProductList = function() {
		var self = this;
		
		productService.getProductList(self.searchKey()).done(function(data) {
			self.productList(data);
		});
	};
	
	Product.prototype.create = function() {
		var self = this;
		
		ProductForm.show('Create', this.newProduct()).then(function(response) {
			if(response != undefined) {
				if(response) {
					app.showMessage('Product successfully created.');
					self.refreshProductList();
				} else {
					app.showMessage('Failed to create product.');
				}
			}
		});
	};
	
	Product.prototype.edit = function(productId) {
		var self = this;
		
		productService.getProduct(productId).done(function(data) {
			ProductForm.show('Update', data).then(function(response) {
				if(response != undefined) {
					if(response) {
						app.showMessage('Product successfully updated.');
						self.refreshProductList();
					} else {
						app.showMessage('Failed to update product.');
					}
				}
			});
		});
	};
	
	Product.prototype.remove = function(productId) {alert(productId);
		var self = this;
		
		productService.removeProduct(productId).done(function(data) {
			self.refreshProductList();
		});
	};
	
	Product.prototype.newProduct = function() {
		var product = new Object();
		
		product.id = null;
		product.name = '';
		
		return product;
	};
	
    return Product;
});