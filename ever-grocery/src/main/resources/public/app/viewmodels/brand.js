define(['durandal/app', 'knockout', 'modules/brandservice', 'viewmodels/forms/brandform'], function (app, ko, brandService, BrandForm) {
	var Brand = function() {
		this.brandList = ko.observable();
		
		this.searchKey = ko.observable();
		
		this.itemsPerPage = ko.observable(app.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
	};
	
	Brand.prototype.activate = function() {
		var self = this;
		
		self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshBrandList();
		});
		
		self.refreshBrandList();
	};
	
	Brand.prototype.refreshBrandList = function() {
		var self = this;
		
		brandService.getBrandList(self.currentPage(), self.searchKey()).done(function(data) {
			self.brandList(data.list);
			self.totalItems(data.total);
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
	
	Brand.prototype.remove = function(brandId, brandName) {
		var self = this;
		
		app.showMessage('Are you sure you want to remove Brand "' + brandName + '"?',
				'Confirm Remove',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				brandService.removeBrand(brandId).done(function(data) {
					if(data) {
						self.refreshBrandList();
						app.showMessage('Successfully removed Brand "' + brandName + '".');
					} else {
						app.showMessage('Failed to remove Brand "' + brandName + '".');
					}
				});
			}
		})
	};
	
	Brand.prototype.newBrand = function() {
		var brand = new Object();
		
		brand.id = null;
		brand.name = '';
		
		return brand;
	};
	
    return Brand;
});