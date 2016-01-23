define(['knockout', 'modules/categoryservice'], function (ko, categoryService) {
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
	
	Category.prototype.edit = function() {
		alert('edit');
	};
	
	Category.prototype.remove = function(categoryId) {alert(categoryId);
		var self = this;
		
		categoryService.removeCategory(categoryId).done(function(data) {
			self.refreshCategoryList();
		});
	};
	
    return Category;
});