define(['durandal/app', 'knockout', 'modules/distributorservice', 'viewmodels/forms/distributorform'], function (app, ko, distributorService, DistributorForm) {
	var Distributor = function() {
		this.distributorList = ko.observable();
		
		this.searchKey = ko.observable();
	};
	
	Distributor.prototype.activate = function() {
		this.refreshDistributorList();
	};
	
	Distributor.prototype.refreshDistributorList = function() {
		var self = this;
		
		distributorService.getDistributorList(self.searchKey()).done(function(data) {
			self.distributorList(data);
		});
	};
	
	Distributor.prototype.create = function() {
		var self = this;
		
		DistributorForm.show('Create', this.newDistributor()).then(function(response) {
			if(response != undefined) {
				if(response) {
					app.showMessage('Distributor successfully created.');
					self.refreshDistributorList();
				} else {
					app.showMessage('Failed to create distributor.');
				}
			}
		});
	};
	
	Distributor.prototype.edit = function(distributorId) {
		var self = this;
		
		distributorService.getDistributor(distributorId).done(function(data) {
			DistributorForm.show('Update', data).then(function(response) {
				if(response != undefined) {
					if(response) {
						app.showMessage('Distributor successfully updated.');
						self.refreshDistributorList();
					} else {
						app.showMessage('Failed to update distributor.');
					}
				}
			});
		});
	};
	
	Distributor.prototype.remove = function(distributorId) {alert(distributorId);
		var self = this;
		
		distributorService.removeDistributor(distributorId).done(function(data) {
			self.refreshDistributorList();
		});
	};
	
	Distributor.prototype.newDistributor = function() {
		var distributor = new Object();
		
		distributor.id = null;
		distributor.name = '';
		distributor.address = '';
		distributor.agent = '';
		distributor.phoneNumber = '';
		
		return distributor;
	};
	
    return Distributor;
});