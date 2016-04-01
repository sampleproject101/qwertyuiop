define(['durandal/app', 'knockout', 'modules/productservice'], function (app, ko, productService) {
	var Product = function() {
		this.productList = ko.observable();
		
		this.searchKey = ko.observable();
		
		this.itemsPerPage = ko.observable(app.user.itemsPerPage);
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
	
    return Product;
});