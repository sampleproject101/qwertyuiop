define(['plugins/router', 'durandal/app', 'modules/securityservice', 'modules/userservice', 'viewmodels/usersettings'], function (router, app, securityService, userService, UserSettings) {
	var routes = [
	  	{ route: ['', 'home'], moduleId: 'viewmodels/home', title: 'Home', nav: true },
	  	{ route: 'manage', moduleRootId: 'viewmodels/manage', title: '', nav: true, hash: '#manage',
	  		childRoutes: [
	  		    { route: 'brand', moduleId: 'brand', title: 'Brands', nav: true, hash: 'brand' },
	  		    { route: 'category', moduleId: 'category', title: 'Categories', nav: true, hash: 'category' },
	  		    { route: 'company', moduleId: 'company', title: 'Companies', nav: true, hash: 'company' },
	  		    { route: 'distributor', moduleId: 'distributor', title: 'Distributors', nav: true, hash: 'distributor' },
	      		{ route: 'product', moduleId: 'product', title: 'Products', nav: true, hash: 'product' },
	      		
	      		{ route: 'user', moduleId: 'user', title: 'Users', nav: true, hash: 'user' },
	      		
	      		{ route: 'customer', moduleId: 'customer', title: 'Customers', nav: true, hash: 'customer' }
	  		]
	  	},
	  	{ route: 'customerorder', moduleId: 'viewmodels/customer-order/customerorder', title: 'Customer Order', nav: true, hash: '#customerorder' },
	  	{ route: 'cashier', moduleId: 'viewmodels/cashier/cashier', title: '', nav: true, hash: '#cashier'},
	  	
	  	{ route: 'purchaseorder', moduleId: 'viewmodels/purchase-order/purchaseorder', title: 'Purchase Order', nav: true, hash: '#purchaseorder'},
	  	
	  	{ route: 'search', moduleId: 'viewmodels/search/search', title: '', nav: true, hash: '#search' },
	  	
	  	{ route: 'customerorderpage/:id', moduleId: 'viewmodels/customer-order/customerorderpage', title: 'Customer Order Page', nav: false, hash: '#customerorderpage' }
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