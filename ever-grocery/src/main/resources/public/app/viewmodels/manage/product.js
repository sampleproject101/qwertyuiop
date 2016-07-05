define(['durandal/app', 'knockout', 'modules/securityservice', 'modules/productservice', 'modules/companyservice', 'viewmodels/manage/productform', 'viewmodels/manage/productdetailsform'], function (app, ko, securityService, productService, companyService, ProductForm, ProductDetailsForm) {
	var Product = function() {
		this.productList = ko.observable();
		this.companyList = ko.observable();
		
		this.searchKey = ko.observable();
		this.companyId = ko.observable();
		
		this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
	};
	
	Product.prototype.canActivate = function() {
		var deferred = $.Deferred();
	    return deferred.then(securityService.authenticatePage('manage/product').done(function(result) {
	        if (result.success) {
	            deferred.resolve(result.success);
	        } else {
	        	app.showMessage(result.message);
	            deferred.resolve({ 'redirect': '/' });
	        }
	        return deferred.promise();
	    }));
	};
	
	Product.prototype.activate = function() {
		var self = this;
		
		companyService.getCompanyListByName().done(function(companyList) {
    		self.companyList(companyList);
    	});
		
		self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshProductList();
		});
		
		self.refreshProductList();
	};
	
	Product.prototype.refreshProductList = function() {
		var self = this;
		
		productService.getProductList(self.currentPage(), self.searchKey(), self.companyId(), true).done(function(data) {
			self.productList(data.list);
			self.totalItems(data.total);
		});
	};
	
	Product.prototype.search = function() {
		var self = this;
		
		self.currentPage(1);
		self.refreshProductList();
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
			productService.getProductDetailList(productId).done(function(data2) {
				ProductDetailsForm.show(data, data2).then(function() {
					self.refreshProductList();
				});	
			});
		});
	};
	
    return Product;
});