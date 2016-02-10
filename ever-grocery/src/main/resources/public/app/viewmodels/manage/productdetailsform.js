define(['plugins/dialog', 'knockout', 'viewmodels/manage/productdetails'], function (dialog, ko, ProductDetails) {
    var ProductDetailsForm = function(product) {
        this.product = product;
        
        this.productDetailsFormModel = {
        	id: ko.observable(),
        	
        	whole: new ProductDetails("Whole Wheat", new Object(), ko.observableArray([ 'Case', 'Bundle' ]),
        			{ enableQuantity : false, enableGrossPrice : false, enableDiscount : false, enableNetPrice : false, 
        			  enablePercentProfit : true, enableSellingPrice : false, enableNetProfit : false, enableStockCount : true }),
        			  
        	piece: new ProductDetails("Piece", new Object(), ko.observableArray([ 'Piece', 'Bag', 'Can' ]),
        			{ enableQuantity : true, enableGrossPrice : true, enableDiscount : true, enableNetPrice : false, 
  			  		  enablePercentProfit : true, enableSellingPrice : false, enableNetProfit : false, enableStockCount : true }),
  			  		  
        	innerPiece: new ProductDetails("Inner Piece", new Object(), ko.observableArray([ 'Piece', 'Bag', 'Can' ]),
        			{ enableQuantity : true, enableGrossPrice : false, enableDiscount : false, enableNetPrice : false, 
  			  		  enablePercentProfit : false, enableSellingPrice : false, enableNetProfit : false, enableStockCount : true }),
  			  		  
        	secondInnerPiece: new ProductDetails("2nd Inner Piece", new Object(), ko.observableArray([ 'Piece', 'Bag', 'Can' ]),
        			{ enableQuantity : true, enableGrossPrice : false, enableDiscount : false, enableNetPrice : false, 
  			  		  enablePercentProfit : false, enableSellingPrice : false, enableNetProfit : false, enableStockCount : true })
        };
    };
 
    ProductDetailsForm.prototype.compositionComplete = function() {
    	var self = this;
    	
    	self.productDetailsFormModel.id(self.product.id);
    	self.productDetailsFormModel.piece.grossPrice(0);
    	self.productDetailsFormModel.piece.discount(0);
    };
    
    ProductDetailsForm.show = function(product) {
        return dialog.show(new ProductDetailsForm(product));
    };
    
    ProductDetailsForm.prototype.save = function() {
    	var self = this;
    	
        alert('save');
    };
    
    ProductDetailsForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return ProductDetailsForm;
});