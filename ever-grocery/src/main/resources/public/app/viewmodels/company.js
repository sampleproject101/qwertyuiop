define(['knockout', 'modules/companyservice'], function (ko, companyService) {
	var Company = function() {
		this.companyList = ko.observable();
	};
	
	Company.prototype.activate = function() {
		this.refreshCompanyList();
	};
	
	Company.prototype.refreshCompanyList = function() {
		var self = this;
		
		companyService.getCompanyList().done(function(data) {
			self.companyList(data);
		});
	};
	
	Company.prototype.edit = function() {
		alert('edit');
	};
	
	Company.prototype.remove = function(companyId) {alert(companyId);
		var self = this;
		
		companyService.removeCompany(companyId).done(function(data) {
			self.refreshCompanyList();
		});
	};
	
    return Company;
});