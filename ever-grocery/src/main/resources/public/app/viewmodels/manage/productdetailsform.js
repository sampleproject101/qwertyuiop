define(['plugins/dialog', 'knockout', 'viewmodels/manage/productdetails', 'modules/utility', 'modules/productservice'], function (dialog, ko, ProductDetails, utility, productService) {
    var ProductDetailsForm = function(product) {
        this.product = product;
        
        this.productDetailsFormModel = {
        	id: ko.observable(),
        	
        	whole: new ProductDetails("Whole", new Object(), ko.observableArray([ 'Case', 'Bundle' ]),
        			{ enableQuantity : false, enableGrossPrice : false, enableDiscount : false, enableNetPrice : false, 
        			  enablePercentProfit : true, enableSellingPrice : false, enableNetProfit : false, enableStockCount : true }),
        			  
        	piece: new ProductDetails("Piece", new Object(), ko.observableArray([ 'Piece', 'Bag', 'Can' ]),
        			{ enableQuantity : true, enableGrossPrice : true, enableDiscount : true, enableNetPrice : false, 
  			  		  enablePercentProfit : true, enableSellingPrice : false, enableNetProfit : false, enableStockCount : true }),
  			  		  
        	innerPiece: new ProductDetails("Inner Piece", new Object(), ko.observableArray([ 'Piece', 'Bag', 'Can' ]),
        			{ enableQuantity : true, enableGrossPrice : false, enableDiscount : false, enableNetPrice : false, 
  			  		  enablePercentProfit : true, enableSellingPrice : false, enableNetProfit : false, enableStockCount : true }),
  			  		  
        	secondInnerPiece: new ProductDetails("2nd Inner Piece", new Object(), ko.observableArray([ 'Piece', 'Bag', 'Can' ]),
        			{ enableQuantity : true, enableGrossPrice : false, enableDiscount : false, enableNetPrice : false, 
  			  		  enablePercentProfit : true, enableSellingPrice : false, enableNetProfit : false, enableStockCount : true })
        };
    };
 
    ProductDetailsForm.prototype.compositionComplete = function() {
    	var self = this;
    	
    	self.productDetailsFormModel.id(self.product.id);

    	/*self.productDetailsFormModel.piece.quantity.subscribe(function(newValue) {
    		if(newValue > 1) {
    			self.productDetailsFormModel.whole.quantity(1);
    		}
    		else {
    			self.productDetailsFormModel.whole.quantity(0);
    		}
    	});
    	
    	self.productDetailsFormModel.whole.grossPrice(0);
    	self.productDetailsFormModel.whole.discount(0);
    	self.productDetailsFormModel.whole.netPrice(0);
    	self.productDetailsFormModel.whole.percentProfit(3.75);
    	self.productDetailsFormModel.whole.sellingPrice(0);
    	self.productDetailsFormModel.whole.netProfit(0);
    	self.productDetailsFormModel.whole.stockCount(0);
    	
    	self.productDetailsFormModel.whole.grossPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.netPrice(utility.computeNetPrice(newValue, self.productDetailsFormModel.whole.discount()));
    	});
    	
    	self.productDetailsFormModel.whole.discount.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.netPrice(utility.computeNetPrice(self.productDetailsFormModel.whole.grossPrice(), newValue));
    	});
    	
    	self.productDetailsFormModel.whole.percentProfit.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.sellingPrice(utility.computeSellingPrice(self.productDetailsFormModel.whole.netPrice(), newValue));
    		self.productDetailsFormModel.whole.netProfit(utility.computeNetProfit(self.productDetailsFormModel.whole.netPrice(), self.productDetailsFormModel.whole.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.whole.netPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.sellingPrice(utility.computeSellingPrice(newValue, self.productDetailsFormModel.whole.percentProfit()));
    		self.productDetailsFormModel.whole.netProfit(utility.computeNetProfit(newValue, self.productDetailsFormModel.whole.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.piece.quantity(0);
    	self.productDetailsFormModel.piece.grossPrice(0);
    	self.productDetailsFormModel.piece.discount(0);
    	self.productDetailsFormModel.piece.netPrice(0);
    	self.productDetailsFormModel.piece.percentProfit(5);
    	self.productDetailsFormModel.piece.sellingPrice(0);
    	self.productDetailsFormModel.piece.netProfit(0);
    	self.productDetailsFormModel.piece.stockCount(0);
    	
    	self.productDetailsFormModel.piece.quantity.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.grossPrice(self.productDetailsFormModel.piece.grossPrice() * newValue);
    	});
    	
    	self.productDetailsFormModel.piece.grossPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.grossPrice(newValue * self.productDetailsFormModel.piece.quantity());
    		self.productDetailsFormModel.piece.netPrice(utility.computeNetPrice(newValue, self.productDetailsFormModel.piece.discount()));
    		self.productDetailsFormModel.innerPiece.grossPrice(newValue / self.productDetailsFormModel.innerPiece.quantity());
    	});
    	
    	self.productDetailsFormModel.piece.discount.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.discount(newValue);
    		self.productDetailsFormModel.piece.netPrice(utility.computeNetPrice(self.productDetailsFormModel.piece.grossPrice(), newValue));
    		self.productDetailsFormModel.innerPiece.discount(newValue);
    		self.productDetailsFormModel.secondInnerPiece.discount(newValue);
    	});
    	
    	self.productDetailsFormModel.piece.percentProfit.subscribe(function(newValue) {
    		self.productDetailsFormModel.piece.sellingPrice(utility.computeSellingPrice(self.productDetailsFormModel.piece.netPrice(), newValue));
    		self.productDetailsFormModel.piece.netProfit(utility.computeNetProfit(self.productDetailsFormModel.piece.netPrice(), self.productDetailsFormModel.piece.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.piece.netPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.piece.sellingPrice(utility.computeSellingPrice(newValue, self.productDetailsFormModel.piece.percentProfit()));
    		self.productDetailsFormModel.piece.netProfit(utility.computeNetProfit(newValue, self.productDetailsFormModel.piece.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.innerPiece.quantity(0);
    	self.productDetailsFormModel.innerPiece.grossPrice(0);
    	self.productDetailsFormModel.innerPiece.discount(0);
    	self.productDetailsFormModel.innerPiece.netPrice(0);
    	self.productDetailsFormModel.innerPiece.percentProfit(7.5);
    	self.productDetailsFormModel.innerPiece.sellingPrice(0);
    	self.productDetailsFormModel.innerPiece.netProfit(0);
    	self.productDetailsFormModel.innerPiece.stockCount(0);
    	
    	self.productDetailsFormModel.innerPiece.quantity.subscribe(function(newValue) {
    		self.productDetailsFormModel.innerPiece.grossPrice(self.productDetailsFormModel.piece.grossPrice() / newValue);
    	});
    	
    	self.productDetailsFormModel.innerPiece.grossPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.innerPiece.netPrice(utility.computeNetPrice(newValue, self.productDetailsFormModel.innerPiece.discount()));
    		self.productDetailsFormModel.secondInnerPiece.grossPrice(newValue / self.productDetailsFormModel.secondInnerPiece.quantity());
    	});
    	
    	self.productDetailsFormModel.innerPiece.discount.subscribe(function(newValue) {
    		self.productDetailsFormModel.innerPiece.netPrice(utility.computeNetPrice(self.productDetailsFormModel.innerPiece.grossPrice(), newValue));
    	});
    	
    	self.productDetailsFormModel.innerPiece.percentProfit.subscribe(function(newValue) {
    		self.productDetailsFormModel.innerPiece.sellingPrice(utility.computeSellingPrice(self.productDetailsFormModel.innerPiece.netPrice(), newValue));
    		self.productDetailsFormModel.innerPiece.netProfit(utility.computeNetProfit(self.productDetailsFormModel.innerPiece.netPrice(), self.productDetailsFormModel.innerPiece.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.innerPiece.netPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.innerPiece.sellingPrice(utility.computeSellingPrice(newValue, self.productDetailsFormModel.innerPiece.percentProfit()));
    		self.productDetailsFormModel.innerPiece.netProfit(utility.computeNetProfit(newValue, self.productDetailsFormModel.innerPiece.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.secondInnerPiece.quantity(0);
    	self.productDetailsFormModel.secondInnerPiece.grossPrice(0);
    	self.productDetailsFormModel.secondInnerPiece.discount(0);
    	self.productDetailsFormModel.secondInnerPiece.netPrice(0);
    	self.productDetailsFormModel.secondInnerPiece.percentProfit(11.25);
    	self.productDetailsFormModel.secondInnerPiece.sellingPrice(0);
    	self.productDetailsFormModel.secondInnerPiece.netProfit(0);
    	self.productDetailsFormModel.secondInnerPiece.stockCount(0);
    	
    	self.productDetailsFormModel.secondInnerPiece.quantity.subscribe(function(newValue) {
    		self.productDetailsFormModel.secondInnerPiece.grossPrice(self.productDetailsFormModel.innerPiece.grossPrice() / newValue);
    	});
    	
    	self.productDetailsFormModel.secondInnerPiece.grossPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.secondInnerPiece.netPrice(utility.computeNetPrice(newValue, self.productDetailsFormModel.secondInnerPiece.discount()));
    	});
    	
    	self.productDetailsFormModel.secondInnerPiece.discount.subscribe(function(newValue) {
    		self.productDetailsFormModel.secondInnerPiece.netPrice(utility.computeNetPrice(self.productDetailsFormModel.secondInnerPiece.grossPrice(), newValue));
    	});
    	
    	self.productDetailsFormModel.secondInnerPiece.percentProfit.subscribe(function(newValue) {
    		self.productDetailsFormModel.secondInnerPiece.sellingPrice(utility.computeSellingPrice(self.productDetailsFormModel.secondInnerPiece.netPrice(), newValue));
    		self.productDetailsFormModel.secondInnerPiece.netProfit(utility.computeNetProfit(self.productDetailsFormModel.secondInnerPiece.netPrice(), self.productDetailsFormModel.secondInnerPiece.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.secondInnerPiece.netPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.secondInnerPiece.sellingPrice(utility.computeSellingPrice(newValue, self.productDetailsFormModel.secondInnerPiece.percentProfit()));
    		self.productDetailsFormModel.secondInnerPiece.netProfit(utility.computeNetProfit(newValue, self.productDetailsFormModel.secondInnerPiece.sellingPrice()));
    	});*/
    };
    
    ProductDetailsForm.show = function(product) {
        return dialog.show(new ProductDetailsForm(product));
    };
    
    ProductDetailsForm.prototype.save = function() {
    	var self = this;
    	
    	productService.saveProductDetails(ko.toJSON(self.productDetailsFormModel.whole.formModel)).done(function(data) {
        	dialog.close(self, data);
        });
    };
    
    ProductDetailsForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return ProductDetailsForm;
});