package com.chua.evergrocery.database.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chua.evergrocery.database.dao.CustomerOrderDAO;
import com.chua.evergrocery.database.entity.CustomerOrder;
import com.chua.evergrocery.enums.Status;
import com.chua.evergrocery.objects.ObjectList;

@Repository
public class CustomerOrderDAOImpl 
		extends AbstractDAO<CustomerOrder, Long>
		implements CustomerOrderDAO {

	@Override
	public ObjectList<CustomerOrder> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		
		final Disjunction disjunction = Restrictions.disjunction();
		
		if(StringUtils.isNotBlank(searchKey))
		{
			
			disjunction.add(Restrictions.ilike("name", searchKey, MatchMode.ANYWHERE));
		}
		
		conjunction.add(disjunction);
		
		return findAllByCriterion(pageNumber, resultsPerPage, null, null, null, null, conjunction);
	}
	
	@Override
	public ObjectList<CustomerOrder> findAllWithPagingWithStatus(int pageNumber, int resultsPerPage, String searchKey, Status[] status)
	{
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		
		final Disjunction disjunction = Restrictions.disjunction();
		
		if(StringUtils.isNotBlank(searchKey))
		{
			
			disjunction.add(Restrictions.ilike("name", searchKey, MatchMode.ANYWHERE));
		}
		
		for(Status statuz : status) {
			disjunction.add(Restrictions.eq("status", statuz));
		}
		
		conjunction.add(disjunction);
		
		return findAllByCriterion(pageNumber, resultsPerPage, null, null, null, null, conjunction);
	}
	
	@Override
	public CustomerOrder findByNameAndStatus(String name, Status[] status) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.eq("name", name));
		
		final Disjunction disjunction = Restrictions.disjunction();
		
		for(Status statuz : status) {
			disjunction.add(Restrictions.eq("status", statuz));
		}
		
		conjunction.add(disjunction);
		
		return findUniqueResult(null, null, null, conjunction);
	}
}
