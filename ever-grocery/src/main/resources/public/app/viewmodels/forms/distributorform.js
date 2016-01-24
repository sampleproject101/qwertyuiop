define(['plugins/dialog', 'knockout', 'modules/distributorservice'], function (dialog, ko, distributorService) {
    var DistributorForm = function(preTitle, distributorId) {
        this.preTitle = preTitle;
        
        this.distributorFormModel = {
        	id: ko.observable(distributorId),
        	name: ko.observable(),
        	address: ko.observable(),
        	agent: ko.observable(),
        	phoneNumber: ko.observable()
        };
    };
 
    DistributorForm.show = function(preTitle, distributorId) {
        return dialog.show(new DistributorForm(preTitle, distributorId));
    };
 
    DistributorForm.prototype.save = function() {
    	var self = this;
    	
    	distributorService.saveDistributor(ko.toJSON(self.distributorFormModel)).done(function(data) {
        	dialog.close(self, data);
        });
    };
    
    DistributorForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return DistributorForm;
});