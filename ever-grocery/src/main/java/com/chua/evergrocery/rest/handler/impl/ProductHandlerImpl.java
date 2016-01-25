package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.beans.ProductFormBean;
import com.chua.evergrocery.database.entity.Product;
import com.chua.evergrocery.database.service.ProductService;
import com.chua.evergrocery.rest.handler.ProductHandler;

@Transactional
@Component
public class ProductHandlerImpl implements ProductHandler {

	@Autowired
	private ProductService productService;

	@Override
	public List<Product> getProductList(String searchKey) {
		return productService.findAllWithPaging(0, Application.ITEMS_PER_PAGE, searchKey).getList();
	}
	
	@Override
	public Product getProduct(Long productId) {
		return productService.find(productId);
	}
	
	@Override
	public Boolean createProduct(ProductFormBean productForm) {
		final Product product = new Product();
		
		setProduct(product, productForm);
		
		return productService.insert(product) != null;
	}
	
	@Override
	public Boolean updateProduct(ProductFormBean productForm) {
		final Boolean success;
		
		final Product product = productService.find(productForm.getId());
		if(product != null) {
			setProduct(product, productForm);
			success = productService.update(product);
		} else {
			success = Boolean.FALSE;
		}
		
		return success;
	}

	@Override
	public Boolean removeProduct(Long productId) {
		return productService.delete(productService.find(productId));
	}
	
	private void setProduct(Product product, ProductFormBean productForm) {
		product.setName(productForm.getName());
	}
}
