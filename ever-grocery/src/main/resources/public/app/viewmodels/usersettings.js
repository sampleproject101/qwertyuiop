define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/userservice'], function (dialog, app, ko, userService) {
    var UserSettings = function(user) {
        this.user = user;
        
        this.itemsPerPageList = ko.observableArray([5, 10, 15, 20]);
        
        this.formModel = {
    		id: ko.observable(),
        	firstName: ko.observable(),
        	lastName: ko.observable(),
        	itemsPerPage: ko.observable(),
        	username: ko.observable()
        };
    };
    
    UserSettings.prototype.activate = function() {
    	var self = this;
    	
    	self.formModel.id(self.user.id);
    	self.formModel.firstName(self.user.firstName);
    	self.formModel.lastName(self.user.lastName);
    	self.formModel.itemsPerPage(self.user.itemsPerPage);
    	self.formModel.username(self.user.username);
    };
 
    UserSettings.show = function(user) {
        return dialog.show(new UserSettings(user));
    };
    
    UserSettings.prototype.save = function() {
    	var self = this;
		userService.saveUser(ko.toJSON(self.formModel)).done(function(result) {
        	if(result.success) {
        		alert("success");
        		dialog.close(self);
        	} 
        	app.showMessage(result.message);
        });
    };
    
    UserSettings.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return UserSettings;
});