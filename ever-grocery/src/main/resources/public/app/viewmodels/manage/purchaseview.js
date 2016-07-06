define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/purchaseorderservice', 'viewmodels/purchase-order/search'], function (dialog, app, ko, purchaseOrderService, Search) {
    var PurchaseView = function(purchaseOrder) {
    	this.purchaseOrder = purchaseOrder;
    	this.purchaseOrderDetailList = ko.observable();
    	
    	this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
		
		this.purchaseOrderPageModel = {
			purchaseOrderId: ko.observable(),
			totalAmount: ko.observable(),
			companyName: ko.observable()
		};
    };
    
    PurchaseView.prototype.activate = function() {
    	var self = this;
    	
    	self.purchaseOrderPageModel.purchaseOrderId(self.purchaseOrder.id);
    	
    	self.currentPage(1);
    	self.currentPageSubscription = self.currentPage.subscribe(function() {
    		self.refreshPurchaseOrderDetailList();
		});
    	
    	purchaseOrderService.refreshPurchaseOrder(self.purchaseOrder.id, false).done(function() {
    		self.refreshPurchaseOrderDetailList();
    	});
    	
    	self.purchaseOrderPageModel.totalAmount(self.purchaseOrder.totalAmount);
		self.purchaseOrderPageModel.companyName(self.purchaseOrder.company.name);
    };
    
    PurchaseView.show = function(purchaseOrder) {
    	return dialog.show(new PurchaseView(purchaseOrder));
    };
    
    PurchaseView.prototype.refreshPurchaseOrderDetailList = function() {
    	var self = this;
    	
    	purchaseOrderService.getPurchaseOrderDetailList(self.currentPage(), self.purchaseOrderPageModel.purchaseOrderId(), false).done(function(data) { 
			self.purchaseOrderDetailList(data.list);
			self.totalItems(data.total);
		});
    };
    
    PurchaseView.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return PurchaseView;
});