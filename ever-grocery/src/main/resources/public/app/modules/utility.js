define(['knockout'], function (ko) {
    return {
    	computeNetPrice: function(base, discount) {
    		return base - (base * discount / 100);
    	},
    	
    	computeSellingPrice: function(base, percentProfit) {
    		return base + (base * percentProfit / 100);	//I need ceil .. create roundoff function
    	},
    	
    	computeNetProfit: function(base, selling) {
    		return selling - base;
    	}
    };
});