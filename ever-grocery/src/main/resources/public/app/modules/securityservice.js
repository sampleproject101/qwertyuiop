define(['jquery'], function ($) {
    return {
    	getUser: function() {
    		return $.ajax({
    			url: '/services/security/user'
    		});
    	},
    	
    	login: function(username, password) {
    		return $.ajax({
    			url: '/login',
    			method: 'POST',
    			data: {
    				username: username,
    				password: password
    			}
    		});
    	},
    	
    	logout: function() {
    		return $.ajax({
    			url: '/services/security/logout',
    			method: 'POST'
    		});
    	},
    	
    	authenticatePage: function(page) {
    		return $.ajax({
    			url: '/services/security/authenticatepage',
    			method: 'POST',
    			data: {
    				page: page
    			}
    		});
    	}
    };
});