define(['durandal/app', 'knockout', 'modules/productservice', 'viewmodels/manage/productform', 'viewmodels/manage/productdetailsform'], function (app, ko, productService, ProductForm, ProductDetailsForm) {
	var Product = function() {
		this.productList = ko.observable();
		
		this.searchKey = ko.observable();
		
		this.itemsPerPage = ko.observable(app.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
	};
	
	Product.prototype.activate = function() {
		var self = this;
		
		self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshProductList();
		});
		
		self.refreshProductList();
	};
	
	Product.prototype.refreshProductList = function() {
		var self = this;
		
		productService.getProductList(self.currentPage(), self.searchKey()).done(function(data) {
			self.productList(data.list);
			self.totalItems(data.total);
		});
	};
	
	Product.prototype.create = function() {
		var self = this;
		
		ProductForm.show('Create', new Object()).then(function() {
			self.refreshProductList();
		});
	};
	
	Product.prototype.edit = function(productId) {
		var self = this;
		
		productService.getProduct(productId).done(function(data) {
			ProductForm.show('Update', data).then(function() {
				self.refreshProductList();
			});
		});
	};
	
	Product.prototype.remove = function(productId, productName) {
		var self = this;
		
		app.showMessage('Are you sure you want to remove Product "' + productName + '"?',
				'Confirm Remove',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				productService.removeProduct(productId).done(function(result) {
					self.refreshProductList();
					app.showMessage(result.message);
				});
			}
		})
	};
	
	Product.prototype.details = function(productId) {
		var self = this;
		
		productService.getProduct(productId).done(function(data) {
			ProductDetailsForm.show(data).then(function(response) {
				if(response != undefined) {
					if(response) {
						app.showMessage('Product details successfully managed.');
						self.refreshProductList();
					} else {
						app.showMessage('Failed to manage product.');
					}
				}
			});
		});
	};
	
    return Product;
});