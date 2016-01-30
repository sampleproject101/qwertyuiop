define(['plugins/dialog', 'knockout', 'modules/productservice', 'modules/brandservice'], function (dialog, ko, productService, brandService) {
    var ProductForm = function(preTitle, product) {
        this.preTitle = preTitle;
        
        this.productFormModel = {
        	id: ko.observable(product.id),
        	name: ko.observable(product.name),
        	brandId: ko.observable()
        };
        
        this.brandList = ko.observable();
    };
 
    ProductForm.prototype.activate = function() {
    	var self = this;
    	
    	brandService.getBrandListByName().done(function(brandList) {
    		self.brandList(brandList);
    	});
    };
    
    ProductForm.show = function(preTitle, product) {
        return dialog.show(new ProductForm(preTitle, product));
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