define(['durandal/app', 'knockout', 'modules/utility', 'modules/customerorderservice'], function (app, ko, util, customerOrderService) {
    var CustomerOrderPage = function(customerOrder) {
    	this.customerOrder = customerOrder;
    	
    	this.customerOrderDetailList = ko.observable();
    	
    	this.totalAmount = ko.observable();
    	
    	this.barcodeKey = ko.observable();
    	
    	this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
    };
    
    CustomerOrderPage.prototype.activate = function() {
    	var self = this;
    	
    	self.totalAmount(self.customerOrder.totalAmount());
    	
    	self.currentPage(util.getLastPage(self.itemsPerPage, self.totalItems));
    	self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshCustomerOrderDetailList();
		});
    	
    	self.refreshCustomerOrderDetailList();
    };
    
    CustomerOrderPage.prototype.refreshCustomerOrderDetailList = function() {
    	var self = this;
    	
    	customerOrderService.getCustomerOrderDetailList(self.currentPage(), self.customerOrder.id()).done(function(data) {
			self.customerOrderDetailList(data.list);
			self.totalItems(data.total);
		});
    };
    
    CustomerOrderPage.prototype.addItemByBarcode = function() {
    	var self = this;
    	
    	customerOrderService.addItemByBarcode(self.barcodeKey(), self.customerOrder.id()).done(function(result) {
    		self.currentPage(util.getLastPage(self.itemsPerPage, self.totalItems));
    		self.refreshCustomerOrderDetailList();
    		if(!result.success) {
    			app.showMessage(result.message);
    		}
    	});
    	
    	self.barcodeKey("");
    };
    
    return CustomerOrderPage;
});