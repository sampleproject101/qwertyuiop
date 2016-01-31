define(['plugins/dialog', 'knockout', 'modules/companyservice'], function (dialog, ko, companyService) {
    var CompanyForm = function(preTitle, company) {
        this.preTitle = preTitle;
        this.company = company;
        
        this.companyFormModel = {
        	id: ko.observable(),
        	name: ko.observable(),
        	address: ko.observable(),
        	agent: ko.observable(),
        	phoneNumber: ko.observable()
        };
    };
    
    CompanyForm.prototype.activate = function() {
    	var self = this;
    	
    	self.companyFormModel.id(self.company.id);
    	self.companyFormModel.name(self.company.name);
    	self.companyFormModel.address(self.company.address);
    	self.companyFormModel.agent(self.company.agent);
    	self.companyFormModel.phoneNumber(self.company.phoneNumber);
    };
 
    CompanyForm.show = function(preTitle, company) {
        return dialog.show(new CompanyForm(preTitle, company));
    };
 
    CompanyForm.prototype.save = function() {
    	var self = this;
    	
    	companyService.saveCompany(ko.toJSON(self.companyFormModel)).done(function(data) {
        	dialog.close(self, data);
        });
    };
    
    CompanyForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return CompanyForm;
});