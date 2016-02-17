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

    	self.productDetailsFormModel.piece.formModel.quantity.subscribe(function(newValue) {
    		if(newValue > 1) {
    			self.productDetailsFormModel.whole.formModel.quantity(1);
    		}
    		else {
    			self.productDetailsFormModel.whole.formModel.quantity(0);
    		}
    	});
    	
    	self.productDetailsFormModel.whole.formModel.grossPrice(0);
    	self.productDetailsFormModel.whole.formModel.discount(0);
    	self.productDetailsFormModel.whole.formModel.netPrice(0);
    	self.productDetailsFormModel.whole.formModel.percentProfit(3.75);
    	self.productDetailsFormModel.whole.formModel.sellingPrice(0);
    	self.productDetailsFormModel.whole.formModel.netProfit(0);
    	self.productDetailsFormModel.whole.formModel.stockCount(0);
    	
    	self.productDetailsFormModel.whole.formModel.grossPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.formModel.netPrice(utility.computeNetPrice(newValue, self.productDetailsFormModel.whole.formModel.discount()));
    	});
    	
    	self.productDetailsFormModel.whole.formModel.discount.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.formModel.netPrice(utility.computeNetPrice(self.productDetailsFormModel.whole.formModel.grossPrice(), newValue));
    	});
    	
    	self.productDetailsFormModel.whole.formModel.percentProfit.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.formModel.sellingPrice(utility.computeSellingPrice(self.productDetailsFormModel.whole.formModel.netPrice(), newValue));
    		self.productDetailsFormModel.whole.formModel.netProfit(utility.computeNetProfit(self.productDetailsFormModel.whole.formModel.netPrice(), self.productDetailsFormModel.whole.formModel.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.whole.formModel.netPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.formModel.sellingPrice(utility.computeSellingPrice(newValue, self.productDetailsFormModel.whole.formModel.percentProfit()));
    		self.productDetailsFormModel.whole.formModel.netProfit(utility.computeNetProfit(newValue, self.productDetailsFormModel.whole.formModel.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.piece.formModel.quantity(0);
    	self.productDetailsFormModel.piece.formModel.grossPrice(0);
    	self.productDetailsFormModel.piece.formModel.discount(0);
    	self.productDetailsFormModel.piece.formModel.netPrice(0);
    	self.productDetailsFormModel.piece.formModel.percentProfit(5);
    	self.productDetailsFormModel.piece.formModel.sellingPrice(0);
    	self.productDetailsFormModel.piece.formModel.netProfit(0);
    	self.productDetailsFormModel.piece.formModel.stockCount(0);
    	
    	self.productDetailsFormModel.piece.formModel.quantity.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.formModel.grossPrice(self.productDetailsFormModel.piece.formModel.grossPrice() * newValue);
    	});
    	
    	self.productDetailsFormModel.piece.formModel.grossPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.formModel.grossPrice(newValue * self.productDetailsFormModel.piece.formModel.quantity());
    		self.productDetailsFormModel.piece.formModel.netPrice(utility.computeNetPrice(newValue, self.productDetailsFormModel.piece.formModel.discount()));
    		self.productDetailsFormModel.innerPiece.formModel.grossPrice(newValue / self.productDetailsFormModel.innerPiece.formModel.quantity());
    	});
    	
    	self.productDetailsFormModel.piece.formModel.discount.subscribe(function(newValue) {
    		self.productDetailsFormModel.whole.formModel.discount(newValue);
    		self.productDetailsFormModel.piece.formModel.netPrice(utility.computeNetPrice(self.productDetailsFormModel.piece.formModel.grossPrice(), newValue));
    		self.productDetailsFormModel.innerPiece.formModel.discount(newValue);
    		self.productDetailsFormModel.secondInnerPiece.formModel.discount(newValue);
    	});
    	
    	self.productDetailsFormModel.piece.formModel.percentProfit.subscribe(function(newValue) {
    		self.productDetailsFormModel.piece.formModel.sellingPrice(utility.computeSellingPrice(self.productDetailsFormModel.piece.formModel.netPrice(), newValue));
    		self.productDetailsFormModel.piece.formModel.netProfit(utility.computeNetProfit(self.productDetailsFormModel.piece.formModel.netPrice(), self.productDetailsFormModel.piece.formModel.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.piece.formModel.netPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.piece.formModel.sellingPrice(utility.computeSellingPrice(newValue, self.productDetailsFormModel.piece.formModel.percentProfit()));
    		self.productDetailsFormModel.piece.formModel.netProfit(utility.computeNetProfit(newValue, self.productDetailsFormModel.piece.formModel.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.innerPiece.formModel.quantity(0);
    	self.productDetailsFormModel.innerPiece.formModel.grossPrice(0);
    	self.productDetailsFormModel.innerPiece.formModel.discount(0);
    	self.productDetailsFormModel.innerPiece.formModel.netPrice(0);
    	self.productDetailsFormModel.innerPiece.formModel.percentProfit(7.5);
    	self.productDetailsFormModel.innerPiece.formModel.sellingPrice(0);
    	self.productDetailsFormModel.innerPiece.formModel.netProfit(0);
    	self.productDetailsFormModel.innerPiece.formModel.stockCount(0);
    	
    	self.productDetailsFormModel.innerPiece.formModel.quantity.subscribe(function(newValue) {
    		self.productDetailsFormModel.innerPiece.formModel.grossPrice(self.productDetailsFormModel.piece.formModel.grossPrice() / newValue);
    	});
    	
    	self.productDetailsFormModel.innerPiece.formModel.grossPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.innerPiece.formModel.netPrice(utility.computeNetPrice(newValue, self.productDetailsFormModel.innerPiece.formModel.discount()));
    		self.productDetailsFormModel.secondInnerPiece.formModel.grossPrice(newValue / self.productDetailsFormModel.secondInnerPiece.formModel.quantity());
    	});
    	
    	self.productDetailsFormModel.innerPiece.formModel.discount.subscribe(function(newValue) {
    		self.productDetailsFormModel.innerPiece.formModel.netPrice(utility.computeNetPrice(self.productDetailsFormModel.innerPiece.formModel.grossPrice(), newValue));
    	});
    	
    	self.productDetailsFormModel.innerPiece.formModel.percentProfit.subscribe(function(newValue) {
    		self.productDetailsFormModel.innerPiece.formModel.sellingPrice(utility.computeSellingPrice(self.productDetailsFormModel.innerPiece.formModel.netPrice(), newValue));
    		self.productDetailsFormModel.innerPiece.formModel.netProfit(utility.computeNetProfit(self.productDetailsFormModel.innerPiece.formModel.netPrice(), self.productDetailsFormModel.innerPiece.formModel.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.innerPiece.formModel.netPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.innerPiece.formModel.sellingPrice(utility.computeSellingPrice(newValue, self.productDetailsFormModel.innerPiece.formModel.percentProfit()));
    		self.productDetailsFormModel.innerPiece.formModel.netProfit(utility.computeNetProfit(newValue, self.productDetailsFormModel.innerPiece.formModel.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.secondInnerPiece.formModel.quantity(0);
    	self.productDetailsFormModel.secondInnerPiece.formModel.grossPrice(0);
    	self.productDetailsFormModel.secondInnerPiece.formModel.discount(0);
    	self.productDetailsFormModel.secondInnerPiece.formModel.netPrice(0);
    	self.productDetailsFormModel.secondInnerPiece.formModel.percentProfit(11.25);
    	self.productDetailsFormModel.secondInnerPiece.formModel.sellingPrice(0);
    	self.productDetailsFormModel.secondInnerPiece.formModel.netProfit(0);
    	self.productDetailsFormModel.secondInnerPiece.formModel.stockCount(0);
    	
    	self.productDetailsFormModel.secondInnerPiece.formModel.quantity.subscribe(function(newValue) {
    		self.productDetailsFormModel.secondInnerPiece.formModel.grossPrice(self.productDetailsFormModel.innerPiece.formModel.grossPrice() / newValue);
    	});
    	
    	self.productDetailsFormModel.secondInnerPiece.formModel.grossPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.secondInnerPiece.formModel.netPrice(utility.computeNetPrice(newValue, self.productDetailsFormModel.secondInnerPiece.formModel.discount()));
    	});
    	
    	self.productDetailsFormModel.secondInnerPiece.formModel.discount.subscribe(function(newValue) {
    		self.productDetailsFormModel.secondInnerPiece.formModel.netPrice(utility.computeNetPrice(self.productDetailsFormModel.secondInnerPiece.formModel.grossPrice(), newValue));
    	});
    	
    	self.productDetailsFormModel.secondInnerPiece.formModel.percentProfit.subscribe(function(newValue) {
    		self.productDetailsFormModel.secondInnerPiece.formModel.sellingPrice(utility.computeSellingPrice(self.productDetailsFormModel.secondInnerPiece.formModel.netPrice(), newValue));
    		self.productDetailsFormModel.secondInnerPiece.formModel.netProfit(utility.computeNetProfit(self.productDetailsFormModel.secondInnerPiece.formModel.netPrice(), self.productDetailsFormModel.secondInnerPiece.formModel.sellingPrice()));
    	});
    	
    	self.productDetailsFormModel.secondInnerPiece.formModel.netPrice.subscribe(function(newValue) {
    		self.productDetailsFormModel.secondInnerPiece.formModel.sellingPrice(utility.computeSellingPrice(newValue, self.productDetailsFormModel.secondInnerPiece.formModel.percentProfit()));
    		self.productDetailsFormModel.secondInnerPiece.formModel.netProfit(utility.computeNetProfit(newValue, self.productDetailsFormModel.secondInnerPiece.formModel.sellingPrice()));
    	});
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