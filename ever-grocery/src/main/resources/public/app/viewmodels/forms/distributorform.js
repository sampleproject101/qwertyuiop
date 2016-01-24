define(['plugins/dialog', 'knockout', 'modules/distributorservice'], function (dialog, ko, distributorService) {
    var DistributorForm = function(preTitle, distributor) {
        this.preTitle = preTitle;
        
        this.distributorFormModel = {
        	id: ko.observable(distributor.id),
        	name: ko.observable(distributor.name),
        	address: ko.observable(distributor.address),
        	agent: ko.observable(distributor.agent),
        	phoneNumber: ko.observable(distributor.phoneNumber)
        };
    };
 
    DistributorForm.show = function(preTitle, distributor) {
        return dialog.show(new DistributorForm(preTitle, distributor));
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