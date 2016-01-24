define(['knockout', 'modules/brandservice', 'viewmodels/forms/brandform'], function (ko, brandService, BrandForm) {
	var Brand = function() {
		this.brandList = ko.observable();
	};
	
	Brand.prototype.activate = function() {
		this.refreshBrandList();
	};
	
	Brand.prototype.refreshBrandList = function() {
		var self = this;
		
		brandService.getBrandList().done(function(data) {
			self.brandList(data);
		});
	};
	
	Brand.prototype.create = function() {
		var self = this;
		
		BrandForm.show('Create', null).then(function(response) {
			if(response) {
				self.refreshBrandList();
			}
		});
	};
	
	Brand.prototype.edit = function(brandId) {
		BrandForm.show('Update', brandId);
	};
	
	Brand.prototype.remove = function(brandId) {alert(brandId);
		var self = this;
		
		brandService.removeBrand(brandId).done(function(data) {
			self.refreshBrandList();
		});
	};
	
    return Brand;
});