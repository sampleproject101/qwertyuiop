define(['plugins/dialog', 'knockout'], function (dialog, ko) {
    var ProductDetailsForm = function(product) {
        this.product = product;
        
        this.productDetailsFormModel = {
        	id: ko.observable(),
        	name: ko.observable()
        };
    };
 
    ProductDetailsForm.prototype.activate = function() {
    	var self = this;
    	
    	self.productDetailsFormModel.id(self.product.id);
    	self.productDetailsFormModel.name(self.product.name);
    };
    
    ProductDetailsForm.show = function(product) {
        return dialog.show(new ProductDetailsForm(product));
    };
    
    ProductDetailsForm.prototype.save = function() {
    	var self = this;
    	
        alert('save');
    };
    
    ProductDetailsForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return ProductDetailsForm;
});