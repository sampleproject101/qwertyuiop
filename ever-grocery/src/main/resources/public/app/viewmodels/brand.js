define(['durandal/app', 'knockout', 'modules/brandservice', 'viewmodels/forms/brandform'], function (app, ko, brandService, BrandForm) {
	var Brand = function() {
		this.brandList = ko.observable();
		
		this.searchKey = ko.observable();
	};
	
	Brand.prototype.activate = function() {
		this.refreshBrandList();
	};
	
	Brand.prototype.refreshBrandList = function() {
		var self = this;
		
		brandService.getBrandList(self.searchKey()).done(function(data) {
			self.brandList(data);
		});
	};
	
	Brand.prototype.create = function() {
		var self = this;
		
		BrandForm.show('Create', this.newBrand()).then(function(response) {
			if(response != undefined) {
				if(response) {
					app.showMessage('Brand successfully created.');
					self.refreshBrandList();
				} else {
					app.showMessage('Failed to create brand.');
				}
			}
		});
	};
	
	Brand.prototype.edit = function(brandId) {
		var self = this;
		
		brandService.getBrand(brandId).done(function(data) {
			BrandForm.show('Update', data).then(function(response) {
				if(response != undefined) {
					if(response) {
						app.showMessage('Brand successfully updated.');
						self.refreshBrandList();
					} else {
						app.showMessage('Failed to update brand.');
					}
				}
			});
		});
	};
	
	Brand.prototype.remove = function(brandId) {alert(brandId);
		var self = this;
		
		brandService.removeBrand(brandId).done(function(data) {
			self.refreshBrandList();
		});
	};
	
	Brand.prototype.newBrand = function() {
		var brand = new Object();
		
		brand.id = null;
		brand.name = '';
		
		return brand;
	};
	
    return Brand;
});