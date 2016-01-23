define(['knockout', 'modules/distributorservice'], function (ko, distributorService) {
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
	
	Distributor.prototype.edit = function() {
		alert('edit');
	};
	
	Distributor.prototype.remove = function(distributorId) {alert(distributorId);
		var self = this;
		
		distributorService.removeDistributor(distributorId).done(function(data) {
			self.refreshDistributorList();
		});
	};
	
    return Distributor;
});