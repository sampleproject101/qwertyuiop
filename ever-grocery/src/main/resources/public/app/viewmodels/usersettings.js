define(['plugins/dialog', 'durandal/app', 'knockout'], function (dialog, app, ko) {
    var UserSettings = function(user) {
        this.user = user;
    };
    
    UserSettings.prototype.activate = function() {
    	var self = this;
    	
    	
    };
 
    UserSettings.show = function(user) {
        return dialog.show(new UserSettings(user));
    };
    
    /*UserSettings.prototype.save = function() {
    	var self = this;
    	
        userService.saveUser(ko.toJSON(self.userFormModel)).done(function(result) {
        	if(result.success) {
        		dialog.close(self);	
        	} 
        	app.showMessage(result.message);
        });
    };*/
    
    UserSettings.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return UserSettings;
});