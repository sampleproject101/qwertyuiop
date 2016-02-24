define(['knockout'], function (ko) {
    var ProductDetails = function(productDetails, unitList, enableConfig) {
    	this.productDetails = productDetails;

    	this.unitList = unitList;
    	
    	this.enableConfig = enableConfig;
    	
    	this.formModel = {
    		id: ko.observable(),	
    		
    		title: ko.observable(),
    		
    		barcode: ko.observable(),
    		
    		quantity: ko.observable(),
    		
    		unitType: ko.observable(),
    		
    		grossPrice: ko.observable(),
    		
    		discount: ko.observable(),
    		
    		netPrice: ko.observable(),
    		
    		percentProfit: ko.observable(),
    		
    		sellingPrice: ko.observable(),
    		
    		netProfit: ko.observable(),
    		
    		stockCount: ko.observable()
    	};
    	
        this.enableQuantity = ko.observable();
        
        this.enableGrossPrice = ko.observable();
        
        this.enableDiscount = ko.observable();

        this.enableNetPrice = ko.observable();
        
        this.enablePercentProfit = ko.observable();
        
        this.enableSellingPrice = ko.observable();
        
        this.enableNetProfit = ko.observable();
        
        this.enableStockCount = ko.observable();
    };
    
    ProductDetails.prototype.activate = function() {
    	var self = this;
    	
    	self.formModel.id(self.productDetails.id);
    	self.formModel.title(self.productDetails.title);
    	self.formModel.barcode(self.productDetails.barcode);
    	self.formModel.quantity(self.productDetails.quantity);
    	self.formModel.unitType(self.productDetails.unitType);
    	self.formModel.grossPrice(self.productDetails.grossPrice);
    	self.formModel.discount(self.productDetails.discount);
    	self.formModel.netPrice(self.productDetails.netPrice);
    	self.formModel.percentProfit(self.productDetails.percentProfit);
    	self.formModel.sellingPrice(self.productDetails.sellingPrice);
    	self.formModel.netProfit(self.productDetails.netProfit);
    	self.formModel.stockCount(self.productDetails.stockCount);
    	
    	self.enableQuantity(self.enableConfig.enableQuantity);
    	self.enableGrossPrice(self.enableConfig.enableGrossPrice);
    	self.enableDiscount(self.enableConfig.enableDiscount);
    	self.enableNetPrice(self.enableConfig.enableNetPrice);
    	self.enablePercentProfit(self.enableConfig.enablePercentProfit);
    	self.enableSellingPrice(self.enableConfig.enableSellingPrice);
    	self.enableNetProfit(self.enableConfig.enableNetProfit);
    	self.enableStockCount(self.enableConfig.enableStockCount);
    };
 
    return ProductDetails;
});