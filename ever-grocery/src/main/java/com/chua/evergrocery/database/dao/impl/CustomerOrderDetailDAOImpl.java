package com.chua.evergrocery.database.dao.impl;

import java.util.List;

import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chua.evergrocery.database.dao.CustomerOrderDetailDAO;
import com.chua.evergrocery.database.entity.CustomerOrderDetail;
import com.chua.evergrocery.objects.ObjectList;

@Repository
public class CustomerOrderDetailDAOImpl 
		extends AbstractDAO<CustomerOrderDetail, Long>
		implements CustomerOrderDetailDAO 
{

	@Override
	public ObjectList<CustomerOrderDetail> findAllWithPaging(int pageNumber, int resultsPerPage, long customerOrderId) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.eq("customerOrder.id", customerOrderId));
		
		return findAllByCriterion(pageNumber, resultsPerPage, null, null, null, null, conjunction);
	}
	
	@Override
	public List<CustomerOrderDetail> findAllByCustomerOrderId(Long customerOrderId) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.eq("customerOrder.id", customerOrderId));
		
		return findAllByCriterionList(null, null, null, null, conjunction);
	}

	@Override
	public CustomerOrderDetail findByOrderAndDetailId(long customerOrderId, long productDetailId) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.eq("customerOrder.id", customerOrderId));
		conjunction.add(Restrictions.eq("productDetail.id", productDetailId));
		
		return findUniqueResult(null, null, null, conjunction);
	}
}
