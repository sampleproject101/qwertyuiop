define(['durandal/app', 'knockout', 'modules/utility', 'modules/customerorderservice', 'modules/customerservice', 'viewmodels/customer-order/search'], function (app, ko, util, customerOrderService, customerService, Search) {
    var CustomerOrderPage = function() {
    	this.customerOrderDetailList = ko.observable();
    	
    	this.barcodeKey = ko.observable();
    	
    	this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
		
		this.customerOrderPageModel = {
			customerOrderId: ko.observable(),
			totalAmount: ko.observable(),
			customerOrderName: ko.observable(),
			status: ko.observable()
		};
    };
    
    CustomerOrderPage.prototype.activate = function(customerOrderId) {
    	var self = this;
    	
    	self.customerOrderPageModel.customerOrderId(customerOrderId);
    	
    	self.currentPage(1);
    	self.currentPageSubscription = self.currentPage.subscribe(function() {
    		self.refreshCustomerOrderDetailList();
		});
    	
    	customerOrderService.refreshCustomerOrder(self.customerOrderPageModel.customerOrderId()).done(function() {
    		self.refreshCustomerOrderDetailList();
    	});
    };
    
    CustomerOrderPage.prototype.search = function() {
    	var self = this;
    	
    	customerOrderService.getCustomerOrder(self.customerOrderPageModel.customerOrderId()).done(function(data) { 
    		Search.show(data).done(function() {
        		self.refreshCustomerOrderDetailList();
        	});
    	});
    };
    
    CustomerOrderPage.prototype.refreshCustomerOrderDetailList = function() {
    	var self = this;
    	
    	customerOrderService.getCustomerOrder(self.customerOrderPageModel.customerOrderId()).done(function(data) { 
    		self.customerOrderPageModel.totalAmount(data.totalAmount);
    		self.customerOrderPageModel.customerOrderName(data.name);
    		self.customerOrderPageModel.status(data.status);
    	});
    	
    	customerOrderService.getCustomerOrderDetailList(self.currentPage(), self.customerOrderPageModel.customerOrderId()).done(function(data) { 
			self.customerOrderDetailList(data.list);
			self.totalItems(data.total);
		});
    };
    
    CustomerOrderPage.prototype.remove = function(customerOrderDetailId, quantity, productName, unitType) {
		var self = this;
		
		app.showMessage('Are you sure you want to remove ' + quantity + ' "' + productName + ' (' + unitType + ')"?',
				'Confirm Remove',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				customerOrderService.removeCustomerOrderDetail(customerOrderDetailId).done(function(result) {
					self.refreshCustomerOrderDetailList();
					app.showMessage(result.message);
				});
			}
		})
	};
    
    CustomerOrderPage.prototype.addItemByBarcode = function() {
    	var self = this;
    	
    	customerOrderService.addItemByBarcode(self.barcodeKey(), self.customerOrderPageModel.customerOrderId()).done(function(result) {	
    		if(result.success) {
    			self.currentPage(util.getLastPage(self.itemsPerPage(), self.totalItems() + 1));
    		} else {
    			self.currentPage(util.getLastPage(self.itemsPerPage(), self.totalItems()));
    		}
    		self.refreshCustomerOrderDetailList();
    		if(!result.success) {
    			app.showMessage(result.message);
    		}
    	});
    	
    	self.barcodeKey("");
    };
    
    CustomerOrderPage.prototype.changeQuantity = function(customerOrderDetailId, quantity) {
    	var self = this;
    	
    	customerOrderService.changeQuantity(customerOrderDetailId, quantity).done(function(result) {
    		self.refreshCustomerOrderDetailList();
    		if(!result.success) {
    			app.showMessage(result.message);
    		}
    	});
    };
    
    return CustomerOrderPage;
});