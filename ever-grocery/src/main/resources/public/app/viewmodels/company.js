define(['durandal/app', 'knockout', 'modules/companyservice', 'viewmodels/forms/companyform'], function (app, ko, companyService, CompanyForm) {
	var Company = function() {
		this.companyList = ko.observable();
		
		this.searchKey = ko.observable();
	};
	
	Company.prototype.activate = function() {
		this.refreshCompanyList();
	};
	
	Company.prototype.refreshCompanyList = function() {
		var self = this;
		
		companyService.getCompanyList(self.searchKey()).done(function(data) {
			self.companyList(data);
		});
	};
	
	Company.prototype.create = function() {
		var self = this;
		
		CompanyForm.show('Create', this.newCompany()).then(function(response) {
			if(response != undefined) {
				if(response) {
					app.showMessage('Company successfully created.');
					self.refreshCompanyList();
				} else {
					app.showMessage('Failed to create company.');
				}
			}
		});
	};
	
	Company.prototype.edit = function(companyId) {
		var self = this;
		
		companyService.getCompany(companyId).done(function(data) {
			CompanyForm.show('Update', data).then(function(response) {
				if(response != undefined) {
					if(response) {
						app.showMessage('Company successfully updated.');
						self.refreshCompanyList();
					} else {
						app.showMessage('Failed to update company.');
					}
				}
			});
		});
	};
	
	Company.prototype.remove = function(companyId) {alert(companyId);
		var self = this;
		
		companyService.removeCompany(companyId).done(function(data) {
			self.refreshCompanyList();
		});
	};
	
	Company.prototype.newCompany = function() {
		var company = new Object();
		
		company.id = null;
		company.name = '';
		company.address = '';
		company.agent = '';
		company.phoneNumber = '';
		
		return company;
	};
	
    return Company;
});