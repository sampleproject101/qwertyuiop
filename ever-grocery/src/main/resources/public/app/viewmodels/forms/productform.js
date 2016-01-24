define(['plugins/dialog', 'knockout', 'modules/productservice'], function (dialog, ko, productService) {
    var ProductForm = function(preTitle, productId) {
        this.preTitle = preTitle;
        
        this.productFormModel = {
        	id: ko.observable(productId),
        	name: ko.observable()	
        };
    };
 
    ProductForm.show = function(preTitle, productId) {
        return dialog.show(new ProductForm(preTitle, productId));
    };
 
    ProductForm.prototype.save = function() {
    	var self = this;
    	
    	productService.saveProduct(ko.toJSON(self.productFormModel)).done(function(data) {
        	dialog.close(self, data);
        });
    };
    
    ProductForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return ProductForm;
});