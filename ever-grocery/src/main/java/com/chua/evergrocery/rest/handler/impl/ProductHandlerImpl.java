package com.chua.evergrocery.rest.handler.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chua.evergrocery.Application;
import com.chua.evergrocery.beans.ProductDetailsFormBean;
import com.chua.evergrocery.beans.ProductFormBean;
import com.chua.evergrocery.beans.ResultBean;
import com.chua.evergrocery.database.entity.Product;
import com.chua.evergrocery.database.entity.ProductDetail;
import com.chua.evergrocery.database.service.BrandService;
import com.chua.evergrocery.database.service.CategoryService;
import com.chua.evergrocery.database.service.CompanyService;
import com.chua.evergrocery.database.service.DistributorService;
import com.chua.evergrocery.database.service.ProductDetailService;
import com.chua.evergrocery.database.service.ProductService;
import com.chua.evergrocery.objects.ObjectList;
import com.chua.evergrocery.rest.handler.ProductHandler;

@Transactional
@Component
public class ProductHandlerImpl implements ProductHandler {

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private DistributorService distributorService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDetailService productDetailService;

	@Override
	public ObjectList<Product> getProductList(Integer pageNumber, String searchKey) {
		return productService.findAllWithPaging(pageNumber, Application.ITEMS_PER_PAGE, searchKey);
	}
	
	@Override
	public Product getProduct(Long productId) {
		return productService.find(productId);
	}
	
	@Override
	public ResultBean createProduct(ProductFormBean productForm) {
		final ResultBean result;
		
		if(!productService.isExistsByName(productForm.getName())) {
			final Product product = new Product();
			setProduct(product, productForm);
			
			result = new ResultBean();
			result.setSuccess(productService.insert(product) != null);
			if(result.getSuccess()) {
				result.setMessage("Product successfully created.");
			} else {
				result.setMessage("Failed to create product.");
			}
		} else {
			result = new ResultBean(false, "Product \"" + productForm.getName() + "\" already exists!");
		}
		
		return result;
	}
	
	@Override
	public ResultBean updateProduct(ProductFormBean productForm) {
		final ResultBean result;
		
		final Product product = productService.find(productForm.getId());
		if(product != null) {
			if(!(StringUtils.trimToEmpty(product.getName()).equalsIgnoreCase(productForm.getName())) &&
					productService.isExistsByName(productForm.getName())) {
				result = new ResultBean(false, "Product \"" + productForm.getName() + "\" already exists!");
			} else {
				setProduct(product, productForm);
				
				result = new ResultBean();
				result.setSuccess(productService.update(product));
				if(result.getSuccess()) {
					result.setMessage("Product successfully updated.");
				} else {
					result.setMessage("Failed to update product.");
				}
			}
		} else {
			result = new ResultBean(false, "Product not found.");
		}
		
		return result;
	}

	@Override
	public ResultBean removeProduct(Long productId) {
		final ResultBean result;
		
		final Product product = productService.find(productId);
		if(product != null) {
			result = new ResultBean();
			
			result.setSuccess(productService.delete(product));
			if(result.getSuccess()) {
				result.setMessage("Successfully removed Product \"" + product.getName() + "\".");
			} else {
				result.setMessage("Failed to remove Product \"" + product.getName() + "\".");
			}
		} else {
			result = new ResultBean(false, "Product not found.");
		}
		
		return result;
	}
	
	@Override
	public Boolean upsertProductDetails(Long productId, List<ProductDetailsFormBean> productDetailsFormList) {
		final Product product = productService.find(productId);
		if(product != null) {
			ProductDetail productDetail;
			for(ProductDetailsFormBean productDetailsForm : productDetailsFormList) {
				productDetail = productDetailService.find(productDetailsForm.getProductDetailId());
				if(productDetail == null) {
					productDetail = new ProductDetail();
				}
				setProductDetail(productDetail, productDetailsForm);
				
				if(productDetail.getId() == null) {
					productDetail.setProduct(product);
					productDetailService.insert(productDetail);
				} else {
					productDetailService.update(productDetail);
				}
			}
		} else {
			
		}
		
		return true;
	}
	
	private void setProduct(Product product, ProductFormBean productForm) {
		product.setName(productForm.getName());
		product.setBrand(brandService.find(productForm.getBrandId()));
		product.setCategory(categoryService.find(productForm.getCategoryId()));
		product.setCompany(companyService.find(productForm.getCompanyId()));
		product.setDistributor(distributorService.find(productForm.getDistributorId()));
	}
	
	private void setProductDetail(ProductDetail productDetail, ProductDetailsFormBean productDetailsForm) {
		productDetail.setTitle(productDetailsForm.getTitle());
		productDetail.setBarcode(productDetailsForm.getBarcode());
		productDetail.setQuantity(productDetailsForm.getQuantity());
		productDetail.setUnitType(productDetailsForm.getUnit());
		productDetail.setGrossPrice(productDetailsForm.getGrossPrice());
		productDetail.setDiscount(productDetailsForm.getDiscount());
		productDetail.setNetPrice(productDetailsForm.getNetPrice());
		productDetail.setPercentProfit(productDetailsForm.getPercentProfit());
		productDetail.setSellingPrice(productDetailsForm.getSellingPrice());
		productDetail.setNetProfit(productDetailsForm.getNetProfit());
	}
}
