define(['jquery'], function ($) {
    return {
    	getItemsPerPage: function() {
    		return $.ajax({
    			url: '/services/settings/itemsperpage'
    		});
    	}
    };
});