define(['durandal/app', 'knockout', 'modules/securityservice', 'modules/userservice', 'viewmodels/manage/userform'], function (app, ko, securityService, userService, UserForm) {
	var User = function() {
		this.userList = ko.observable();
		
		this.searchKey = ko.observable();
		
		this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
	};
	
	User.prototype.canActivate = function() {
		var deferred = $.Deferred();
	    return deferred.then(securityService.authenticatePage('manage/user').done(function(result) {
	        if (result.success) {
	            deferred.resolve(result.success);
	        } else {
	        	app.showMessage(result.message);
	            deferred.resolve({ 'redirect': '/' });
	        }
	        return deferred.promise();
	    }));
	};
	
	User.prototype.activate = function() {
		var self = this;

		self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshUserList();
		});
		
		self.refreshUserList();
	};
	
	User.prototype.refreshUserList = function() {
		var self = this;
		
		userService.getUserList(self.currentPage(), self.searchKey()).done(function(data) {
			self.userList(data.list);
			self.totalItems(data.total);
		});
	};
	
	User.prototype.search = function() {
		var self = this;
		
		self.currentPage(1);
		self.refreshUserList();
	};
	
	User.prototype.create = function() {
		var self = this;
		
		UserForm.show('Create', new Object()).then(function() {
			self.refreshUserList();
		});
	};
	
	User.prototype.edit = function(userId) {
		var self = this;
		
		userService.getUser(userId).done(function(data) {
			UserForm.show('Update', data).then(function() {
				self.refreshUserList();
			});
		});
	};
	
	User.prototype.remove = function(userId, userName) {
		var self = this;
		
		app.showMessage('Are you sure you want to remove User "' + userName + '"?',
				'Confirm Remove',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				userService.removeUser(userId).done(function(result) {
					self.refreshUserList();
					app.showMessage(result.message);
				});
			}
		})
	};
	
    return User;
});