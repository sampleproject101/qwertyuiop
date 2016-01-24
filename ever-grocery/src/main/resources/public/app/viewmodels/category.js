define(['durandal/app', 'knockout', 'modules/categoryservice', 'viewmodels/forms/categoryform'], function (app, ko, categoryService, CategoryForm) {
	var Category = function() {
		this.categoryList = ko.observable();
		
		this.searchKey = ko.observable();
	};
	
	Category.prototype.activate = function() {
		this.refreshCategoryList();
	};
	
	Category.prototype.refreshCategoryList = function() {
		var self = this;
		
		categoryService.getCategoryList(self.searchKey()).done(function(data) {
			self.categoryList(data);
		});
	};
	
	Category.prototype.create = function() {
		var self = this;
		
		CategoryForm.show('Create', this.newCategory()).then(function(response) {
			if(response != undefined) {
				if(response) {
					app.showMessage('Category successfully created.');
					self.refreshCategoryList();
				} else {
					app.showMessage('Failed to create category.');
				}
			}
		});
	};
	
	Category.prototype.edit = function(categoryId) {
		var self = this;
		
		categoryService.getCategory(categoryId).done(function(data) {
			CategoryForm.show('Update', data).then(function(response) {
				if(response != undefined) {
					if(response) {
						app.showMessage('Category successfully updated.');
						self.refreshCategoryList();
					} else {
						app.showMessage('Failed to update category.');
					}
				}
			});
		});
	};
	
	Category.prototype.remove = function(categoryId) {alert(categoryId);
		var self = this;
		
		categoryService.removeCategory(categoryId).done(function(data) {
			self.refreshCategoryList();
		});
	};
	
	Category.prototype.newCategory = function() {
		var category = new Object();
		
		category.id = null;
		category.name = '';
		
		return category;
	};
	
    return Category;
});