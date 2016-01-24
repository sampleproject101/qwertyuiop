define(['plugins/dialog', 'knockout', 'modules/companyservice'], function (dialog, ko, companyService) {
    var CompanyForm = function(preTitle, companyId) {
        this.preTitle = preTitle;
        
        this.companyFormModel = {
        	id: ko.observable(companyId),
        	name: ko.observable(),
        	address: ko.observable(),
        	agent: ko.observable(),
        	phoneNumber: ko.observable()
        };
    };
 
    CompanyForm.show = function(preTitle, companyId) {
        return dialog.show(new CompanyForm(preTitle, companyId));
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