define(['plugins/dialog', 'knockout', 'modules/companyservice'], function (dialog, ko, companyService) {
    var CompanyForm = function(preTitle, company) {
        this.preTitle = preTitle;
        
        this.companyFormModel = {
        	id: ko.observable(company.id),
        	name: ko.observable(company.name),
        	address: ko.observable(company.address),
        	agent: ko.observable(company.agent),
        	phoneNumber: ko.observable(company.phoneNumber)
        };
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