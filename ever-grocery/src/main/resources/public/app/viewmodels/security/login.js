define(['durandal/app', 'knockout', 'modules/securityservice'], function (app, ko, securityService) {
	var Login = function() {
		this.username = ko.observable();
		
		this.password = ko.observable();
		
		this.errorMessage = ko.observable();
	};
	
	Login.prototype.login = function() {
		var self = this;
		
		securityService.login(self.username(), self.password()).done(function(data) {
			if(data == 'SUCCESS') {
				app.setRoot('viewmodels/shell');
			} else { // FAILURE
				self.errorMessage('Invalid Username / Password!');
			}
		});
	};
	
    return Login;
});