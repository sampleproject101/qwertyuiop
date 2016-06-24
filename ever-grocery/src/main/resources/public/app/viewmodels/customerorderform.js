define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/customerOrderservice', 'modules/customerservice'], function (dialog, app, ko, customerOrderService, customerService) {
    var CustomerOrderForm = function(preTitle, customerOrder) {
        this.preTitle = preTitle;
        this.customerOrder = customerOrder;
        
        this.customerOrderFormModel = {
        	id: ko.observable(),
        	name: ko.observable(),
        	customerId: ko.observable(),
        	creatorId: ko.observable(),
        	cashierId: ko.observable(),
        	totalAmount: ko.observable()
        };
        
        this.customerList = ko.observable();
    };
 
    CustomerOrderForm.prototype.activate = function() {
    	var self = this;
    	
    	self.customerOrderFormModel.id(self.customerOrder.id);
    	self.customerOrderFormModel.name(self.customerOrder.name);
    	
    	customerService.getCustomerListByLastName().done(function(customerList) {
    		self.customerList(customerList);
    		self.customerOrderFormModel.customerId(self.customerOrder.customer.id);
    	});
    };
    
    CustomerOrderForm.show = function(preTitle, customerOrder) {
        return dialog.show(new CustomerOrderForm(preTitle, customerOrder));
    };
    
    CustomerOrderForm.prototype.save = function() {
    	var self = this;
    	
        customerOrderService.saveCustomerOrder(ko.toJSON(self.customerOrderFormModel)).done(function(result) {
        	if(result.success) {
        		dialog.close(self);	
        	} 
        	app.showMessage(result.message);
        });
    };
    
    CustomerOrderForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return CustomerOrderForm;
});