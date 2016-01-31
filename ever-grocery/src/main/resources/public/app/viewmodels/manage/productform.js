define(['plugins/dialog', 'knockout', 'modules/productservice', 'modules/brandservice', 'modules/categoryservice', 'modules/companyservice', 'modules/distributorservice'], function (dialog, ko, productService, brandService,
		categoryService, companyService, distributorService) {
    var ProductForm = function(preTitle, product) {
        this.preTitle = preTitle;
        this.product = product;
        
        this.productFormModel = {
        	id: ko.observable(),
        	name: ko.observable(),
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
    	
    	self.productFormModel.id(self.product.id);
    	self.productFormModel.name(self.product.name);
    	
    	brandService.getBrandListByName().done(function(brandList) {
    		self.brandList(brandList);
    		self.productFormModel.brandId(self.product.brand.id);
    	});
    	
    	categoryService.getCategoryListByName().done(function(categoryList) {
    		self.categoryList(categoryList);
    		self.productFormModel.categoryId(self.product.category.id);
    	});
    	
    	companyService.getCompanyListByName().done(function(companyList) {
    		self.companyList(companyList);
    		self.productFormModel.companyId(self.product.company.id);
    	});
    	
    	distributorService.getDistributorListByName().done(function(distributorList) {
    		self.distributorList(distributorList);
    		self.productFormModel.distributorId(self.product.distributor.id);
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