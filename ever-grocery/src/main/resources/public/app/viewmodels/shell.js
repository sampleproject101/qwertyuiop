﻿define(['plugins/router', 'durandal/app'], function (router, app) {
    return {
        router: router,
        search: function() {
            //It's really easy to show a message box.
            //You can add custom options too. Also, it returns a promise for the user's response.
            app.showMessage('Search not yet implemented...');
        },
        activate: function () {
            router.map([
                { route: '', title:'Home', moduleId: 'viewmodels/home', nav: false },
                { route: 'brand', moduleId: 'viewmodels/brand', nav: false },
                { route: 'category', moduleId: 'viewmodels/category', nav: false},
                { route: 'company', moduleId: 'viewmodels/company', nav: false},
                { route: 'distributor', moduleId: 'viewmodels/distributor', nav: false},
                { route: 'product', moduleId: 'viewmodels/product', nav: false}
            ]).buildNavigationModel();
            
            return router.activate();
        }
    };
});