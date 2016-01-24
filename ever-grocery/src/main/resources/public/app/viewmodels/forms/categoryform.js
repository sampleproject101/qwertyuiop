define(['plugins/dialog', 'knockout', 'modules/categoryservice'], function (dialog, ko, categoryService) {
    var CategoryForm = function(preTitle, categoryId) {
        this.preTitle = preTitle;
        
        this.categoryFormModel = {
        	id: ko.observable(categoryId),
        	name: ko.observable()	
        };
    };
 
    CategoryForm.show = function(preTitle, categoryId) {
        return dialog.show(new CategoryForm(preTitle, categoryId));
    };
 
    CategoryForm.prototype.save = function() {
    	var self = this;
    	
    	categoryService.saveCategory(ko.toJSON(self.categoryFormModel)).done(function(data) {
        	dialog.close(self, data);
        });
    };
    
    CategoryForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return CategoryForm;
});