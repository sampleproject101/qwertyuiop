define(['knockout', 'modules/categoryservice', 'viewmodels/forms/categoryform'], function (ko, categoryService, CategoryForm) {
	var Category = function() {
		this.categoryList = ko.observable();
	};
	
	Category.prototype.activate = function() {
		this.refreshCategoryList();
	};
	
	Category.prototype.refreshCategoryList = function() {
		var self = this;
		
		categoryService.getCategoryList().done(function(data) {
			self.categoryList(data);
		});
	};
	
	Category.prototype.create = function() {
		var self = this;
		
		CategoryForm.show('Create', null).then(function(response) {
			if(response) {
				self.refreshCategoryList();
			}
		});
	};
	
	Category.prototype.edit = function(categoryId) {
		CategoryForm.show('Update', categoryId);
	};
	
	Category.prototype.remove = function(categoryId) {alert(categoryId);
		var self = this;
		
		categoryService.removeCategory(categoryId).done(function(data) {
			self.refreshCategoryList();
		});
	};
	
    return Category;
});