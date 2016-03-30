define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/userservice'], function (dialog, app, ko, userService) {
    var UserForm = function(preTitle, user) {
        this.preTitle = preTitle;
        this.user = user;
        
        this.userFormModel = {
        	id: ko.observable(),
        	firstName: ko.observable(),
        	lastName: ko.observable(),
        	itemsPerPage: ko.observable(),
        	username: ko.observable(),
        	password: ko.observable()
        };
    };
    
    UserForm.prototype.activate = function() {
    	var self = this;
    	
    	self.userFormModel.id(self.user.id);
    	self.userFormModel.firstName(self.user.firstName);
    	self.userFormModel.lastName(self.user.lastName);
    	self.userFormModel.itemsPerPage(self.user.itemsPerPage);
    	self.userFormModel.username(self.user.username);
    	self.userFormModel.password(self.user.password);
    };
 
    UserForm.show = function(preTitle, user) {
        return dialog.show(new UserForm(preTitle, user));
    };
    
    UserForm.prototype.save = function() {
    	var self = this;
    	
        userService.saveUser(ko.toJSON(self.userFormModel)).done(function(result) {
        	if(result.success) {
        		dialog.close(self);	
        	} 
        	app.showMessage(result.message);
        });
    };
    
    UserForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return UserForm;
});