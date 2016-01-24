define(['plugins/dialog', 'knockout', 'modules/brandservice'], function (dialog, ko, brandService) {
    var BrandForm = function(preTitle, brandId) {
        this.preTitle = preTitle;
        
        this.brandFormModel = {
        	id: ko.observable(brandId),
        	name: ko.observable()	
        };
    };
 
    BrandForm.show = function(preTitle, brandId) {
        return dialog.show(new BrandForm(preTitle, brandId));
    };
 
    BrandForm.prototype.save = function() {
    	var self = this;
    	
        brandService.saveBrand(ko.toJSON(self.brandFormModel)).done(function(data) {
        	dialog.close(self, data);
        });
    };
    
    BrandForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return BrandForm;
});