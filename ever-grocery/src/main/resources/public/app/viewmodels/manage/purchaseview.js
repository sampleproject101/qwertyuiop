define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/purchaseorderservice'], function (dialog, app, ko, purchaseOrderService) {
    var PurchaseView = function(purchaseOrder) {
    	this.purchaseOrder = purchaseOrder;
    	this.purchaseOrderDetailList = ko.observable();
    	
    	this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
		
		this.purchaseOrderViewModel = {
			purchaseOrderId: ko.observable(),
			totalAmount: ko.observable(),
			companyName: ko.observable()
		};
    };
    
    PurchaseView.prototype.activate = function() {
    	var self = this;
    	
    	self.purchaseOrderViewModel.purchaseOrderId(self.purchaseOrder.id);
    	self.purchaseOrderViewModel.totalAmount(self.purchaseOrder.totalAmount);
		self.purchaseOrderViewModel.companyName(self.purchaseOrder.company.name);
    	
    	self.currentPage(1);
    	self.currentPageSubscription = self.currentPage.subscribe(function() {
    		self.refreshPurchaseOrderDetailList();
		});
    	
    	self.refreshPurchaseOrderDetailList();
    };
    
    PurchaseView.show = function(purchaseOrder) {
    	return dialog.show(new PurchaseView(purchaseOrder));
    };
    
    PurchaseView.prototype.refreshPurchaseOrderDetailList = function() {
    	var self = this;
    	
    	purchaseOrderService.getPurchaseOrderDetailList(self.currentPage(), self.purchaseOrderViewModel.purchaseOrderId(), false).done(function(data) { 
			self.purchaseOrderDetailList(data.list);
			self.totalItems(data.total);
		});
    };
    
    PurchaseView.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return PurchaseView;
});