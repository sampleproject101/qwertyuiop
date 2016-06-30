define(['durandal/app', 'knockout', 'modules/utility', 'modules/customerorderservice'], function (app, ko, util, customerOrderService) {
    var CustomerOrderPage = function() {		//function(customerOrder)
    	this.customerOrderId = ko.observable(1);		// to remove
    	this.totalAmount = ko.observable();	// this.customerOrder = customerOrder
    	this.customerOrderName = ko.observable();		//to remove
    	this.status = ko.observable();			// to remove
    	
    	this.customerOrderDetailList = ko.observable();
    	
    	this.barcodeKey = ko.observable();
    	
    	this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
    };
    
    CustomerOrderPage.prototype.activate = function() {
    	var self = this;
    	
    	self.currentPage(1);
    	self.currentPageSubscription = self.currentPage.subscribe(function() {
    		self.refreshCustomerOrderDetailList();
		});
    	
    	customerOrderService.refreshCustomerOrder(self.customerOrderId()).done(function() {					//change id to self.customerOrder.id
    		customerOrderService.getCustomerOrder(self.customerOrderId()).done(function(data) {				//change id to self.customerOrder.id
        		self.totalAmount(data.totalAmount);		//self.customerOrder = data;		update html after
        		self.customerOrderId(data.id);
        		self.customerOrderName(data.name);
        		self.status(data.status);				//^^ to remove
        	});
    	});						
    	
    	self.refreshCustomerOrderDetailList();
    };
    
    CustomerOrderPage.prototype.refreshCustomerOrderDetailList = function() {
    	var self = this;
    	
    	customerOrderService.getCustomerOrderDetailList(self.currentPage(), self.customerOrderId()).done(function(data) {		//change id to self.customerOrder.id
			self.customerOrderDetailList(data.list);
			self.totalItems(data.total);
		});
    	
    	customerOrderService.getCustomerOrder(self.customerOrderId()).done(function(data) {			// change id
    		self.totalAmount(data.totalAmount);		//self.customerOrder = data;		update html after
    		self.customerOrderId(data.id);
    		self.customerOrderName(data.name);
    		self.status(data.status);					//^^ to remove
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
    	
    	customerOrderService.addItemByBarcode(self.barcodeKey(), self.customerOrderId()).done(function(result) {				// id!!!!
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
    
    return CustomerOrderPage;
});