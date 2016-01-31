define(['durandal/app', 'knockout', 'modules/companyservice', 'viewmodels/manage/companyform'], function (app, ko, companyService, CompanyForm) {
	var Company = function() {
		this.companyList = ko.observable();
		
		this.searchKey = ko.observable();
		
		this.itemsPerPage = ko.observable(app.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
	};
	
	Company.prototype.activate = function() {
		var self = this;
		
		self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshCompanyList();
		});
		
		self.refreshCompanyList();
	};
	
	Company.prototype.refreshCompanyList = function() {
		var self = this;
		
		companyService.getCompanyList(self.currentPage(), self.searchKey()).done(function(data) {
			self.companyList(data.list);
			self.totalItems(data.total);
		});
	};
	
	Company.prototype.create = function() {
		var self = this;
		
		CompanyForm.show('Create', new Object()).then(function(response) {
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
	
	Company.prototype.remove = function(companyId, companyName) {
		var self = this;
		
		app.showMessage('Are you sure you want to remove Company "' + companyName + '"?',
				'Confirm Remove',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				companyService.removeCompany(companyId).done(function(data) {
					if(data) {
						self.refreshCompanyList();
						app.showMessage('Successfully removed Company "' + companyName + '".');
					} else {
						app.showMessage('Failed to remove Company "' + companyName + '".');
					}
				});
			}
		})
	};
	
    return Company;
});