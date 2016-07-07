define(['durandal/app', 'knockout', 'modules/customerorderservice', 'viewmodels/manage/saleview'], function (app, ko, customerOrderService, SaleView) {
	var CustomerOrder = function() {
		this.customerOrderList = ko.observable();
		
		this.showRecent = ko.observable(true);
		
		this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
	};
	
	CustomerOrder.prototype.activate = function() {
		var self = this;
		
		self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshCustomerOrderList();
		});
		self.showRecent.subscribe(function() {
			self.refreshCustomerOrderList();
		});
		
		self.refreshCustomerOrderList();
	};
	
	CustomerOrder.prototype.refreshCustomerOrderList = function() {
		var self = this;
		
		if(self.showRecent()) {
			customerOrderService.getCustomerOrderList(self.currentPage(), null, true, 7).done(function(data) {
				self.customerOrderList(data.list);
				self.totalItems(data.total);
			});
		} else {
			customerOrderService.getCustomerOrderList(self.currentPage(), null, true, null).done(function(data) {
				self.customerOrderList(data.list);
				self.totalItems(data.total);
			});
		}
	};
	
	CustomerOrder.prototype.view = function(customerOrderId) {
		var self = this;
		
		customerOrderService.getCustomerOrder(customerOrderId).done(function(data) {
			SaleView.show(data).done(function() {
				self.refreshCustomerOrderList();
			});
		});
	};
	
    return CustomerOrder;
});