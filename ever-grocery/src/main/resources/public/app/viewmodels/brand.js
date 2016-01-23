define(['knockout', 'modules/brandservice'], function (ko, brandService) {
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
	
	Brand.prototype.edit = function() {
		alert('edit');
	};
	
	Brand.prototype.remove = function(brandId) {alert(brandId);
		var self = this;
		
		brandService.removeBrand(brandId).done(function(data) {
			self.refreshBrandList();
		});
	};
	
    return Brand;
});