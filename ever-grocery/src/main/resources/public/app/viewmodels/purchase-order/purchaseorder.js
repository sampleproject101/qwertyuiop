define(['plugins/router', 'durandal/app', 'knockout', 'modules/securityservice', 'modules/purchaseorderservice', 'modules/companyservice', 'viewmodels/purchase-order/purchaseorderform'], function (router, app, ko, securityService, purchaseOrderService, companyService, PurchaseOrderForm) {
	var PurchaseOrder = function() {
		this.purchaseOrderList = ko.observable();
		this.companyList = ko.observable();
		
		this.searchKey = ko.observable();
		this.companyId = ko.observable();
		
		this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
	};
	
	PurchaseOrder.prototype.canActivate = function() {
		var deferred = $.Deferred();
	    return deferred.then(securityService.authenticatePage('purchaseorder').done(function(result) {
	        if (result.success) {
	            deferred.resolve(result.success);
	        } else {
	        	app.showMessage(result.message);
	            deferred.resolve({ 'redirect': '/' });
	        }
	        return deferred.promise();
	    }));
	};
	
	PurchaseOrder.prototype.activate = function() {
		var self = this;
		
		companyService.getCompanyListByName().done(function(companyList) {
    		self.companyList(companyList);
    	});
		
		self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshPurchaseOrderList();
		});
		
		self.refreshPurchaseOrderList();
	};
	
	PurchaseOrder.prototype.refreshPurchaseOrderList = function() {
		var self = this;
		
		purchaseOrderService.getPurchaseOrderList(self.currentPage(), self.companyId(), false, true).done(function(data) {
			self.purchaseOrderList(data.list);
			self.totalItems(data.total);
		});
	};
	
	PurchaseOrder.prototype.create = function() {
		var self = this;
		
		PurchaseOrderForm.show('Create', new Object()).then(function() {
			self.refreshPurchaseOrderList();
		});
	};
	
	PurchaseOrder.prototype.remove = function(purchaseOrderId) {
		var self = this;
		
		app.showMessage('Are you sure you want to cancel Purchase Order "' + purchaseOrderId + '"?',
				'Confirm Remove',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				purchaseOrderService.removePurchaseOrder(purchaseOrderId).done(function(result) {
					self.refreshPurchaseOrderList();
					app.showMessage(result.message);
				});
			}
		})
	};
	
	PurchaseOrder.prototype.details = function(purchaseOrderId) {
		var self = this;
		
		alert("details");
	};
	
    return PurchaseOrder;
});