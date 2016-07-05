define(['durandal/app', 'knockout', 'modules/securityservice', 'modules/brandservice', 'viewmodels/manage/brandform'], function (app, ko, securityService, brandService, BrandForm) {
	var Brand = function() {
		this.brandList = ko.observable();
		
		this.searchKey = ko.observable();
		
		this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
	};
	
	Brand.prototype.canActivate = function() {
		var deferred = $.Deferred();
	    return deferred.then(securityService.authenticatePage('manage/brand').done(function(result) {
	        if (result.success) {
	            deferred.resolve(result.success);
	        } else {
	        	app.showMessage(result.message);
	            deferred.resolve({ 'redirect': '/' });
	        }
	        return deferred.promise();
	    }));
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
	
	Brand.prototype.search = function() {
		var self = this;
		
		self.currentPage(1);
		self.refreshBrandList();
	};
	
	Brand.prototype.create = function() {
		var self = this;
		
		BrandForm.show('Create', new Object()).then(function() {
			self.refreshBrandList();
		});
	};
	
	Brand.prototype.edit = function(brandId) {
		var self = this;
		
		brandService.getBrand(brandId).done(function(data) {
			BrandForm.show('Update', data).then(function() {
				self.refreshBrandList();
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
				brandService.removeBrand(brandId).done(function(result) {
					self.refreshBrandList();
					app.showMessage(result.message);
				});
			}
		})
	};
	
    return Brand;
});