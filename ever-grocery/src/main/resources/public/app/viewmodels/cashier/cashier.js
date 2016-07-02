define(['durandal/app', 'knockout', 'modules/customerorderservice', 'viewmodels/cashier/payform'], function (app, ko, customerOrderService, PayForm) {
	var Cashier = function() {
		this.customerOrderList = ko.observable();
		
		this.searchKey = ko.observable();
		this.showPaid = ko.observable(false);
		
		this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
	};
	
	Cashier.prototype.activate = function() {
		var self = this;
		
		self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshCustomerOrderList();
		});
		self.showPaid.subscribe(function() {
			self.refreshCustomerOrderList();
		});
		
		self.refreshCustomerOrderList();
	};
	
	Cashier.prototype.refreshCustomerOrderList = function() {
		var self = this;
		
		customerOrderService.getCustomerOrderList(self.currentPage(), self.searchKey(), self.showPaid()).done(function(data) {
			self.customerOrderList(data.list);
			self.totalItems(data.total);
		});
	};
	
	Cashier.prototype.print = function(customerOrderId) {
		var self = this;
		
		customerOrderService.printCustomerOrderList(customerOrderId).done(function(result) {
			app.showMessage(result.message);
		});
	};
	
	Cashier.prototype.pay = function(customerOrderId) {
		var self = this;
		
		customerOrderService.getCustomerOrder(customerOrderId).done(function(data) {
			PayForm.show(data).then(function() {
				self.refreshCustomerOrderList();
			});
		});
	};
	
    return Cashier;
});