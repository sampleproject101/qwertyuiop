define(['durandal/app', 'knockout', 'modules/distributorservice', 'viewmodels/manage/distributorform'], function (app, ko, distributorService, DistributorForm) {
	var Distributor = function() {
		this.distributorList = ko.observable();
		
		this.searchKey = ko.observable();
		
		this.itemsPerPage = ko.observable(app.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
	};
	
	Distributor.prototype.activate = function() {
		var self = this;
		
		self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshDistributorList();
		});
		
		self.refreshDistributorList();
	};
	
	Distributor.prototype.refreshDistributorList = function() {
		var self = this;
		
		distributorService.getDistributorList(self.currentPage(), self.searchKey()).done(function(data) {
			self.distributorList(data.list);
			self.totalItems(data.total);
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
	
	Distributor.prototype.remove = function(distributorId, distributorName) {
		var self = this;
		
		app.showMessage('Are you sure you want to remove Distributor "' + distributorName + '"?',
				'Confirm Remove',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				distributorService.removeDistributor(distributorId).done(function(data) {
					if(data) {
						self.refreshDistributorList();
						app.showMessage('Successfully removed Distributor "' + distributorName + '".');
					} else {
						app.showMessage('Failed to remove Distributor "' + distributorName + '".');
					}
				});
			}
		})
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