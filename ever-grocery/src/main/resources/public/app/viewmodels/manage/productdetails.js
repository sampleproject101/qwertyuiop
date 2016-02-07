define(['knockout'], function (ko) {
    var ProductDetails = function(title, productDetails, unitList, enableConfig) {
    	this.title = title;
    	
    	this.productDetails = productDetails;

    	this.unitList = unitList;
    	
    	this.enableConfig = enableConfig;
    	
        this.barcode = ko.observable();
        
        this.quantity = ko.observable();
        this.enableQuantity = ko.observable();
        
        this.unit = ko.observable();
        
        this.grossPrice = ko.observable();
        this.enableGrossPrice = ko.observable();
        
        this.discount = ko.observable();
        this.enableDiscount = ko.observable();

        this.netPrice = ko.observable();
        this.enableNetPrice = ko.observable();
        
        this.percentProfit = ko.observable();
        this.enablePercentProfit = ko.observable();
        
        this.sellingPrice = ko.observable();
        this.enableSellingPrice = ko.observable();
        
        this.netProfit = ko.observable();
        this.enableNetProfit = ko.observable();
        
        this.stockCount = ko.observable();
        this.enableStockCount = ko.observable();
    };
    
    ProductDetails.prototype.activate = function() {
    	var self = this;
    	
    	self.barcode(self.productDetails.barcode);
    	self.quantity(self.productDetails.quantity);
    	self.unit(self.productDetails.unit);
    	self.grossPrice(self.productDetails.grossPrice);
    	self.discount(self.productDetails.discount);
    	self.netPrice(self.productDetails.netPrice);
    	self.percentProfit(self.productDetails.percentProfit);
    	self.sellingPrice(self.productDetails.sellingPrice);
    	self.netProfit(self.productDetails.netProfit);
    	self.stockCount(self.productDetails.stockCount);
    	
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