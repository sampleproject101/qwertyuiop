define(['plugins/router', 'durandal/app', 'modules/securityservice', 'modules/userservice', 'viewmodels/usersettings'], function (router, app, securityService, userService, UserSettings) {
	var routes = [
	  	{ route: ['', 'home'], moduleId: 'viewmodels/home', title: 'Home', nav: true },
	  	{ route: 'manage', moduleRootId: 'viewmodels/manage', title: '', nav: true, hash: '#manage',
	  		childRoutes: [
	  		    { route: 'brand', moduleId: 'brand', title: 'Brand', nav: true, hash: 'brand' },
	  		    { route: 'category', moduleId: 'category', title: 'Category', nav: true, hash: 'category' },
	  		    { route: 'company', moduleId: 'company', title: 'Company', nav: true, hash: 'company' },
	  		    { route: 'customer', moduleId: 'customer', title: 'Customer', nav: true, hash: 'customer' },
	  		    { route: 'distributor', moduleId: 'distributor', title: 'Distributor', nav: true, hash: 'distributor' },
	      		{ route: 'product', moduleId: 'product', title: 'Product', nav: true, hash: 'product' },
	      		{ route: 'user', moduleId: 'user', title: 'User', nav: true, hash: 'user' }
	  		]
	  	},
	  	{ route: 'customerorder', moduleId: 'viewmodels/customerorder', title: 'Customer Order', nav: true, hash: '#customerorder' },
	  	{ route: 'search', moduleId: 'viewmodels/search', title: '', nav: true, hash: '#search' }
	];
	
    return {
        router: router,
        
        user: app.user,
        
        activate: function () {
        	$.each(routes, function(index, route) {
                if (route.childRoutes === undefined)
                    return
                $.each(route.childRoutes, function(index, childRoute) {
                    childRoute.route = route.route + '/' + childRoute.route;
                    childRoute.moduleId = route.moduleRootId + '/' + childRoute.moduleId;
                    childRoute.title = childRoute.title;
                    childRoute.hash = route.hash + '/' + childRoute.hash;
                    childRoute.parent = route.moduleRootId;
                });
                routes = routes.concat(route.childRoutes);
            });
        	
            router.map(routes).buildNavigationModel();
            
            return router.activate();
        },
        
        logout: function() {
        	securityService.logout().done(function() {
        		location.href = '/';
        	});
        },
        
        userSettings: function() {
        	var self = this;
        	
        	UserSettings.show(self.user);
        }
    };
});