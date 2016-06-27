package com.chua.evergrocery.database.dao.impl;

import java.util.List;

import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chua.evergrocery.database.dao.ProductDetailDAO;
import com.chua.evergrocery.database.entity.ProductDetail;

@Repository
public class ProductDetailDAOImpl 
		extends AbstractDAO<ProductDetail, Long>
		implements ProductDetailDAO 
{
	@Override
	public List<ProductDetail> findAllByProductId(Long productId) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.eq("product.id", productId));
		
		return findAllByCriterionList(null, null, null, null, conjunction);
	}

	@Override
	public ProductDetail findByBarcode(String barcode) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.eq("barcode", barcode));
		
		return findUniqueResult(null, null, null, conjunction);
	}
}