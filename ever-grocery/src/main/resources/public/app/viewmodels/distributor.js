define(['knockout', 'modules/distributorservice', 'viewmodels/forms/distributorform'], function (ko, distributorService, DistributorForm) {
	var Distributor = function() {
		this.distributorList = ko.observable();
	};
	
	Distributor.prototype.activate = function() {
		this.refreshDistributorList();
	};
	
	Distributor.prototype.refreshDistributorList = function() {
		var self = this;
		
		distributorService.getDistributorList().done(function(data) {
			self.distributorList(data);
		});
	};
	
	Distributor.prototype.create = function() {
		var self = this;
		
		DistributorForm.show('Create', null).then(function(response) {
			if(response) {
				self.refreshDistributorList();
			}
		});
	};
	
	Distributor.prototype.edit = function(distributorId) {
		DistributorForm.show('Update', distributorId);
	};
	
	Distributor.prototype.remove = function(distributorId) {alert(distributorId);
		var self = this;
		
		distributorService.removeDistributor(distributorId).done(function(data) {
			self.refreshDistributorList();
		});
	};
	
    return Distributor;
});