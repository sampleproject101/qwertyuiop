define(['plugins/dialog', 'knockout', 'modules/productservice', 'modules/brandservice', 'modules/categoryservice', 'modules/companyservice', 'modules/distributorservice'], function (dialog, ko, productService, brandService,
		categoryService, companyService, distributorService) {
    var ProductForm = function(preTitle, product) {
        this.preTitle = preTitle;
        
        this.productFormModel = {
        	id: ko.observable(product.id),
        	name: ko.observable(product.name),
        	brandId: ko.observable(),
        	categoryId: ko.observable(),
        	companyId: ko.observable(),
        	distributorId: ko.observable()
        };
        
        this.brandList = ko.observable();
        this.categoryList = ko.observable();
        this.companyList = ko.observable();
        this.distributorList = ko.observable();
    };
 
    ProductForm.prototype.activate = function() {
    	var self = this;
    	
    	brandService.getBrandListByName().done(function(brandList) {
    		self.brandList(brandList);
    	});
    	
    	categoryService.getCategoryListByName().done(function(categoryList) {
    		self.categoryList(categoryList);
    	});
    	
    	companyService.getCompanyListByName().done(function(companyList) {
    		self.companyList(companyList);
    	});
    	
    	distributorService.getDistributorListByName().done(function(distributorList) {
    		self.distributorList(distributorList);
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