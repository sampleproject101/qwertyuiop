define(['knockout', 'modules/companyservice', 'viewmodels/forms/companyform'], function (ko, companyService, CompanyForm) {
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
	
	Company.prototype.create = function() {
		var self = this;
		
		CompanyForm.show('Create', null).then(function(response) {
			if(response) {
				self.refreshCompanyList();
			}
		});
	};
	
	Company.prototype.edit = function(companyId) {
		CompanyForm.show('Update', companyId);
	};
	
	Company.prototype.remove = function(companyId) {alert(companyId);
		var self = this;
		
		companyService.removeCompany(companyId).done(function(data) {
			self.refreshCompanyList();
		});
	};
	
    return Company;
});