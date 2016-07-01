define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/productservice', 'viewmodels/customer-order/searchdetails'], function (dialog, app, ko, productService, SearchDetails) {
	var Search = function(customerOrder) {
		this.customerOrder = customerOrder;
		this.productList = ko.observable();
		
		this.searchKey = ko.observable();
		
		this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
	};
	
	Search.prototype.activate = function() {
		var self = this;
		
		self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshProductList();
		});
		
		self.refreshProductList();
	};
	
	Search.show = function(customerOrder) {
		return dialog.show(new Search(customerOrder));
	};
	
	Search.prototype.refreshProductList = function() {
		var self = this;
		
		productService.getProductList(self.currentPage(), self.searchKey(), null).done(function(data) {
			self.productList(data.list);
			self.totalItems(data.total);
		});
	};
	
	Search.prototype.search = function() {
		var self = this;
		
		self.currentPage(1);
		self.refreshProductList();
	};
	
	Search.prototype.details = function(productId) {
		var self = this;

		productService.getProduct(productId).done(function (data) {
			SearchDetails.show(data, self.customerOrder);
		});
	};
	
	Search.prototype.cancel = function() {
        dialog.close(this);
    };
	
    return Search;
});