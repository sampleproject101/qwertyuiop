define(['plugins/router', 'durandal/app'], function (router, app) {
	var routes = [
	  	{ route: ['', 'home'], moduleId: 'viewmodels/home', title: 'Home', nav: true },
	  	{ route: 'manage', moduleRootId: 'viewmodels/manage', title: '', nav: true, hash: '#manage',
	  		childRoutes: [
	  		    { route: 'brand', moduleId: 'brand', title: 'Brand', nav: true, hash: 'brand' },
	  		    { route: 'category', moduleId: 'category', title: 'Category', nav: true, hash: 'category' },
	  		    { route: 'company', moduleId: 'company', title: 'Company', nav: true, hash: 'company' },
	  		    { route: 'distributor', moduleId: 'distributor', title: 'Distributor', nav: true, hash: 'distributor' },
	      		{ route: 'product', moduleId: 'product', title: 'Product', nav: true, hash: 'product' },
	      		{ route: 'user', moduleId: 'user', title: 'User', nav: true, hash: 'user' }
	  		]
	  	},
	  	{ route: 'search', moduleId: 'viewmodels/search', title: '', nav: true, hash: '#search' }
	];
	
    return {
        router: router,
        
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
        }
    };
});