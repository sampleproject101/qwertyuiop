define(['plugins/dialog', 'knockout', 'modules/categoryservice'], function (dialog, ko, categoryService) {
    var CategoryForm = function(preTitle, category) {
        this.preTitle = preTitle;
        
        this.categoryFormModel = {
        	id: ko.observable(category.id),
        	name: ko.observable(category.name)
        };
    };
 
    CategoryForm.show = function(preTitle, category) {
        return dialog.show(new CategoryForm(preTitle, category));
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