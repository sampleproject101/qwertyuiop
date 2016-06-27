define(['plugins/dialog','knockout', 'durandal/app'], function (dialog, ko, app) {
	var More = function(product) {
		this.product = product;
	};
	
	More.prototype.activate = function() {
		var self = this;
	};
	
	More.show = function(product) {
		return dialog.show(new More(product));
	};
	
	More.prototype.cancel = function() {
		dialog.close(this);
	};
	
	return More;
});